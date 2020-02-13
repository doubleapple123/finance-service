package io.myfunstuff.stocks.model;

public class StockFullTimeData {
    private String date;
    private double open;
    private double high;
    private double low;
    private double volume;

    public StockFullTimeData(String date, double open, double high, double low, double volume){
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.volume = volume;
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
