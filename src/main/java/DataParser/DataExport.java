package DataParser;

import DataParser.DBConnector.Connector;
import DataParser.DBConnector.ResultRow;

import java.io.*;
import java.sql.ResultSet;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/*
    plan for the future:
        - implement using mysql connector (jdbc) to directly write and get from local mysql server
        - automate update of aws rds database using java or python
 */
public class DataExport {

    public static void main(String[] args) throws IOException, ParseException {
//        DataExport dataExport = new DataExport();
//        dataExport.outputToFile();
//
        Connector connector = new Connector();

        ArrayList<ArrayList<Object>> table = connector.getTable();

        for(ArrayList<Object> row : table){
            System.out.println(row);
        }

    }

    public StockModel getData() throws IOException, ParseException {
        Parser parser = new Parser("MSFT", false);
        StockModel model = new StockModel(parser.getSymbol());

        Map<String, Object> stockData = parser.parseData();
        model.addStockData(stockData);

        return model;
    }

    public void outputToFile() throws IOException, ParseException {

        FileWriter fileWriter = new FileWriter("src/main/stockData.txt", false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(bufferedWriter);
        StockModel stockModel = getData();
        Map<LocalDate, ArrayList<Double>> outMap = stockModel.getSymbolData();

        for(Map.Entry<LocalDate, ArrayList<Double>> entry : outMap.entrySet()){
            printWriter.print(stockModel.getSymbol());
            printWriter.print(",");
            printWriter.print(entry.getKey().toString());
            printWriter.print(",");

            for(Double arrayValues : entry.getValue()){
                printWriter.printf("%.3f", arrayValues);
                printWriter.print(",");
            }

            printWriter.println();
        }

        printWriter.close();
    }
}
