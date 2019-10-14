package io.myfunstuff.stocks.model;

public class StockStatistics {

	private String symbol;
	private TimeSeriesType dataPointType;
	private float high;
	private float low;
	
	private String periodStart;
	private String periodEnd;
	private String lowDate;
	private String highDate;

	public StockStatistics() {
	}

	public StockStatistics(String symbol) {
		this.symbol = symbol;
	}
	
	public StockStatistics(String symbol, TimeSeriesType dataPointType) {
		this.symbol = symbol;
		this.dataPointType = dataPointType;
	}
	
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public TimeSeriesType getDataPointType() {
		return dataPointType;
	}

	public void setDataPointType(TimeSeriesType dataPointType) {
		this.dataPointType = dataPointType;
	}

	public float getHigh() {
		return high;
	}

	public void setHigh(float high) {
		this.high = high;
	}

	public float getLow() {
		return low;
	}

	public void setLow(float low) {
		this.low = low;
	}

	public String getPeriodStart() {
		return periodStart;
	}

	public void setPeriodStart(String periodStart) {
		this.periodStart = periodStart;
	}

	public String getPeriodEnd() {
		return periodEnd;
	}

	public void setPeriodEnd(String periodEnd) {
		this.periodEnd = periodEnd;
	}

	public String getLowDate() {
		return lowDate;
	}

	public void setLowDate(String lowDate) {
		this.lowDate = lowDate;
	}

	public String getHighDate() {
		return highDate;
	}

	public void setHighDate(String highDate) {
		this.highDate = highDate;
	} 
	
	
}
