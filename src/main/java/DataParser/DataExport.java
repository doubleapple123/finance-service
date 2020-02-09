package DataParser;

import DataParser.DBConnector.Connector;
import org.apache.wink.json4j.JSONException;

import java.io.*;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;


/*
    plan for the future:
        - implement using mysql connector (jdbc) to directly write and get from local mysql server
        - set up spring usage of local mysql database
        - automate update of aws rds database using java or python
 */
public class DataExport {
    public static void main(String[] args) throws SQLException, IOException, ParseException, NoSuchFieldException, IllegalAccessException, JSONException {
        DataExport dataExport = new DataExport();
        Connector connector = new Connector();

        dataExport.updateDatabase(connector);

        ArrayList<ArrayList<Object>> table = connector.getTable();

        for(ArrayList<Object> row : table){
         //   System.out.println(row);
        }

        connector.closeConnections();

    }

    public StockModel getData() throws IOException, ParseException, NoSuchFieldException, IllegalAccessException, JSONException {
        Parser parser = new Parser("MSFT", false);
        StockModel model = new StockModel(parser.getSymbol());

        Map<String, Object> stockData = parser.parseData();
        model.addStockData(stockData);

        return model;
    }

    public void updateDatabase(Connector connector) throws IOException, ParseException, NoSuchFieldException, IllegalAccessException, JSONException {
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(3);
        StringBuilder stringBuilder;

        StockModel stockModel = getData();
        Map<LocalDate, ArrayList<Double>> outMap = stockModel.getSymbolData();
        //"'MSFT','2020-03-01', 100, 200, 300, 400, 500";
        for(Map.Entry<LocalDate, ArrayList<Double>> entry : outMap.entrySet()){
            stringBuilder = new StringBuilder();

            stringBuilder.append("'" + stockModel.getSymbol() + "'," );
            stringBuilder.append("'" + entry.getKey().toString() + "'");


            for(Double arrayValues : entry.getValue()){
                stringBuilder.append(", " + df.format(arrayValues));
            }

            connector.addToDatabase(stringBuilder.toString());
        }
    }
}
