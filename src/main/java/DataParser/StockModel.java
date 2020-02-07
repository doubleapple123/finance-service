package DataParser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;

public class StockModel implements Comparable<StockModel>{
    private String symbol;
    private int low, volume, open, high, close;

    //low, volume, open, high, close
    private Map<LocalDate, ArrayList<Double>> symbolData; // data of a date and its correlated stockData
    private LocalDate timeDate;
    private DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(Locale.ENGLISH);

    public StockModel(String symbol){
        this.symbol = symbol;
        symbolData = new TreeMap<>();
    }

    //setters

    public void setSymbol(String symbol){
        this.symbol = symbol;
    }

    public void setTimeDate(String date) throws ParseException {
        timeDate = LocalDate.parse(date, format);
    }

    //functionality

    public void addStockData(Map<String, Object> dataMap) throws ParseException {
        for(Map.Entry<String, Object> keys : dataMap.entrySet()){
            setTimeDate(keys.getKey());
            String [] priceData = parseArray(keys.getValue().toString().split(","));
            ArrayList<Double> priceArr = new ArrayList<>();

            String [] finArr;
            for(String pricePoint : priceData){
                finArr = pricePoint.split("=");
                Double value = Double.parseDouble(finArr[1]);
                priceArr.add(value);
            }
            symbolData.put(getTimeDate(), priceArr);

        }
    }

    public void printMap(){
        for(Map.Entry<LocalDate, ArrayList<Double>> listEntry: symbolData.entrySet()){
            System.out.println(listEntry.getKey() + " " + listEntry.getValue());
        }
    }

    public String[] parseArray(String [] arr){
        String[] newArr = new String[arr.length];
        for(int i = 0; i < arr.length; i++){
            newArr[i] = arr[i].substring(4).replaceAll("}", "");
        }

       return newArr;
    }

    //getters
    public String getSymbol(){
        return symbol;
    }

    public LocalDate getTimeDate(){
        return timeDate;
    }

    @Override
    public int compareTo(StockModel o) {
        return getTimeDate().compareTo(o.getTimeDate());
    }
}
