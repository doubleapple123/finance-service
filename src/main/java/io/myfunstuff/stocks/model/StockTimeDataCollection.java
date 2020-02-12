package io.myfunstuff.stocks.model;

import DataParser.DBQueries.Queries;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class StockTimeDataCollection{
	private String symbol;
	private String startDate;
	private String endDate;
	private ArrayList<StockTimeData> stockTimeDataList;

	public StockTimeDataCollection(String symbol, String startDate, String endDate){
		this.symbol = symbol;
		this.startDate = startDate;
		this.endDate = endDate;
		stockTimeDataList = new ArrayList<>();

		convertArr();

	}

	public void convertArr(){
		Queries queries = new Queries();
		queries.setQuery(String.format("SELECT date, open FROM stockdata where `stock_symbol` = '%s' and " +
				"`date` between '%s' and '%s'", symbol, startDate, endDate));

		for(ArrayList<Object> row: queries.executeQuery()){
			StockTimeData stockTimeData = new StockTimeData(row.get(0).toString(), Float.parseFloat(row.get(1).toString()));
			stockTimeDataList.add(stockTimeData);
		}

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

	public ArrayList<StockTimeData> getStockTimeData(){

		return this.stockTimeDataList;
	}
}
