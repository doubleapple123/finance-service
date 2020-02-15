package io.myfunstuff.stocks.service;

import DataParser.DataCollections.StockAdjustedDailyDC;
import io.myfunstuff.stocks.model.StockStatistics;
import io.myfunstuff.stocks.model.TimeSeriesDataCollection;
import io.myfunstuff.stocks.model.TimeSeriesType;

public interface StockAnalysisService {

	StockStatistics getStockStatistics(StockAdjustedDailyDC stockDC);
	
	TimeSeriesDataCollection parseRawTimeSeriesData(String timeseries, String startDate, String endDate, String symbol, TimeSeriesType timeseriesType);
	
}
