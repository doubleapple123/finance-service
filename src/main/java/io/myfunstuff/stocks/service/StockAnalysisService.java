package io.myfunstuff.stocks.service;

import io.myfunstuff.stocks.model.StockStatistics;
import io.myfunstuff.stocks.model.TimeSeriesDataCollection;
import io.myfunstuff.stocks.model.TimeSeriesType;

public interface StockAnalysisService {
	
	public StockStatistics getStockStatistics(String symbol, TimeSeriesDataCollection timeseriesData);
	
	public TimeSeriesDataCollection parseRawTimeSeriesData(String symbol, TimeSeriesType timeseriesType);
	
}
