package DataParser;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.Map;

public class Parser {
    private static String urlFormat = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=%s&apikey=728C9KPM2IY7IVDZ";
    private static String fullUrlFormat = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=%s&outputsize=full&apikey=728C9KPM2IY7IVDZ";

    public static void main(String[] args) throws ParseException {
        String sym = "MSFT";
        StockModel model = new StockModel(sym);
//        System.out.println(model.getTimeDate().toString());

        model.addStockData(parseData(getData(sym)));
        model.printMap();
    }

    public static String getData(String symbol){
        urlFormat = String.format(urlFormat, symbol);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(urlFormat, String.class);

        return response.toString();
    }

    public static Map<String, Object> parseData(String rawData){
        rawData = rawData.substring(5,rawData.length()-243);
        JSONObject jsonOb = new JSONObject(rawData);

        String timeObj = jsonOb.get("Time Series (Daily)").toString();

        JSONObject timeJson = new JSONObject(timeObj);
        return timeJson.toMap();
    }
}
