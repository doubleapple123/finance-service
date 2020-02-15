package io.myfunstuff.stocks.model;

public class StockTimeData{
	private String date;
	private Float adjustedClose;

	public StockTimeData(String date, Float adjustedClose){
		this.date = date;
		this.adjustedClose = adjustedClose;
	}

	public String getDate(){
		return date;
	}

	public void setDate(String date){
		this.date = date;
	}

	public Float getOpen(){
		return adjustedClose;
	}

	public void setOpen(Float open){
		this.adjustedClose = open;
	}
}
