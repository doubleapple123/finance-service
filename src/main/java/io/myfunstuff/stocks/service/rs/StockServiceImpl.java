package io.myfunstuff.stocks.service.rs;

import DataParser.DBQueries.QueryExecute;
import DataParser.DBQueries.QueryUpdate;
import DataParser.DataCollections.DataCollection;
import io.myfunstuff.stocks.PropertyValues;
import io.myfunstuff.stocks.model.StockModels.StockAdjustedDaily;
import io.myfunstuff.stocks.model.StockModels.StockAdjustedWeekly;
import io.myfunstuff.stocks.model.StockModels.StockStatistics;
import io.myfunstuff.stocks.service.StockAnalysisService;
import io.myfunstuff.stocks.service.database.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
public class StockServiceImpl <T> implements StockService {
	QueryUpdate queryUpdate;

	@Autowired
	StockAnalysisService stockAnalysisService;

	@Autowired
	StockRepo stockrepo;

	@Autowired
	PropertyValues propertyValues;

	@Autowired
	public StockServiceImpl() {
	}

	//TODO configure this when adding....
	public Class getDataObject(String timeseries){
		switch(timeseries){
			case "TIME_SERIES_DAILY_ADJUSTED" : return StockAdjustedDaily.class;
			case "TIME_SERIES_WEEKLY_ADJUSTED" : return StockAdjustedWeekly.class;
			default: return null;
		}
	}

	@Override
	public StockStatistics analyzeTimeSeriesData(String timeseries, String symbol, String startDate, String endDate) {
		queryUpdate = new QueryUpdate(propertyValues);
		queryUpdate.addNotExist(symbol);
		DataCollection<StockAdjustedDaily> stockFullDC = new DataCollection<>(timeseries, symbol, startDate, endDate);
		stockFullDC.convertArr(StockAdjustedDaily.class);
		return stockAnalysisService.getStockStatistics(stockFullDC);
	}

	@Override
	public ArrayList getFullStockData(String timeseries, String symbol, String startDate, String endDate) {
		queryUpdate = new QueryUpdate(propertyValues);
		queryUpdate.addNotExist(symbol);
		Class type = getDataObject(timeseries);
		DataCollection<T> stockFullDC = new DataCollection<>(timeseries, symbol, startDate, endDate);
		stockFullDC.convertArr(type);
		return stockFullDC.getDataRow();
	}

}
