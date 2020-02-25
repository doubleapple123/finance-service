//package DataParser.DataCollections;
//
//import DataParser.DBQueries.QueryExecute;
//import io.myfunstuff.stocks.model.StockModels.StockTimeData;
//
//import java.util.ArrayList;
//
//public class StockTimeDC extends DataCollection{
//	public StockTimeDC(String timeseries, String symbol, String startDate, String endDate){
//		super(timeseries, symbol, startDate, endDate);
//	}
//
//	public void convertArr(){
//		QueryExecute query = new QueryExecute();
//		query.setTimeser(this.getTimeseries());
//
//		query.setQuery(String.format("SELECT date, adjusted_close, volume FROM %s where `symbol` = '%s' and " +
//				"`date` between '%s' and '%s'",query.getDBtable(), getSymbol(), getStartDate(), getEndDate()));
//
//		for(ArrayList<Object> row: query.executeQuery()){
//			StockTimeData stockTimeData = new StockTimeData(row.get(0).toString(), Float.parseFloat(row.get(1).toString()), Float.parseFloat(row.get(2).toString()));
//			addObject(stockTimeData);
//		}
//
//		query.closeConnection();
//	}
//}
