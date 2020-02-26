package io.myfunstuff.stocks.service.rs;

import org.json.JSONArray;
import org.json.JSONObject;
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

	public String getData(String sym, String start, String end) throws IOException {
		StringBuilder dataBuilder = new StringBuilder();
		URL address = new URL(String.format("http://localhost:5000/stock/data/alldata?symbol=%s&startDate=%s&endDate=%s&timeseries=%s", sym, start, end, thisTimeseries));
//		URL address = new URL(String.format("http://stockscreener-env.applestock.us-west-1.elasticbeanstalk.com/stock/data?symbol=%s&startDate=%s&endDate=%s", sym, start, end));
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
		firstOpen = Double.parseDouble(stockObj.get("open").toString());

		dataBuilder.append(stockObj.get("date")).append(",").append(Double.parseDouble(stockObj.get("close").toString())/firstOpen).append(":");

		while (keys.hasNext()) {
			stockObj = new JSONObject(keys.next().toString());
			dataBuilder.append(stockObj.get("date")).append(",").append(Double.parseDouble(stockObj.get("close").toString())/firstOpen).append(":");
		}

		return dataBuilder.toString().replaceAll("/", "-");

	}

    public ModelAndView getHello(String symbol, String startDate, String endDate, String timeseries) throws IOException {
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
//			URL address = new URL(String.format("http://stockscreener-env.applestock.us-west-1.elasticbeanstalk.com/stock/data?symbol=%s&startDate=%s&endDate=%s", symbol, startDate, endDate));
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

			while (keys.hasNext()) {
				stockObj = new JSONObject(keys.next().toString());
				dataBuilder.append(stockObj.get("date")).append(",").append(stockObj.get("close")).append(":");
				dataVolumeBuilder.append(stockObj.get("date")).append(",").append(stockObj.get("volume")).append(":");
			}

			model.addObject("volDataColl", dataVolumeBuilder.toString().replaceAll("/", "-"));
			model.addObject("dataColl", dataBuilder.toString().replaceAll("/", "-"));
			model.addObject("timeseries", this.thisTimeseries);

		}

        model.addObject("name", symbol.toUpperCase());

        return model;
    }
}
