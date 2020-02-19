package io.myfunstuff.stocks.service.rs;

import DataParser.DBQueries.QueryExecute;
import DataParser.DBQueries.QueryUpdate;
import DataParser.DataCollections.StockAdjustedDailyDC;
import DataParser.DataCollections.StockTimeDC;
import io.myfunstuff.stocks.model.StockStatistics;
import io.myfunstuff.stocks.service.StockAnalysisService;
import io.myfunstuff.stocks.service.database.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
public class StockServiceImpl implements StockService {

	@Autowired
	StockAnalysisService stockAnalysisService;

	@Autowired
	StockRepo stockrepo;

	@Autowired
	public StockServiceImpl() {
	}

	public void addNotExist(String timeseries, String symbol){
		QueryExecute queryExecute = new QueryExecute();
		QueryUpdate queryUpdate = new QueryUpdate();

		if(!queryExecute.checkExist(symbol)){
			//if the symbol does not exist in table `stocksymbols`, then add data from past 20 yrs
			//to daily and weekly table
			queryUpdate.setTimeser(timeseries);
			queryUpdate.addToDatabaseSymbol(symbol, "full");
			queryUpdate.updateQuery();

			queryUpdate.addToMainTable(symbol);
			queryUpdate.updateQuery();
		}
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
	}

	@Override
	public void updateDatabase(){
		updateAll();

	}

	@Override
	public StockStatistics analyzeTimeSeriesData(String timeseries, String symbol, String startDate, String endDate) {
		addNotExist(timeseries, symbol);
		StockAdjustedDailyDC stockFullDC = new StockAdjustedDailyDC(timeseries, symbol, startDate, endDate);
		stockFullDC.convertArr();
		return stockAnalysisService.getStockStatistics(stockFullDC);
	}

	@Override
	public ArrayList getStockData(String timeseries, String symbol, String startDate, String endDate){
		addNotExist(timeseries, symbol);
		StockTimeDC stockTimeDC = new StockTimeDC(timeseries, symbol, startDate, endDate);
		stockTimeDC.convertArr();
		return stockTimeDC.getDataRow();
	}

	@Override
	public ArrayList getFullStockData(String timeseries, String symbol, String startDate, String endDate){
		addNotExist(timeseries, symbol);
		StockAdjustedDailyDC stockFullDC = new StockAdjustedDailyDC(timeseries, symbol, startDate, endDate);
		stockFullDC.convertArr();
		return stockFullDC.getDataRow();
	}

}
