package io.myfunstuff.stocks.service.rs;

import io.myfunstuff.stocks.model.StockStatistics;
import io.myfunstuff.stocks.model.TimeSeriesType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/stock")
public interface StockService {

	//mapping returns one data point of analyzed data
	@RequestMapping(path = "/analyze", method = RequestMethod.GET, produces = {"application/json"})
	StockStatistics analyzeTimeSeriesData(
			@RequestParam(value = "timeseries", defaultValue = "TIME_SERIES_DAILY") String timeseries,
			@RequestParam(value = "symbol", defaultValue = "SPY") String symbol,
			@RequestParam(value = "startDate", defaultValue = "1999-01-01") String startDate,
			@RequestParam(value = "endDate", defaultValue = "2100-01-01") String endDate,
			@RequestParam(value = "timeseries", defaultValue = "DAILY")TimeSeriesType timeSeriesType //currently not implemented

//			@DefaultValue("false") @QueryParam("size") boolean dataSize
	);

	//mapping returns list of datapoints and associated date. this will be used to plot data points on a graph.
	@RequestMapping(path = "/data", method = RequestMethod.GET, produces = {"application/json"})
	ArrayList getStockData(
			@RequestParam(value = "timeseries", defaultValue = "TIME_SERIES_DAILY") String timeseries,
			@RequestParam(value = "symbol", defaultValue = "SPY") String symbol,
			@RequestParam(value = "startDate", defaultValue = "1999-01-01") String startDate,
			@RequestParam(value = "endDate", defaultValue = "2100-01-01") String endDate
	);

	@RequestMapping(path = "/data/alldata", method = RequestMethod.GET, produces = {"application/json"})
	ArrayList getFullStockData(
			@RequestParam(value = "timeseries", defaultValue = "TIME_SERIES_DAILY") String timeseries,
			@RequestParam(value = "symbol", defaultValue = "SPY") String symbol,
			@RequestParam(value = "startDate", defaultValue = "1999-01-01") String startDate,
			@RequestParam(value = "endDate", defaultValue = "2100-01-01") String endDate
	);

//	@GET
//	@Path("/getAnalysis")
//	TechnicalDetails getAnalysis(
//			@QueryParam("type") String techAnalysis,
//			@QueryParam("id") Long id
//	);
}
