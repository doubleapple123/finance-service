package DataParser.DataCollections;

import DataParser.DBQueries.Queries;
import io.myfunstuff.stocks.model.StockTimeData;

import java.util.ArrayList;

public class StockTimeDC extends DataCollection{
	public StockTimeDC(String timeseries, String symbol, String startDate, String endDate){
		super(timeseries, symbol, startDate, endDate);
	}

	public void convertArr(){
		Queries queries = new Queries();
		queries.setTimeser(this.getTimeseries());
		queries.setQuery(String.format("SELECT date, open FROM %s where `symbol` = '%s' and " +
				"`date` between '%s' and '%s'",queries.getDBtable(), getSymbol(), getStartDate(), getEndDate()));

		for(ArrayList<Object> row: queries.executeQuery()){
			StockTimeData stockTimeData = new StockTimeData(row.get(0).toString(), Float.parseFloat(row.get(1).toString()));
			addObject(stockTimeData);
		}
	}
}
