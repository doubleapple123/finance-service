package io.myfunstuff.stocks.model;

public class StockTimeData{
//	private Map<String, Float> stockMap = new LinkedHashMap<>();
	private String date;
	private Float adjustedClose;

	public StockTimeData(String date, Float adjustedClose){
		this.date = date;
		this.adjustedClose = adjustedClose;
	}

//	public Map<String, Float> getStockMap() {
//		return stockMap;
//	}
//
//	public void setStockMap(Map<String, Float> stockMap) {
//		this.stockMap = stockMap;
//	}

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
