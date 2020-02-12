package io.myfunstuff.stocks.service.rs;

import io.myfunstuff.stocks.model.StockStatistics;
import io.myfunstuff.stocks.model.TimeSeriesType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/stock", produces = {"application/json"})
public interface StockService {

	@RequestMapping(path = "/analyze", method = RequestMethod.GET)
	StockStatistics analyzeTimeSeriesData(
			@RequestParam(value = "symbol", defaultValue = "MSFT") String symbol,
			@RequestParam(value = "startDate", defaultValue = "1999-01-01") String startDate,
			@RequestParam(value = "endDate", defaultValue = "2100-01-01") String endDate,
			@RequestParam(value = "timeseries", defaultValue = "DAILY")TimeSeriesType timeSeriesType //currently not implemented

//			@DefaultValue("false") @QueryParam("size") boolean dataSize
	);

//	@GET
//	@Path("/getAnalysis")
//	TechnicalDetails getAnalysis(
//			@QueryParam("type") String techAnalysis,
//			@QueryParam("id") Long id
//	);
}
