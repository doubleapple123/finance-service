package DataParser;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

public class Parser {
    private String urlFormat = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=%s&apikey=728C9KPM2IY7IVDZ";
    private String fullUrlFormat = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=%s&outputsize=full&apikey=728C9KPM2IY7IVDZ";
    private boolean full;
    private String symbol;

    public Parser(){
        this.full = false;
        this.symbol = "SPY";
    }

    public Parser(String symbol, boolean full){
        this.symbol = symbol;
        this.full = full;
    }

    public String getSymbol(){
        return this.symbol;
    }

    public String retrieveData() throws IOException {
        URL address;

        if(full){
            address = new URL(String.format(fullUrlFormat,symbol));
        }else{
            address = new URL(String.format(urlFormat,symbol));
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

    public Map<String, Object> parseData() throws IOException {
        JSONObject jsonOb = new JSONObject(retrieveData());
        String timeObj = jsonOb.get("Time Series (Daily)").toString();

        JSONObject timeJson = new JSONObject(timeObj);
        return timeJson.toMap();
    }
}
