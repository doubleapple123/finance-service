package io.myfunstuff.stocks.model;


public class StockTimeData{
	private String date;
	private float open;

	public StockTimeData(String date, float open){
		this.date = date;
		this.open = open;
	}

	public String getDate(){
		return date;
	}

	public void setDate(String date){
		this.date = date;
	}

}
