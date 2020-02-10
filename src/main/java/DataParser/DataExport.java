package DataParser;

import org.apache.wink.json4j.JSONException;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;


public class DataExport {
    public StockModel getData() throws IOException, ParseException, NoSuchFieldException, IllegalAccessException, JSONException {
        Parser parser = new Parser("MSFT", false);
        StockModel model = new StockModel(parser.getSymbol());

        Map<String, Object> stockData = parser.parseData();
        model.addStockData(stockData);

        return model;
    }

    public String updateDatabase() throws IOException, ParseException, NoSuchFieldException, IllegalAccessException, JSONException {
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(3);
        StringBuilder stringBuilder = new StringBuilder();

        StockModel stockModel = getData();
        Map<LocalDate, ArrayList<Double>> outMap = stockModel.getSymbolData();
        //"'MSFT','2020-03-01', 100, 200, 300, 400, 500";
        for(Map.Entry<LocalDate, ArrayList<Double>> entry : outMap.entrySet()){
            stringBuilder.append("(");
            stringBuilder.append("'" + stockModel.getSymbol() + "'," );
            stringBuilder.append("'" + entry.getKey().toString() + "'");


            for(Double arrayValues : entry.getValue()){
                stringBuilder.append(", " + df.format(arrayValues));
            }
            stringBuilder.append("),\n");

        }

        stringBuilder.deleteCharAt(stringBuilder.toString().length()-1);
        stringBuilder.deleteCharAt(stringBuilder.toString().length()-1);

        return stringBuilder.toString();
    }
}
