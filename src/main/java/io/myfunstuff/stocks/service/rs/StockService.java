package io.myfunstuff.stocks.service.rs;

import io.myfunstuff.stocks.model.StockStatistics;
import io.myfunstuff.stocks.model.TimeSeriesType;
import io.myfunstuff.stocks.service.technicalFunctions.TechnicalDetails;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/stock")
@Produces({MediaType.APPLICATION_JSON})
public interface StockService {

	@GET
	@Path("/analyze")
	StockStatistics analyzeTimeSeriesData(
			@DefaultValue("MSFT") @QueryParam("symbol")String symbol,
			@DefaultValue("DAILY") @QueryParam("timeseries") TimeSeriesType timeseriesType,
			@DefaultValue("10") @QueryParam("size") int dataSize
	);

	@GET
	@Path("/getAllStat")
	Iterable<StockStatistics> getAllStat();

	@GET
	@Path("/getAnalysis")
	TechnicalDetails getAnalysis(
			@QueryParam("type") String techAnalysis,
			@QueryParam("id") Long id
	);
}
