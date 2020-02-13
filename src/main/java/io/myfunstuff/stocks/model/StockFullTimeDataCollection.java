package io.myfunstuff.stocks.model;

import java.util.ArrayList;

public class StockFullTimeDataCollection {
    private String symbol;
    private ArrayList<StockFullTimeData> dataList;

    public StockFullTimeDataCollection(String symbol){
        this.symbol = symbol;
        this.dataList = new ArrayList<>();
    }

    public ArrayList<StockFullTimeData> getDataList(){
        return this.dataList;
    }

    public void addToList(StockFullTimeData stockData){
        this.dataList.add(stockData);
    }


}
