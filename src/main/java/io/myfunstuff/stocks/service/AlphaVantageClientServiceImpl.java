//package io.myfunstuff.stocks.service;
//
//import io.myfunstuff.stocks.model.TimeSeriesType;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//@Service
//public class AlphaVantageClientServiceImpl implements AlphaVantageClientService {
//
////	private static final String URLTemplate = "https://www.alphavantage.co/query?function=%s&symbol=%s&outputsize=%d&apikey=%s";
//
//	@Value("${alphavantage.apikey}")
//	private String apiKey;
//
//	@Autowired
//	public AlphaVantageClientServiceImpl() {
//	}
//
//	private RestTemplate restTemplate = new RestTemplate();
//
////	@Override
////	public String retrieveTimeSeriesData(String symbol, TimeSeriesType timeseriesType, int dataSize) {
////		return symbol;
////	}
//
//
//}
