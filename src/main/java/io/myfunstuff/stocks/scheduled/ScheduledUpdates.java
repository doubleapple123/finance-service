package io.myfunstuff.stocks.scheduled;
import DataParser.DBQueries.QueryExecute;
import DataParser.DBQueries.QueryUpdate;
import io.myfunstuff.stocks.PropertyValues;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

@Component
public class ScheduledUpdates{
	public static final Logger log = LoggerFactory.getLogger(ScheduledUpdates.class);
	private LinkedHashSet symbolQue = new LinkedHashSet();
	private Iterator<?> symIte;
	@Autowired
	PropertyValues propertyValues;

	@Async
	@Scheduled(fixedDelay = 100000)
	public void addSymbol(){
		QueryUpdate queryUpdate = new QueryUpdate(propertyValues);
		symIte = symbolQue.iterator();
		if(symIte.hasNext()){
			Object sym = symIte.next();
			queryUpdate.addScheduled(sym.toString());
			log.info("The symbol added to database {}", sym.toString());
			symIte.remove();
			queryUpdate.closeConnection();
		}
	}

	@Async
	@Scheduled(fixedRate = 43200000)
	public void addToQueueDaily(){
		QueryExecute queryExecute = new QueryExecute(propertyValues);

		queryExecute.getDistinctSymbols();
		ArrayList<ArrayList<Object>> symbols = queryExecute.executeQuery();

		for(ArrayList<Object> obj : symbols){
			symbolQue.add(obj.get(0).toString());
			log.info("The symbol added to que is {}", obj.get(0).toString());
		}

		queryExecute.closeConnection();
	}
}
