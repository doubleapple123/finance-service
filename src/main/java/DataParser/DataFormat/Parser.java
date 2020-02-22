package DataParser.DataFormat;

import org.apache.wink.json4j.JSONException;
import org.apache.wink.json4j.OrderedJSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

//class used to parse raw json data from public api
//methods used for adding onto finalParsed instance variable
//different tables for daily and weekly data
public class Parser {
    private StringBuilder finalParsed; //FINAL DATA -- important
    private String dailyURLFormat = "https://www.alphavantage.co/query?function=%s&symbol=%s&outputsize=%s&apikey=728C9KPM2IY7IVDZ"; //daily or weekly, symbol
    private String weeklyURLFormat = "https://www.alphavantage.co/query?function=%s&symbol=%s&apikey=728C9KPM2IY7IVDZ";
    private String symbol;
    private String timeseries;
    private String outputsize;

    public Parser(String symbol, String timeseries){
        this.symbol = symbol;
        this.timeseries = timeseries;
        this.outputsize = "full";
        finalParsed = new StringBuilder();
    }

    public void setOutputsize(String size){
        this.outputsize = size;
    }

    public String getOutputsize(){
        return outputsize;
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

    public StringBuilder getFinalParsed(){
        return this.finalParsed;
    }

    //gets raw json string

    public String retrieveData() throws IOException{
        URL address;
        switch(timeseries){
            case "TIME_SERIES_DAILY" :
            case "TIME_SERIES_DAILY_ADJUSTED" :
                address = new URL(String.format(dailyURLFormat, timeseries, symbol, outputsize)); break;

            case "TIME_SERIES_WEEKLY" : address = new URL(String.format(weeklyURLFormat, timeseries, symbol)); break;


            default: throw new IllegalArgumentException();
        }

        InputStream in = address.openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null){
            result.append(line).append("\n");
        }
        return result.toString();
    }

    public void formatData(String dataRow){
        dataRow = dataRow.replaceAll("/[^A-Za-z0-9]/", "").replaceAll(":", "");

        //this block formats and removes all unnecessary characters
        String[] arr = dataRow.split("\"");
        List<String> list = new ArrayList<>(Arrays.asList(arr));
        list.removeAll(Arrays.asList("", null, ",", "{", "}"));
        for(int i = 1; i < list.size(); i+=2){
            finalParsed.append(", " + list.get(i));
        }
        finalParsed.append("),\n");
    }
    //returns parsed map object

    public void parseData() throws IOException, JSONException {
        String data = retrieveData();
        String timeObj;

        OrderedJSONObject jsonObject = new OrderedJSONObject(data);
        Iterator jsonIte = jsonObject.getOrder();
        jsonIte.next();

        timeObj = jsonObject.get(jsonIte.next().toString()).toString();
        OrderedJSONObject timeJson = new OrderedJSONObject(timeObj);
        Iterator iterator = timeJson.getOrder();
        Object key;

        while(iterator.hasNext()){
            key = iterator.next();
            finalParsed.append("(");
            finalParsed.append(String.format("'%s', '%s'", getSymbol(), key.toString()));
            formatData((timeJson.get(key).toString()));
        }
    }
}
