package io.myfunstuff.stocks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.myfunstuff.stocks.model.TimeSeriesType;

@Service
public class AlphaVantageClientServiceImpl implements AlphaVantageClientService {

	private static final String URLTemplate = "https://www.alphavantage.co/query?function=%s&symbol=%s&outputsize=%d&apikey=%s";
	
	@Value("${alphavantage.apikey}")
	private String apiKey;
	
	@Autowired
	public AlphaVantageClientServiceImpl() {
	}
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public String retrieveTimeSeriesData(String symbol, TimeSeriesType timeseriesType, int dataSize) {
		String url = String.format(URLTemplate, timeseriesType.getFunction(), symbol, dataSize, apiKey);
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		return response.getBody();
	}

	
}
