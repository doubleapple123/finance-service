package io.myfunstuff.stocks.service.rs;

import io.myfunstuff.stocks.model.StockStatistics;
import io.myfunstuff.stocks.model.TimeSeriesDataCollection;
import io.myfunstuff.stocks.model.TimeSeriesType;
//import io.myfunstuff.stocks.service.AlphaVantageClientService;
import io.myfunstuff.stocks.service.StockAnalysisService;
import io.myfunstuff.stocks.service.database.StockRepo;
import io.myfunstuff.stocks.service.technicalFunctions.TechnicalDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
	public StockStatistics analyzeTimeSeriesData(String symbol, TimeSeriesType timeseriesType, int dataSize) {
		TimeSeriesDataCollection timeSeriesData = stockAnalysisService.parseRawTimeSeriesData(symbol, timeseriesType);
		System.out.println(symbol);
		return stockAnalysisService.getStockStatistics(symbol, timeSeriesData);
	}

	@Override
	public Iterable<StockStatistics> getAllStat(){
		return stockrepo.findAll();
	}

	@Override
	public TechnicalDetails getAnalysis(String type, Long id) {
		//analysis
		return null;
	}
}
