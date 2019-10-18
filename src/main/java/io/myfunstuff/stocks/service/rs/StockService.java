package io.myfunstuff.stocks.service.rs;

import javax.persistence.Id;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import io.myfunstuff.stocks.model.StockStatistics;
import io.myfunstuff.stocks.model.TimeSeriesType;
import io.myfunstuff.stocks.service.technicalFunctions.TechnicalDetails;
import io.myfunstuff.stocks.service.technicalFunctions.TrendLine;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

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

	@POST
	@Path("/save")
	String addStockData(
			@DefaultValue("MSFT") @QueryParam("symbol")	String symbol,
			@DefaultValue("DAILY") @QueryParam("timeseries") TimeSeriesType timeseriesType,
			@DefaultValue("10") @QueryParam("size") int dataSize
	);

	@GET
	@Path("/getAllStat")
	Iterable<StockStatistics> getAllStat();

	@GET
	@Path("/getStat")
	StockStatistics getStatistics(
//			@DefaultValue("MSFT") @QueryParam("symbol")	String symbol,
//			@DefaultValue("DAILY") @QueryParam("timeseries") TimeSeriesType timeseriesType,
//			@DefaultValue("10") @QueryParam("size") int dataSize

			@QueryParam("id") Long id
	);

	@GET
	@Path("/getAnalysis")
	TechnicalDetails getAnalysis(
			@QueryParam("type") String techAnalysis,
			@QueryParam("id") Long id
	);
}
