package io.myfunstuff.stocks.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
public class TimeSeriesDataCollection {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String symbol;
	private TimeSeriesType type;
	private Map<Date, TimeSeriesData> timeSeries;

	public TimeSeriesDataCollection(String symbol, TimeSeriesType type) {
		this.symbol = symbol;
		this.type = type;
		this.timeSeries = new HashMap<Date, TimeSeriesData>();
	}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
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
