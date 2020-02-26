package io.myfunstuff.stocks.scheduled;
import DataParser.DBQueries.QueryExecute;
import DataParser.DBQueries.QueryUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	@Async
	@Scheduled(fixedRate = 60000)
	public void addSymbol(){
		QueryUpdate queryUpdate = new QueryUpdate();
		symIte = symbolQue.iterator();
		if(symIte.hasNext()){
			Object sym = symIte.next();
			queryUpdate.addNotExist(sym.toString(),"compact");
			log.info("The symbol added to database {} ", sym.toString());
			symIte.remove();
			queryUpdate.closeConnection();
		}

	}

	@Async
	@Scheduled(fixedRate = 43200000)
	public void addToQueueDaily(){
		QueryExecute queryExecute = new QueryExecute();

		queryExecute.getDistinctSymbols();
		ArrayList<ArrayList<Object>> symbols = queryExecute.executeQuery();

		for(ArrayList<Object> obj : symbols){
			symbolQue.add(obj.get(0).toString());
			log.info("The symbol added to que is {}", obj.get(0).toString());
		}

		queryExecute.closeConnection();
	}
}
