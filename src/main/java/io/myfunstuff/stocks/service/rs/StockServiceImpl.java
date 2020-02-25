package io.myfunstuff.stocks.service.rs;

import DataParser.DBQueries.QueryExecute;
import DataParser.DBQueries.QueryUpdate;
import DataParser.DataCollections.DataCollection;
import io.myfunstuff.stocks.model.StockModels.StockAdjustedDaily;
import io.myfunstuff.stocks.model.StockModels.StockAdjustedWeekly;
import io.myfunstuff.stocks.model.StockModels.StockStatistics;
import io.myfunstuff.stocks.service.StockAnalysisService;
import io.myfunstuff.stocks.service.database.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

@Controller
public class StockServiceImpl <T> implements StockService {
	//TODO add to this when adding new data (including TA)
	private String[] listOfTimeseries = {"TIME_SERIES_DAILY_ADJUSTED", "TIME_SERIES_WEEKLY_ADJUSTED"};

	@Autowired
	StockAnalysisService stockAnalysisService;

	@Autowired
	StockRepo stockrepo;

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

	//TODO configure this when adding new data, need to setTimeser to corresponding correct database
	public void addNotExist(String symbol){
		QueryExecute queryExecute = new QueryExecute();
		QueryUpdate queryUpdate = new QueryUpdate();

		if(!queryExecute.checkExist(symbol)){
			//if the symbol does not exist in table `stocksymbols`, then add data from past 20 yrs
			//to daily and weekly table
			for(String timeseries : listOfTimeseries){
				queryUpdate.setTimeser(timeseries);
				queryUpdate.addToDatabaseSymbol(symbol, "full");
				System.out.println(queryUpdate.getQuery());
				queryUpdate.updateQuery();

			}

			queryUpdate.addToMainTable(symbol);
			queryUpdate.updateQuery();
		}

		queryExecute.closeConnection();
		queryUpdate.closeConnection();
	}

	public void updateAll(){
		QueryExecute queryExecute = new QueryExecute();
		QueryUpdate queryUpdate = new QueryUpdate();

		queryExecute.getDistinctSymbols();
		ArrayList<ArrayList<Object>> symbols = queryExecute.executeQuery();

 		for(ArrayList<Object> obj : symbols){
 			queryUpdate.setTimeser("TIME_SERIES_DAILY_ADJUSTED");
 			queryUpdate.addToDatabaseSymbol(obj.get(0).toString(), "compact");
 			queryUpdate.updateQuery();
		}

		queryExecute.closeConnection();
		queryUpdate.closeConnection();
	}

	@Override
	public void updateDatabase(){
		updateAll();
	}

	@Override
	public StockStatistics analyzeTimeSeriesData(String timeseries, String symbol, String startDate, String endDate) {
		addNotExist(symbol);
		DataCollection<StockAdjustedDaily> stockFullDC = new DataCollection<>(timeseries, symbol, startDate, endDate);
		stockFullDC.convertArr(StockAdjustedDaily.class);
		return stockAnalysisService.getStockStatistics(stockFullDC);
	}

//	@Override
//	public ArrayList getStockData(String timeseries, String symbol, String startDate, String endDate){
//		addNotExist(timeseries, symbol);
//		StockTimeDC stockTimeDC = new StockTimeDC(timeseries, symbol, startDate, endDate);
//		stockTimeDC.convertArr();
//		return stockTimeDC.getDataRow();
//	}

	@Override
	public ArrayList getFullStockData(String timeseries, String symbol, String startDate, String endDate) {
		addNotExist(symbol);
		Class type = getDataObject(timeseries);
		DataCollection<T> stockFullDC = new DataCollection<>(timeseries, symbol, startDate, endDate);
		stockFullDC.convertArr(type);
		return stockFullDC.getDataRow();
	}

}
