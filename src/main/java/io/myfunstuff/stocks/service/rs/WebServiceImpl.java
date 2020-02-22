package io.myfunstuff.stocks.service.rs;

import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class WebServiceImpl implements  WebService{
//
//	@Override
//	public String getIndex(String symbol, Model model) {
//		model.addAttribute("TESTING");
//		return "Hello test";
//	}
	public ModelAndView getHello(String symbol, String startDate, String endDate) throws IOException {
		StringBuilder dateBuilder = new StringBuilder();
		ModelAndView model = new ModelAndView("hello");
		ArrayList<String> dates = new ArrayList<>();
		ArrayList<Double> datas = new ArrayList<>();

		URL address = new URL(String.format("http://localhost:8080/stock/data?symbol=%s&startDate=%s&endDate=%s", symbol, startDate, endDate));
		InputStream in = address.openStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder result = new StringBuilder();
		String line;
		while((line = reader.readLine()) != null){
			result.append(line).append("\n");
		}
		JSONArray object = new JSONArray(result.toString());
		JSONObject stockObj;
		Iterator keys = object.iterator();

		while (keys.hasNext()) {
			stockObj = new JSONObject(keys.next().toString());
			dateBuilder.append(stockObj.get("date").toString() + ",");
			datas.add(Double.parseDouble(stockObj.get("open").toString()));
		}

		model.addObject("name", symbol);
		model.addObject("dates", dateBuilder);
		model.addObject("datas", datas);

		return model;
	}
}
