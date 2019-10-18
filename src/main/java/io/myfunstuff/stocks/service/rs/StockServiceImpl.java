package io.myfunstuff.stocks.service.rs;

import io.myfunstuff.stocks.service.technicalFunctions.TechnicalDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import io.myfunstuff.stocks.model.StockStatistics;
import io.myfunstuff.stocks.model.TimeSeriesDataCollection;
import io.myfunstuff.stocks.model.TimeSeriesType;
import io.myfunstuff.stocks.service.AlphaVantageClientService;
import io.myfunstuff.stocks.service.StockAnalysisService;

@Controller
public class StockServiceImpl implements StockService {

	@Autowired
	StockAnalysisService stockAnalysisService;
	
	@Autowired
	AlphaVantageClientService alphaVantageClientService;

//	@Autowired
//	StockRepo stockrepo;

//	@Autowired
//	TechnicalDetails analysis;
	
	@Autowired
	public StockServiceImpl() {
	}
	
	@Override
	public StockStatistics analyzeTimeSeriesData(String symbol, TimeSeriesType timeseriesType, int dataSize) {
		String rawData = alphaVantageClientService.retrieveTimeSeriesData(symbol, timeseriesType, dataSize);
		TimeSeriesDataCollection timeSeriesData = stockAnalysisService.parseRawTimeSeriesData(rawData, timeseriesType);
		return stockAnalysisService.getStockStatistics(timeSeriesData);
	}

//	@Override
//	public String addStockData(String symbol, TimeSeriesType timeseriesType, int dataSize){
//		try{
//			String rawData = alphaVantageClientService.retrieveTimeSeriesData(symbol, timeseriesType, dataSize);
//			TimeSeriesDataCollection timeSeriesData = stockAnalysisService.parseRawTimeSeriesData(rawData, timeseriesType);
//			stockrepo.save(stockAnalysisService.getStockStatistics(timeSeriesData));
//		}catch(Exception e){
//			return "Error";
//		}
//		return "Saved Succesfully";
//	}
//
//	@Override
//	public StockStatistics getStatistics(Long id){
//		return stockrepo.findById(id).get();
//	}
//
//	@Override
//	public Iterable<StockStatistics> getAllStat(){
//		return stockrepo.findAll();
//	}
//
//	@Override
//	public TechnicalDetails getAnalysis(String type, Long id) {
//		//analysis
//		return null;
//	}
}
