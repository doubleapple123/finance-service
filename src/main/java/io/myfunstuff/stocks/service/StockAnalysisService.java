package io.myfunstuff.stocks.service;

import io.myfunstuff.stocks.model.StockStatistics;
import io.myfunstuff.stocks.model.TimeSeriesDataCollection;
import io.myfunstuff.stocks.model.TimeSeriesType;

public interface StockAnalysisService {

	StockStatistics getStockStatistics(String symbol, TimeSeriesDataCollection timeseriesData);
	
	TimeSeriesDataCollection parseRawTimeSeriesData(String startDate, String endDate, String symbol, TimeSeriesType timeseriesType);
	
}
