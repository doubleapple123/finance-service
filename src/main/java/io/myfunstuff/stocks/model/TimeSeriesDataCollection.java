package io.myfunstuff.stocks.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TimeSeriesDataCollection {

	private String symbol;
	private TimeSeriesType type;
	private String startDate;
	private String endDate;
	private Map<Date, TimeSeriesData> timeSeries;

	public TimeSeriesDataCollection(String startDate, String endDate, String symbol, TimeSeriesType type) {
		this.symbol = symbol;
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
		this.timeSeries = new HashMap<Date, TimeSeriesData>();
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

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public TimeSeriesType getType() {
		return type;
	}

	public void setType(TimeSeriesType type) {
		this.type = type;
	}

	public Map<Date, TimeSeriesData> getTimeSeries() {
		return timeSeries;
	}

	public void setTimeSeries(Map<Date, TimeSeriesData> timeSeries) {
		this.timeSeries = timeSeries;
	}
	
	public void addTimeSeriesData(Date date, TimeSeriesData data) {
		timeSeries.put(date, data);
	}

}
