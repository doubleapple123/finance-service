package io.myfunstuff.stocks.scheduled;

import DataParser.DBQueries.QueryUpdate;
import io.myfunstuff.stocks.PropertyValues;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class IntradayUpdate{
	public static final Logger log = LoggerFactory.getLogger(IntradayUpdate.class);

	@Autowired
	PropertyValues propertyValues;

	@Async
	@Scheduled(fixedDelay = 60000)
	public void updateIntraday(){
		QueryUpdate queryUpdate = new QueryUpdate(propertyValues);
	}
}
