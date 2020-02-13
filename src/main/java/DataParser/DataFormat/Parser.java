package DataParser.DataFormat;

import org.apache.wink.json4j.JSONException;
import org.apache.wink.json4j.OrderedJSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

//class used to parse raw json data from public api

//different tables for daily and weekly data
public class Parser {
    private String dailyURLFormat = "https://www.alphavantage.co/query?function=%s&symbol=%s&outputsize=&apikey=728C9KPM2IY7IVDZ"; //daily or weekly, symbol
    private String weeklyURLFormat = "https://www.alphavantage.co/query?function=%s&symbol=%s&apikey=728C9KPM2IY7IVDZ";
    private String symbol;
    private String timeseries;

    public Parser(String symbol, String timeseries){
        this.symbol = symbol;
        this.timeseries = timeseries;
    }

    public String getTimeseries(){
        return this.timeseries;
    }

    public void setTimeseries(String timeseries){
        this.timeseries = timeseries;
    }

    public String getSymbol(){
        return this.symbol;
    }

    //gets raw json string

    public String retrieveData() throws IOException {
        URL address;

        if(timeseries.equals("TIME_SERIES_DAILY")){
            address = new URL(String.format(dailyURLFormat, "TIME_SERIES_DAILY", symbol));
        }else{
            address = new URL(String.format(weeklyURLFormat, "TIME_SERIES_WEEKLY", symbol));
        }

        InputStream in = address.openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
            result.append(line).append("\n");
        }

        return result.toString();
    }

    //returns parsed map object

    public Map<String, Object> parseData() throws IOException, JSONException {
        String data = retrieveData();

        OrderedJSONObject jsonObject = new OrderedJSONObject(data);
        String timeObj = jsonObject.get("Time Series (Daily)").toString();
        OrderedJSONObject timeJson = new OrderedJSONObject(timeObj);

        Iterator iterator = timeJson.getOrder();
        Map<String, Object> objectMap = new LinkedHashMap<>();
        Object key;

        while(iterator.hasNext()){
            key = iterator.next();
            objectMap.put(key.toString(), timeJson.get(key));
        }

        Object mapVal;

        //this is done because OrderedJSONObject formats the json data differently. I wrote the rest of the program
        //with unordered data so i had to format this data to look exactly the same as the one before to fit the algorithm
        //so everything works

        for(Map.Entry entry : objectMap.entrySet()){
            mapVal = entry.getValue();
            mapVal = mapVal.toString().replaceAll("\"", "");
            mapVal = mapVal.toString().replaceAll(":", " ");
            mapVal = mapVal.toString().replaceAll("open ", "open=");
            mapVal = mapVal.toString().replaceAll("high ", "high=");
            mapVal = mapVal.toString().replaceAll("low ", "low=");
            mapVal = mapVal.toString().replaceAll("close ", "close=");
            mapVal = mapVal.toString().replaceAll("volume ", "volume=");

            objectMap.replace(entry.getKey().toString(), mapVal);

        }

        return objectMap;
    }
}
