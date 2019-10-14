package io.myfunstuff.stocks;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.myfunstuff.stocks.service.rs.MyStuffServicImpl;
import io.myfunstuff.stocks.service.rs.StockServiceImpl;

@Component
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {
	
	@Autowired
	public JerseyConfig() {
		register(MyStuffServicImpl.class);
		register(StockServiceImpl.class);
	}
}
