package io.myfunstuff.stocks.model;

import DataParser.DBQueries.Queries;

import java.util.ArrayList;

public class StockTimeDataCollection<T>{
	private String symbol;
	private String startDate;
	private String endDate;
	private ArrayList<ArrayList<Object>> stockTimeData;

	public StockTimeDataCollection(String symbol, String startDate, String endDate){
		this.symbol = symbol;
		this.startDate = startDate;
		this.endDate = endDate;
		stockTimeData = new ArrayList<>();
		Queries queries = new Queries();
		queries.setQuery(String.format("SELECT date, open FROM stockdata where `stock_symbol` = '%s' and " +
				"`date` between '%s' and '%s'", symbol, startDate, endDate));

		stockTimeData = queries.executeQuery();
	}

	public String getSymbol(){
		return symbol;
	}

	public void setSymbol(String symbol){
		this.symbol = symbol;
	}

	public String getStartDate(){
		return startDate;
	}

	public void setStartDate(String startDate){
		this.startDate = startDate;
	}

	public String getEndDate(){
		return endDate;
	}

	public void setEndDate(String endDate){
		this.endDate = endDate;
	}

	public ArrayList getStockTimeData(){
		return stockTimeData;
	}
}
