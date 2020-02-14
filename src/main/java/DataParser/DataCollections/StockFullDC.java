package DataParser.DataCollections;

import DataParser.DBQueries.QueryExecute;
import io.myfunstuff.stocks.model.StockFullTimeData;

import java.util.ArrayList;

public class StockFullDC extends DataCollection{
	public StockFullDC(String timeseries, String symbol, String startDate, String endDate){
		super(timeseries, symbol, startDate, endDate);
	}

	public void convertArr(){
		QueryExecute query = new QueryExecute();
		String curTable = "";
		query.setTimeser(getTimeseries());
		query.getTableFromSymbol(getSymbol(), getStartDate(), getEndDate());

		StockFullTimeData data;

		for(ArrayList<Object> row : query.executeQuery()){
			data = new StockFullTimeData(row);
			addObject(data);
		}
	}
}
