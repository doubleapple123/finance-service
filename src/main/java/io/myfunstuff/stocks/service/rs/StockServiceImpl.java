package io.myfunstuff.stocks.service.rs;

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
//
//	@Autowired
//	AlphaVantageClientService alphaVantageClientService;

	@Autowired
	StockRepo stockrepo;

	@Autowired
	public StockServiceImpl() {
	}

	@Override
	public StockStatistics analyzeTimeSeriesData(String symbol, String startDate, String endDate, TimeSeriesType timeseriesType) {
		TimeSeriesDataCollection timeSeriesData = stockAnalysisService.parseRawTimeSeriesData(startDate, endDate, symbol, timeseriesType);
		return stockAnalysisService.getStockStatistics(symbol, timeSeriesData);
	}

	@Override
	public ArrayList getStockData(String symbol, String startDate, String endDate){
		StockTimeDataCollection stockTimeDataCollection = new StockTimeDataCollection(symbol, startDate, endDate);
		return stockTimeDataCollection.getStockTimeData();
	}

//	@Override
//	public TechnicalDetails getAnalysis(String type, Long id) {
//		//analysis
//		return null;
//	}
}
