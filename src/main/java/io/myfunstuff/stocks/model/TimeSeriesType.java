package io.myfunstuff.stocks.model;

public enum TimeSeriesType {
	DAILY("daily", "TIME_SERIES_DAILY", 1),
	WEEKLY("weekly", "TIME_SERIES_WEEKLY", 2),
	MONTHLY("monthly", "TIME_SERIES_MONTHLY", 3);
	
	private String name;
	private String function;
	private int value;
	
	private TimeSeriesType(String name, String function, int value) {
		this.name = name;
		this.function = function;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public String getFunction() {
		return function;
	}
	
	public int getValue() {
		return value;
	}
	
}
