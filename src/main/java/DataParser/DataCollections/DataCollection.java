package DataParser.DataCollections;

import java.util.ArrayList;

//meant to represent data in the database
public abstract class DataCollection<T>{

	//TODO fix start and end date, currently all dates is shifted by one
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
