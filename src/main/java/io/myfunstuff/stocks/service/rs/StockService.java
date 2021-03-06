package io.myfunstuff.stocks.service.rs;

import io.myfunstuff.stocks.model.StockModels.StockStatistics;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

@RestController
@RequestMapping(path = "/stock")
public interface StockService {
	//mapping returns one data point of analyzed data

	@RequestMapping(path = "/analyze", method = RequestMethod.GET, produces = {"application/json"})
	StockStatistics analyzeTimeSeriesData(
			@RequestParam(value = "timeseries", defaultValue = "TIME_SERIES_DAILY_ADJUSTED") String timeseries,
			@RequestParam(value = "symbol", defaultValue = "SPY") String symbol,
			@RequestParam(value = "startDate", defaultValue = "1999-01-01") String startDate,
			@RequestParam(value = "endDate", defaultValue = "2100-01-01") String endDate

	) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

	@RequestMapping(path = "/data/alldata", method = RequestMethod.GET, produces = {"application/json"})
	ArrayList getFullStockData(
			@RequestParam(value = "timeseries", defaultValue = "TIME_SERIES_DAILY_ADJUSTED") String timeseries,
			@RequestParam(value = "symbol", defaultValue = "SPY") String symbol,
			@RequestParam(value = "startDate", defaultValue = "1999-01-01") String startDate,
			@RequestParam(value = "endDate", defaultValue = "2100-01-01") String endDate
	) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

}
