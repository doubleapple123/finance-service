package io.myfunstuff.stocks.service.rs;

import io.myfunstuff.stocks.model.StockModels.StockAdjustedDaily;
import io.myfunstuff.stocks.model.StockModels.StockAdjustedWeekly;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

@Service
public class WebServiceImpl implements WebService {
	private String thisTimeseries;
	private String symbol, startDate, endDate, chartType;

	@Autowired
	StockServiceImpl stockService;

	@Async
	@Scheduled(fixedRate = 60000)
	public void getIntradayUpdates() throws IOException, ClassNotFoundException{
		if(symbol != null && thisTimeseries.equals("TIME_SERIES_INTRADAY")){
			getHello(symbol,startDate,endDate,thisTimeseries,chartType);
		}
	}

	public String getData(String sym, String start, String end) {
		ArrayList stockdata = stockService.getFullStockData(thisTimeseries,sym,start,end);
		StringBuilder dataBuilder = new StringBuilder();
		double firstOpen = 0;
		double adjustedClose = 0;

		String date = "";

		for(Object obj : stockdata){
			if(obj instanceof StockAdjustedDaily){
				firstOpen = ((StockAdjustedDaily)stockdata.get(0)).getAdjustedClose();
				date = ((StockAdjustedDaily) obj).getDate();
				adjustedClose = ((StockAdjustedDaily) obj).getAdjustedClose();

			}else if(obj instanceof StockAdjustedWeekly){
				firstOpen = ((StockAdjustedWeekly)stockdata.get(0)).getAdjustedClose();
				date = ((StockAdjustedWeekly) obj).getDate();
				adjustedClose = ((StockAdjustedWeekly) obj).getAdjustedClose();

			}

			dataBuilder.append(date).append(",").append(adjustedClose/firstOpen).append("?");

		}


		return dataBuilder.toString();

	}

    public ModelAndView getHello(String symbol, String startDate, String endDate, String timeseries, String chartType) throws IOException, ClassNotFoundException{
		this.thisTimeseries = timeseries;
		this.symbol = symbol;
		this.startDate = startDate;
		this.endDate = endDate;
		this.chartType = chartType;

		StringBuilder dataBuilder = new StringBuilder();
		StringBuilder dataVolumeBuilder = new StringBuilder();

		ArrayList stockdata = stockService.getFullStockData(thisTimeseries,symbol,startDate,endDate);

		ModelAndView model = new ModelAndView("hello");
        StringBuilder symbolsData = new StringBuilder();
        String[] symbols = symbol.split(",");
		model.addObject("symbols", Arrays.toString(symbols).toUpperCase());

        if (symbols.length > 1) {
        	for(String str:symbols){
				symbolsData.append(getData(str,startDate,endDate)).append("+");
			}
			model.addObject("multSym",symbolsData);

		}else{

        	symbol = symbol.toUpperCase();
			double open = 0,
					high = 0,
					low = 0,
					adjustedRatio,
					adjustedClose = 0,
					volume = 0;

			String date = "";

			for(Object obj : stockdata){
				if(obj instanceof StockAdjustedDaily){
					adjustedRatio = ((StockAdjustedDaily) obj).getClose()/((StockAdjustedDaily) obj).getAdjustedClose();
					open = ((StockAdjustedDaily) obj).getOpen()/adjustedRatio;
					high = ((StockAdjustedDaily) obj).getHigh()/adjustedRatio;
					low = ((StockAdjustedDaily) obj).getLow()/adjustedRatio;
					adjustedClose = ((StockAdjustedDaily) obj).getAdjustedClose();
					volume = ((StockAdjustedDaily) obj).getVolume();
					date = ((StockAdjustedDaily) obj).getDate();

				}else if(obj instanceof StockAdjustedWeekly){
					adjustedRatio = ((StockAdjustedWeekly) obj).getClose()/((StockAdjustedWeekly) obj).getAdjustedClose();
					open = ((StockAdjustedWeekly) obj).getOpen()/adjustedRatio;
					high = ((StockAdjustedWeekly) obj).getHigh()/adjustedRatio;
					low = ((StockAdjustedWeekly) obj).getLow()/adjustedRatio;
					adjustedClose = ((StockAdjustedWeekly) obj).getAdjustedClose();
					volume = ((StockAdjustedWeekly) obj).getVolume();
					date = ((StockAdjustedWeekly) obj).getDate();

				}
				if(chartType.equals("line")){
					dataBuilder.append(date).append(",").append(adjustedClose).append("?");
					dataVolumeBuilder.append(date).append(",").append(volume).append("?");

				}else if(chartType.equals("candle")){
					dataBuilder.append(date).append(",").append(open).append(",").append(high).append(",").append(low).append(",").append(adjustedClose).append("?");
					dataVolumeBuilder.append(date).append(",").append(volume).append("?");
				}
			}

			model.addObject("volDataColl", dataVolumeBuilder.toString().replaceAll("/", "-"));
			model.addObject("dataColl", dataBuilder.toString().replaceAll("/", "-"));
			model.addObject("timeseries", this.thisTimeseries);
			model.addObject("chartType", chartType);

		}

        model.addObject("name", symbol.toUpperCase());

        return model;
    }
}
