package io.myfunstuff.stocks.service.rs;

import DataParser.DBQueries.QueryExecute;
import DataParser.DBQueries.QueryUpdate;
import DataParser.DataCollections.StockAdjustedDailyDC;
import DataParser.DataCollections.StockFullDC;
import DataParser.DataCollections.StockTimeDC;
import io.myfunstuff.stocks.model.StockStatistics;
import io.myfunstuff.stocks.model.TimeSeriesDataCollection;
import io.myfunstuff.stocks.model.TimeSeriesType;
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
			queryUpdate.addToDatabaseSymbol(symbol);
			queryUpdate.updateQuery();

			queryUpdate.addToMainTable(symbol);
			queryUpdate.updateQuery();
		}
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
