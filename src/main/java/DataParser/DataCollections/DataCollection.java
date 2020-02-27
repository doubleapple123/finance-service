package DataParser.DataCollections;

import DataParser.DBQueries.QueryExecute;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

//meant to represent data in the database
public class DataCollection<T>{

	//represents a row of data
	private String timeseries;
	private String symbol;
	private String startDate;
	private String endDate;
	private ArrayList<T> dataRow;

	public DataCollection(String timeseries, String symbol, String startDate, String endDate){
		this.timeseries = timeseries;
		this.symbol = symbol;
		this.startDate = startDate;
		this.endDate = endDate;
		dataRow = new ArrayList<>();
	}

	//wtf is happening here? it works though
	public void convertArr(Class myClass) {
		try{
			QueryExecute query = new QueryExecute();
			query.setTimeser(getTimeseries());
			query.getTableFromSymbol(getSymbol(), getStartDate(), getEndDate());

			for(ArrayList<Object> row : query.executeQuery()){
				addObject((T) myClass.getConstructor(ArrayList.class).newInstance(row));
			}

			query.closeConnection();

		}catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e){
			e.printStackTrace();
		}

	}

	public void addObject(T t){
		this.dataRow.add(t);
	}

	public ArrayList<T> getDataRow(){
		return this.dataRow;
	}

	public String getSymbol(){
		return symbol;
	}

	public void setSymbol(String symbol){
		this.symbol = symbol;
	}

	public String getStartDate(){
		return startDate;
	}

	public void setStartDate(String startDate){
		this.startDate = startDate;
	}

	public String getEndDate(){
		return endDate;
	}

	public void setEndDate(String endDate){
		this.endDate = endDate;
	}

	public String getTimeseries(){
		return timeseries;
	}

	public void setTimeseries(String timeseries){
		this.timeseries = timeseries;
	}
}
