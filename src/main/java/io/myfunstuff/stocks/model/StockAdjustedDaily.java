package io.myfunstuff.stocks.model;

import java.util.ArrayList;

public class StockAdjustedDaily{
	private String date;
	private double open;
	private double high;
	private double low;
	private double close;
	private double adjustedClose;
	private double volume;
	private double dividend;
	private double splitco;

	public StockAdjustedDaily(ArrayList<Object> inData){
		this.date = inData.get(1).toString();
		this.open = Double.parseDouble(inData.get(2).toString());
		this.high = Double.parseDouble(inData.get(3).toString());
		this.low = Double.parseDouble(inData.get(4).toString());
		this.close = Double.parseDouble(inData.get(5).toString());
		this.adjustedClose = Double.parseDouble(inData.get(6).toString());
		this.volume = Double.parseDouble(inData.get(7).toString());
		this.dividend = Double.parseDouble(inData.get(8).toString());
		this.splitco = Double.parseDouble(inData.get(9).toString());
	}

	public String getDate(){
		return date;
	}

	public void setDate(String date){
		this.date = date;
	}

	public double getOpen(){
		return open;
	}

	public void setOpen(double open){
		this.open = open;
	}

	public double getHigh(){
		return high;
	}

	public void setHigh(double high){
		this.high = high;
	}

	public double getLow(){
		return low;
	}

	public void setLow(double low){
		this.low = low;
	}

	public double getClose(){
		return close;
	}

	public void setClose(double close){
		this.close = close;
	}

	public double getAdjustedClose(){
		return adjustedClose;
	}

	public void setAdjustedClose(double adjustedClose){
		this.adjustedClose = adjustedClose;
	}

	public double getVolume(){
		return volume;
	}

	public void setVolume(double volume){
		this.volume = volume;
	}

	public double getDividend(){
		return dividend;
	}

	public void setDividend(double dividend){
		this.dividend = dividend;
	}

	public double getSplitco(){
		return splitco;
	}

	public void setSplitco(double splitco){
		this.splitco = splitco;
	}
}
