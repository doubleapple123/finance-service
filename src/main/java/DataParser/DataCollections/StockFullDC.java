package DataParser.DataCollections;

import DataParser.DBQueries.Queries;
import io.myfunstuff.stocks.model.StockFullTimeData;

import java.util.ArrayList;

public class StockFullDC extends DataCollection{
	public StockFullDC(String timeseries, String symbol, String startDate, String endDate){
		super(timeseries, symbol, startDate, endDate);
	}

	public void convertArr(){
		Queries queries = new Queries();
		String curTable = "";
		queries.setTimeser(getTimeseries());
		queries.getTableFromSymbol(getSymbol(), getStartDate(), getEndDate());

		StockFullTimeData data;

		for(ArrayList<Object> row : queries.executeQuery()){
			data = new StockFullTimeData(row);
			addObject(data);
		}
	}
}
