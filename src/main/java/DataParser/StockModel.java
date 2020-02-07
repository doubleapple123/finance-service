package DataParser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StockModel implements Comparable<StockModel>{
    private String symbol;
    private int low, volume, open, high, close;

    //low, volume, open, high, close
    private Map<Date, ArrayList<Double>> symbolData; // data of a date and its correlated stockData
    private Date timeDate;
    private DateFormat format = new SimpleDateFormat("y-M-d", Locale.ENGLISH);

    public StockModel(String symbol){
        this.symbol = symbol;
    }

    //setters

    public void setSymbol(String symbol){
        this.symbol = symbol;
    }

    public void setTimeDate(String date) throws ParseException {
        timeDate = format.parse(date);
    }

    //functionality

    public void addStockData(Map<String, Object> dataMap) throws ParseException {
        for(Map.Entry<String, Object> keys : dataMap.entrySet()){
            System.out.println(keys.getKey());
            setTimeDate(keys.getKey());
            String [] priceData = parseArray(keys.getValue().toString().split(","));
//            System.out.println("TEST  " + Arrays.toString(priceData));
            ArrayList<Double> priceArr = new ArrayList<>();

            String [] finArr = new String[2];
            for(String pricePoint : priceData){
                finArr = pricePoint.split("=");
                System.out.println(Arrays.toString(finArr));

                priceArr.add(Double.parseDouble(finArr[1]));
            }
            System.out.println(getTimeDate());
            symbolData.put(getTimeDate(), priceArr);
            priceArr.clear();

        }

        System.out.println(symbolData.toString());
    }

    public String[] parseArray(String [] arr){
        String[] newArr = new String[arr.length];
        for(int i = 0; i < arr.length; i++){
            newArr[i] = arr[i].substring(4).replaceAll("}", "");
//            System.out.println("new arr " + i + "    " + newArr[i]);

        }

       return newArr;
    }

    //getters
    public String getSymbol(){
        return symbol;
    }

    public Date getTimeDate(){
        String date = "";
        return timeDate;
    }

    @Override
    public int compareTo(StockModel o) {
        return getTimeDate().compareTo(o.getTimeDate());
    }
}
