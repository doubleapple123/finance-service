package io.myfunstuff.stocks.model;

import java.util.ArrayList;

public class StockFullTimeData {
    private String date;
    private double open;
    private double high;
    private double low;
    private double volume;

    public StockFullTimeData(ArrayList<Object> inData){
        this.date = inData.get(1).toString();
        this.open = Double.parseDouble(inData.get(2).toString());
        this.high = Double.parseDouble(inData.get(3).toString());
        this.low = Double.parseDouble(inData.get(4).toString());
        this.volume = Double.parseDouble(inData.get(5).toString());
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

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
}
