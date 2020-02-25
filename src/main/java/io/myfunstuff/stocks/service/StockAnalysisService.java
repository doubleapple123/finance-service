package io.myfunstuff.stocks.service;

import DataParser.DataCollections.DataCollection;
import io.myfunstuff.stocks.model.StockModels.StockStatistics;
import io.myfunstuff.stocks.model.TimeSeriesDataCollection;
import io.myfunstuff.stocks.model.TimeSeriesType;

public interface StockAnalysisService {

	StockStatistics getStockStatistics(DataCollection stockDC);
	
	TimeSeriesDataCollection parseRawTimeSeriesData(String timeseries, String startDate, String endDate, String symbol, TimeSeriesType timeseriesType);
	
}
