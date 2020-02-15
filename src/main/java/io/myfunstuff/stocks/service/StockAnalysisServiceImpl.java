package io.myfunstuff.stocks.service;

import DataParser.DBQueries.QueryExecute;
import DataParser.DataCollections.StockAdjustedDailyDC;
import io.myfunstuff.stocks.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class StockAnalysisServiceImpl implements StockAnalysisService{

	private static final String dateFormat = "yyyy-MM-dd";
	private static Logger log = LoggerFactory.getLogger(StockAnalysisServiceImpl.class);

	@Autowired
	public StockAnalysisServiceImpl(){
	}

	//TODO fix this function
	@Override
	public StockStatistics getStockStatistics(StockAdjustedDailyDC stockDC){

		StockStatistics stats = new StockStatistics();
		int dataSize = stockDC.getDataRow().size();
		String lowDate = stockDC.getStartDate();
		String highDate = stockDC.getStartDate();
		float low = Float.MAX_VALUE;
		float high = 0.0F;

		for(Object obj : stockDC.getDataRow()){
			StockAdjustedDaily stockObj = ((StockAdjustedDaily)obj);
			if(stockObj.getLow() < low){
				low = (float) stockObj.getLow();
				lowDate = stockObj.getDate();
			}

			if(stockObj.getHigh() > high){
				high = (float) stockObj.getAdjustedClose();
				highDate = stockObj.getDate();
			}
		}

		stats.setDataPointType(stockDC.getTimeseries());
		stats.setSymbol(stockDC.getSymbol());
		stats.setLow(low);
		stats.setHigh(high);
		stats.setPeriodStart(((StockAdjustedDaily)stockDC.getDataRow().get(0)).getDate());
		stats.setPeriodEnd(((StockAdjustedDaily)stockDC.getDataRow().get(dataSize-1)).getDate());
		stats.setLowDate(lowDate);
		stats.setHighDate(highDate);
		return stats;
	}

	@Override
	public TimeSeriesDataCollection parseRawTimeSeriesData(String timeseries, String startDate, String endDate, String symbol, TimeSeriesType timeseriesType){
		TimeSeriesDataCollection timeSeriesDataCollection = new TimeSeriesDataCollection(startDate, endDate, symbol, timeseriesType);
		QueryExecute queryExecute = new QueryExecute();
		queryExecute.setTimeser(timeseries);
		queryExecute.getTableFromSymbol(symbol, startDate, endDate);
		ArrayList<ArrayList<Object>> table = queryExecute.executeQuery();

		String key = "";
		Map<String, String> m = new HashMap<>();

		for(ArrayList<Object> row : table){
			for(int i = 2; i < row.size(); i++){

				switch(i){
					case 2:
						key = "open";
						break;
					case 3:
						key = "high";
						break;
					case 4:
						key = "low";
						break;
					case 5:
						key = "close";
						break;
					case 6:
						key = "volume";
						break;
					default:
						break;
				}
				m.put(key, row.get(i).toString());
			}

			timeSeriesDataCollection.addTimeSeriesData((Date) row.get(1), TimeSeriesData.convertFromMap(m));
		}
		queryExecute.closeConnection();
		return timeSeriesDataCollection;
	}

}
