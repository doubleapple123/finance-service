package io.myfunstuff.stocks.service.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.myfunstuff.stocks.model.Stuff;

@Path("/fun")
@Produces({MediaType.APPLICATION_JSON})
public interface MyStuffService {
	
	@GET
	@Path("/test")
	public Stuff test(
			@QueryParam(value = "project")
			String project,
			@QueryParam(value = "service")
			String service);
}
