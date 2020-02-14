package io.myfunstuff.stocks.service.rs;

import DataParser.DBQueries.Queries;
import DataParser.DataCollections.StockFullDC;
import DataParser.DataCollections.StockTimeDC;
import io.myfunstuff.stocks.model.*;
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

	//TODO implement this function
	public void addNotExist(String symbol){
		Queries queries = new Queries();
		if(queries.checkExist("stocksymbols", symbol)){

		}
	}

	@Override
	public StockStatistics analyzeTimeSeriesData(String timeseries, String symbol, String startDate, String endDate, TimeSeriesType timeseriesType) {
		TimeSeriesDataCollection timeSeriesData = stockAnalysisService.parseRawTimeSeriesData(timeseries, startDate, endDate, symbol, timeseriesType);
		return stockAnalysisService.getStockStatistics(symbol, timeSeriesData);
	}

	@Override
	public ArrayList getStockData(String timeseries, String symbol, String startDate, String endDate){
		StockTimeDC stockTimeDC = new StockTimeDC(timeseries, symbol, startDate, endDate);
		stockTimeDC.convertArr();
		return stockTimeDC.getDataRow();
	}

	@Override
	public ArrayList getFullStockData(String timeseries, String symbol, String startDate, String endDate){
		StockFullDC stockFullDC = new StockFullDC(timeseries, symbol, startDate, endDate);
		stockFullDC.convertArr();
		return stockFullDC.getDataRow();
	}

}
