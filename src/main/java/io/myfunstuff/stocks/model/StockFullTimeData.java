package io.myfunstuff.stocks.model;

import java.util.ArrayList;

public class StockFullTimeData {
    private String date;
    private double open;
    private double high;
    private double low;
    private double close;
    private double volume;

    public StockFullTimeData(String date, ArrayList<Object> inData){
        this.date = date;
        this.open = Double.parseDouble(inData.get(0).toString());
        this.high = Double.parseDouble(inData.get(1).toString());
        this.low = Double.parseDouble(inData.get(2).toString());
        this.close = Double.parseDouble(inData.get(3).toString());
        this.volume = Double.parseDouble(inData.get(4).toString());

    }

    public StockFullTimeData(ArrayList<Object> inData){
        this.date = inData.get(1).toString();
        this.open = Double.parseDouble(inData.get(2).toString());
        this.high = Double.parseDouble(inData.get(3).toString());
        this.low = Double.parseDouble(inData.get(4).toString());
        this.close = Double.parseDouble(inData.get(5).toString());
        this.volume = Double.parseDouble(inData.get(6).toString());
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose(){
        return close;
    }

    public void setClose(double close){
        this.close = close;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public String toString(){
        return String.format("'%s', %f, %f, %f, %f, %f", date, open, high, low, close, volume);
    }
}
