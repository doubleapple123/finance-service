package io.myfunstuff.stocks.model.StockModels;

import java.util.ArrayList;

public class StockQuotes{
	private String symbol, latestTradingDay;
	private double open, high, low, price, volume, previousClose, changePrice, changePercent;

	public StockQuotes(ArrayList<Object> inData){
		this.symbol = inData.get(1).toString();
		this.open = Double.parseDouble(inData.get(2).toString());
		this.high = Double.parseDouble(inData.get(3).toString());
		this.low = Double.parseDouble(inData.get(4).toString());
		this.price = Double.parseDouble(inData.get(5).toString());
		this.volume = Double.parseDouble(inData.get(6).toString());
		this.latestTradingDay = inData.get(7).toString();
		this.previousClose = Double.parseDouble(inData.get(8).toString());
		this.changePrice = Double.parseDouble(inData.get(9).toString());
		this.changePercent = Double.parseDouble(inData.get(10).toString());

	}

	public String getSymbol(){
		return symbol;
	}

	public void setSymbol(String symbol){
		this.symbol = symbol;
	}

	public String getLatestTradingDay(){
		return latestTradingDay;
	}

	public void setLatestTradingDay(String latestTradingDay){
		this.latestTradingDay = latestTradingDay;
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

	public double getPrice(){
		return price;
	}

	public void setPrice(double price){
		this.price = price;
	}

	public double getVolume(){
		return volume;
	}

	public void setVolume(double volume){
		this.volume = volume;
	}

	public double getPreviousClose(){
		return previousClose;
	}

	public void setPreviousClose(double previousClose){
		this.previousClose = previousClose;
	}

	public double getChangePrice(){
		return changePrice;
	}

	public void setChangePrice(double changePrice){
		this.changePrice = changePrice;
	}

	public double getChangePercent(){
		return changePercent;
	}

	public void setChangePercent(double changePercent){
		this.changePercent = changePercent;
	}
}
