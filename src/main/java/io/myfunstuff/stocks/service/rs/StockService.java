package io.myfunstuff.stocks.service.rs;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import io.myfunstuff.stocks.model.StockStatistics;
import io.myfunstuff.stocks.model.TimeSeriesType;

@Path("/stock")
@Produces({MediaType.APPLICATION_JSON})
public interface StockService {

	@GET
	@Path("/analyze")
	StockStatistics analyzeTimeSeriesData(
			@DefaultValue("MSFT") @QueryParam("symbol")	String symbol,
			@DefaultValue("DAILY") @QueryParam("timeseries") TimeSeriesType timeseriesType,
			@DefaultValue("10") @QueryParam("size") int dataSize
	);

	@POST
	@Path("/save")
	void addStockData(
			@DefaultValue("MSFT") @QueryParam("symbol")	String symbol,
			@DefaultValue("DAILY") @QueryParam("timeseries") TimeSeriesType timeseriesType,
			@DefaultValue("10") @QueryParam("size") int dataSize
	);

}
