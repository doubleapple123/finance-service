package io.myfunstuff.stocks.model.StockModels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StockStatistics {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String symbol;
	private String dataPointType;
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
	
	public StockStatistics(String symbol, String dataPointType) {
		this.symbol = symbol;
		this.dataPointType = dataPointType;
	}

	public Long getId(){
		return id;
	}

	public void setId(long id){
		this.id = id;
	}
	
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getDataPointType() {
		return dataPointType;
	}

	public void setDataPointType(String dataPointType) {
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
