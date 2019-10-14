package io.myfunstuff.stocks.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.myfunstuff.stocks.model.StockStatistics;
import io.myfunstuff.stocks.model.TimeSeriesData;
import io.myfunstuff.stocks.model.TimeSeriesDataCollection;
import io.myfunstuff.stocks.model.TimeSeriesType;

@Service
public class StockAnalysisServiceImpl implements StockAnalysisService {

	private static final String dateFormat = "yyyy-MM-dd";
	private static Logger log = LoggerFactory.getLogger(StockAnalysisServiceImpl.class);

	@Autowired
	public StockAnalysisServiceImpl() {
	}

	@Override
	public StockStatistics getStockStatistics(TimeSeriesDataCollection timeseriesData) {
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
	public TimeSeriesDataCollection parseRawTimeSeriesData(String rawData, TimeSeriesType timeseriesType) {
		if (StringUtils.isBlank(rawData)) {
			return null;
		}
		ObjectMapper objectMapper = new ObjectMapper();
		String symbol = null;
		TimeSeriesDataCollection timeSeriesCollection = null;
		try {
			JsonNode jsonRootNode = objectMapper.readTree(rawData);
			Iterator<String> fields = jsonRootNode.fieldNames();
			JsonNode metaData = jsonRootNode.get("Meta Data");
			Iterator<Entry<String, JsonNode>> metaDataFields = metaData.fields();
			while (metaDataFields.hasNext()) {
				Entry<String, JsonNode> i = metaDataFields.next();
				if (i.getKey().contains("Symbol")) {
					symbol = i.getValue().asText();
				}
			}
			JsonNode timeSeries = null;
			while (fields.hasNext()) {
				String fieldName = fields.next();
				if (fieldName.contains("Time Series")) {
					timeSeries = jsonRootNode.get(fieldName);
					break;
				}
			}
			if (timeSeries != null) {
				timeSeriesCollection = new TimeSeriesDataCollection(symbol, timeseriesType);
				Iterator<Entry<String, JsonNode>> timeSeriesData = timeSeries.fields();
				while (timeSeriesData.hasNext()) {
					Entry<String, JsonNode> e = timeSeriesData.next();
					Date date = new SimpleDateFormat(dateFormat).parse(e.getKey());
					JsonNode valuesNode = e.getValue();
					Iterator<Entry<String, JsonNode>> values = valuesNode.fields();
					Map<String, String> m = new HashMap<String, String>();
					while (values.hasNext()) {
						Entry<String, JsonNode> v = values.next();
						m.put(v.getKey(), v.getValue().asText());
					}
					timeSeriesCollection.addTimeSeriesData(date, TimeSeriesData.convertFromMap(m));
				}
			}
		} catch (IOException | ParseException e) {
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
