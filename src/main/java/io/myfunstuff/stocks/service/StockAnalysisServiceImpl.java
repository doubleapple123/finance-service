package io.myfunstuff.stocks.service;

import DataParser.DBConnector.Connector;
import io.myfunstuff.stocks.model.StockStatistics;
import io.myfunstuff.stocks.model.TimeSeriesData;
import io.myfunstuff.stocks.model.TimeSeriesDataCollection;
import io.myfunstuff.stocks.model.TimeSeriesType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class StockAnalysisServiceImpl implements StockAnalysisService {

	private static final String dateFormat = "yyyy-MM-dd";
	private static Logger log = LoggerFactory.getLogger(StockAnalysisServiceImpl.class);

	@Autowired
	public StockAnalysisServiceImpl() {
	}

	@Override
	public StockStatistics getStockStatistics(String symbol, TimeSeriesDataCollection timeseriesData) {
		if (timeseriesData == null) {
			return null;
		}

		StockStatistics stats = new StockStatistics(timeseriesData.getSymbol(), timeseriesData.getType());
		Date start = null;
		Date end = null;
		Date lowDate = null;
		Date highDate = null;
		float low = 0.0F;
		float high = 0.0F;
		for (Map.Entry<Date, TimeSeriesData> e : timeseriesData.getTimeSeries().entrySet()) {
			Date d = e.getKey();
			if (start == null) {
				start = d;
				end = d;
				lowDate = d;
				highDate = d;
				low = e.getValue().getLow();
				high = e.getValue().getHigh();
			} else {
				if (d.before(start))
					start = d;
				if (d.after(end))
					end = d;
				if (e.getValue().getLow() < low) {
					low = e.getValue().getLow();
					lowDate = d;
				}
				if (e.getValue().getHigh() > high) {
					high = e.getValue().getHigh();
					highDate = d;
				}
			}
		}
		stats.setLow(low);
		stats.setHigh(high);
		stats.setPeriodStart(new SimpleDateFormat(dateFormat).format(start));
		stats.setPeriodEnd(new SimpleDateFormat(dateFormat).format(end));
		stats.setLowDate(new SimpleDateFormat(dateFormat).format(lowDate));
		stats.setHighDate(new SimpleDateFormat(dateFormat).format(highDate));
		return stats;
	}

	@Override
	public TimeSeriesDataCollection parseRawTimeSeriesData(String symbol, TimeSeriesType timeseriesType) {
		TimeSeriesDataCollection timeSeriesCollection = null;
		try {
				Connector connector = new Connector();
				ArrayList<ArrayList<Object>> table = connector.getTable();
				timeSeriesCollection = new TimeSeriesDataCollection(table.get(0).get(0).toString(), timeseriesType);

				String key = "";
				Map<String, String> m = new HashMap<>();

				for(ArrayList<Object> row : table){
					for(int i = 2; i < row.size(); i++){

						switch(i){
							case 2 : key = "open"; break;
							case 3 : key = "high"; break;
							case 4 : key = "low"; break;
							case 5 : key = "close"; break;
							case 6 : key = "volume"; break;
							default : break;
						}
						m.put(key, row.get(i).toString());
					}

					timeSeriesCollection.addTimeSeriesData((Date) row.get(1), TimeSeriesData.convertFromMap(m));
				}
				connector.closeConnections();

//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (timeSeriesCollection != null) {
			log.debug(String.format("Find timeSeriesCollection on (%s) of size (%d)", timeSeriesCollection.getSymbol(),
					timeSeriesCollection.getTimeSeries().size()));
		}
		return timeSeriesCollection;
	}

}
