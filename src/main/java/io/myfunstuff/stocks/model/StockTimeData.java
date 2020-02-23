package io.myfunstuff.stocks.model;

public class StockTimeData{
	private String date;
	private Float adjustedClose;
	private Float volume;

	public StockTimeData(String date, Float adjustedClose, Float volume){
		this.volume = volume;
		this.date = date;
		this.adjustedClose = adjustedClose;
	}

	public Float getVolume() {
		return volume;
	}

	public void setVolume(Float volume) {
		this.volume = volume;
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
