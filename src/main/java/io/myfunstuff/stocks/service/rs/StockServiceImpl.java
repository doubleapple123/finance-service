package io.myfunstuff.stocks.service.rs;

import io.myfunstuff.stocks.model.StockStatistics;
import io.myfunstuff.stocks.model.TimeSeriesDataCollection;
import io.myfunstuff.stocks.model.TimeSeriesType;
import io.myfunstuff.stocks.service.StockAnalysisService;
import io.myfunstuff.stocks.service.database.StockRepo;
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
	public StockStatistics analyzeTimeSeriesData(String symbol, String startDate, String endDate, TimeSeriesType timeseriesType) {
		TimeSeriesDataCollection timeSeriesData = stockAnalysisService.parseRawTimeSeriesData(startDate, endDate, symbol, timeseriesType);
		return stockAnalysisService.getStockStatistics(symbol, timeSeriesData);
	}

//	@Override
//	public TechnicalDetails getAnalysis(String type, Long id) {
//		//analysis
//		return null;
//	}
}
