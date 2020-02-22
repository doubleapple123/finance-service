package DataParser.DataCollections;

import DataParser.DBQueries.QueryExecute;
import io.myfunstuff.stocks.model.StockAdjustedDaily;

import java.util.ArrayList;

public class StockAdjustedDailyDC extends DataCollection{
	public StockAdjustedDailyDC(String timeseries, String symbol, String startDate, String endDate){
		super(timeseries, symbol, startDate, endDate);
	}

	public void convertArr(){
		QueryExecute query = new QueryExecute();
		String curTable = "";
		query.setTimeser(getTimeseries());
		query.getTableFromSymbol(getSymbol(), getStartDate(), getEndDate());

		StockAdjustedDaily data;

		for(ArrayList<Object> row : query.executeQuery()){
			data = new StockAdjustedDaily(row);
			addObject(data);
		}
	}
}
