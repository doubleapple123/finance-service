//package DataParser.DataCollections;
//
//import DataParser.DBQueries.QueryExecute;
//import io.myfunstuff.stocks.model.StockModels.StockAdjustedDaily;
//import io.myfunstuff.stocks.model.StockModels.StockFullTimeData;
//
//import java.util.ArrayList;
//
//public class StockAdjustedDC extends DataCollection{
//	public StockAdjustedDC(String timeseries, String symbol, String startDate, String endDate){
//		super(timeseries, symbol, startDate, endDate);
//	}
//
//	public void convertArr(){
//		QueryExecute query = new QueryExecute();
//		String curTable = "";
//		query.setTimeser(getTimeseries());
//		query.getTableFromSymbol(getSymbol(), getStartDate(), getEndDate());
//
//		StockAdjustedDaily data;
//
//		for(ArrayList<Object> row : query.executeQuery()){
//			data = new StockAdjustedDaily(row);
//			addObject(data);
//		}
//
//		query.closeConnection();
//	}
//}
