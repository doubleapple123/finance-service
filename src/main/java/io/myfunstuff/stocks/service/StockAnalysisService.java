package io.myfunstuff.stocks.service;

import io.myfunstuff.stocks.model.StockStatistics;
import io.myfunstuff.stocks.model.TimeSeriesDataCollection;
import io.myfunstuff.stocks.model.TimeSeriesType;

public interface StockAnalysisService {
	
	public StockStatistics getStockStatistics(TimeSeriesDataCollection timeseriesData);
	
	public TimeSeriesDataCollection parseRawTimeSeriesData(String rawData, TimeSeriesType timeseriesType);
	
}
