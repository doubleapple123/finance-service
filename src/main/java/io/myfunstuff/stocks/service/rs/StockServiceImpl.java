package io.myfunstuff.stocks.service.rs;

import io.myfunstuff.stocks.service.database.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import io.myfunstuff.stocks.model.StockStatistics;
import io.myfunstuff.stocks.model.TimeSeriesDataCollection;
import io.myfunstuff.stocks.model.TimeSeriesType;
import io.myfunstuff.stocks.service.AlphaVantageClientService;
import io.myfunstuff.stocks.service.StockAnalysisService;

import java.util.Iterator;
import java.util.Optional;

@Controller
public class StockServiceImpl implements StockService {

	@Autowired
	StockAnalysisService stockAnalysisService;
	
	@Autowired
	AlphaVantageClientService alphaVantageClientService;

	@Autowired
	StockRepo stockrepo;
	
	@Autowired
	public StockServiceImpl() {
	}
	
	@Override
	public StockStatistics analyzeTimeSeriesData(String symbol, TimeSeriesType timeseriesType, int dataSize) {
		String rawData = alphaVantageClientService.retrieveTimeSeriesData(symbol, timeseriesType, dataSize);
		TimeSeriesDataCollection timeSeriesData = stockAnalysisService.parseRawTimeSeriesData(rawData, timeseriesType);
		return stockAnalysisService.getStockStatistics(timeSeriesData);
	}

	@Override
	public void addStockData(String symbol, TimeSeriesType timeseriesType, int dataSize){
		String rawData = alphaVantageClientService.retrieveTimeSeriesData(symbol, timeseriesType, dataSize);
		TimeSeriesDataCollection timeSeriesData = stockAnalysisService.parseRawTimeSeriesData(rawData, timeseriesType);
		stockrepo.save(stockAnalysisService.getStockStatistics(timeSeriesData));
	}

	@Override
	public Optional<StockStatistics> getStatistics(String symbol, TimeSeriesType timeseriesType, int dataSize){
		return stockrepo.findById(symbol);
	}

}
