package io.myfunstuff.stocks.model;

public class StockTimeData{
	private String date;
	private Float open;

	public StockTimeData(String date, Float open){
		this.date = date;
		this.open = open;
	}

	public String getDate(){
		return date;
	}

	public void setDate(String date){
		this.date = date;
	}

	public Float getOpen(){
		return open;
	}

	public void setOpen(Float open){
		this.open = open;
	}
}
