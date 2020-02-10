package DataParser.DBConnector;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.*;

public class ResultRow {
    private Map<Object, String> row;
    private Map<Object, Class> type;

    public ResultRow(){
        addTypes();
        row = new LinkedHashMap<>();
    }

    public void addTypes(){
        type = new HashMap<>();
        type.put("CHAR", String.class);
        type.put("VARCHAR", String.class);
        type.put("LONGVARCHAR", String.class);
        type.put("NUMERIC", BigDecimal.class);
        type.put("DECIMAL", BigDecimal.class);
        type.put("BIT", Boolean.class);
        type.put("TINYINT", Byte.class);
        type.put("SMALLINT", Short.class);
        type.put("INTEGER", Integer.class);
        type.put("BIGINT", Long.class);
        type.put("REAL", Float.class);
        type.put("FLOAT", Double.class);
        type.put("DOUBLE", Double.class);
        type.put("BINARY", byte[].class);
        type.put("VARBINARY", byte[].class);
        type.put("LONGARBINARY", byte[].class);
        type.put("DATE", Date.class);
        type.put("TIME", Time.class);
        type.put("TIMESTAMP", Timestamp.class);
        type.put("TEXT", String.class);
    }

    public ArrayList<ArrayList<Object>> convertType(List<Map<Object, String>> table){
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(3);

        ArrayList<ArrayList<Object>> tableConverted = new ArrayList<>();
        ArrayList<Object> convertedRow;

        for(Map<Object, String> row : table){
            convertedRow = new ArrayList<>();

            for(Map.Entry entry : row.entrySet()){
                Class valObject = type.get(entry.getValue());

                if(valObject.equals(Double.class)){
                    convertedRow.add(df.format(entry.getKey()));
                }else{
                    convertedRow.add(valObject.cast(entry.getKey()));
                }

            }
            tableConverted.add(convertedRow);
        }

        return tableConverted;
    }

    public List<Map<Object, String>> add(ResultSet set){

        ArrayList<Map<Object, String>> table = new ArrayList<>();
        int colCount;

        try {

            ResultSetMetaData metaData = set.getMetaData();
            colCount = metaData.getColumnCount();

            if(colCount == 0){
                System.out.println("Table has no columns");
                return null;
            }

            while(set.next()) {
                row = new LinkedHashMap<>();

                for (int i = 1; i <= colCount; i++) {
                    row.put(set.getObject(i), metaData.getColumnTypeName(i));
                }
                table.add(row);
            }

            return table;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
