package io.myfunstuff.stocks.service.rs;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

@Service
public class WebServiceImpl implements WebService {
	private String thisTimeseries;

	@Async
	@Scheduled(fixedRate = 60000)
	public void getIntradayUpdates(){

	}

	public String getData(String sym, String start, String end) throws IOException {
		StringBuilder dataBuilder = new StringBuilder();
		URL address = new URL(String.format("http://localhost:5000/stock/data/alldata?symbol=%s&startDate=%s&endDate=%s&timeseries=%s", sym, start, end, thisTimeseries));
//		URL address = new URL(String.format("http://applestock.us-west-1.elasticbeanstalk.com/stock/data/alldata?symbol=%s&startDate=%s&endDate=%s&timeseries=%s", sym, start, end,thisTimeseries));
		InputStream in = address.openStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder result = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			result.append(line).append("\n");
		}

		JSONArray object = new JSONArray(result.toString());
		JSONObject stockObj;
		Iterator keys = object.iterator();
		double firstOpen;

		stockObj = new JSONObject(keys.next().toString());
		firstOpen = Double.parseDouble(stockObj.get("adjustedClose").toString());

		dataBuilder.append(stockObj.get("date")).append(",").append(Double.parseDouble(stockObj.get("adjustedClose").toString())/firstOpen).append(":");

		while (keys.hasNext()) {
			stockObj = new JSONObject(keys.next().toString());
			dataBuilder.append(stockObj.get("date")).append(",").append(Double.parseDouble(stockObj.get("adjustedClose").toString())/firstOpen).append(":");
		}

		return dataBuilder.toString().replaceAll("/", "-");

	}

    public ModelAndView getHello(String symbol, String startDate, String endDate, String timeseries, String chartType) throws IOException {
		this.thisTimeseries = timeseries;
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
			StringBuilder dataBuilder = new StringBuilder();
			StringBuilder dataVolumeBuilder = new StringBuilder();

			URL address = new URL(String.format("http://localhost:5000/stock/data/alldata?symbol=%s&startDate=%s&endDate=%s&timeseries=%s", symbol, startDate, endDate, thisTimeseries));
//			URL address = new URL(String.format("http://applestock.us-west-1.elasticbeanstalk.com/stock/data/alldata?symbol=%s&startDate=%s&endDate=%s&timeseries=%s", symbol, startDate, endDate, thisTimeseries));
			InputStream in = address.openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder result = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				result.append(line).append("\n");
			}

			JSONArray object = new JSONArray(result.toString());
			JSONObject stockObj;
			Iterator keys = object.iterator();

			if(chartType.equals("line")){
				while (keys.hasNext()) {
					stockObj = new JSONObject(keys.next().toString());
					dataBuilder.append(stockObj.get("date")).append(",").append(stockObj.get("adjustedClose")).append(":");
					dataVolumeBuilder.append(stockObj.get("date")).append(",").append(stockObj.get("volume")).append(":");
				}

			}else if(chartType.equals("candle")){
				while (keys.hasNext()) {
					stockObj = new JSONObject(keys.next().toString());
					double adjustedRatio = Double.parseDouble(stockObj.get("close").toString())/Double.parseDouble(stockObj.get("adjustedClose").toString());

					double open = Double.parseDouble(stockObj.get("open").toString())/adjustedRatio;
					double high = Double.parseDouble(stockObj.get("high").toString())/adjustedRatio;
					double low = Double.parseDouble(stockObj.get("low").toString())/adjustedRatio;

					dataBuilder.append(stockObj.get("date")).append(",").append(open).append(",").append(high).append(",").append(low).append(",").append(stockObj.get("adjustedClose")).append(":");
					dataVolumeBuilder.append(stockObj.get("date")).append(",").append(stockObj.get("volume")).append(":");
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
