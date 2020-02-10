package DataParser.DataFormat;

import DataParser.StockModels.StockModel;
import org.apache.wink.json4j.JSONException;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataFormatOut{

	//used in conjuction with method below
	//converts mysql data type to java objects
	public ArrayList<ArrayList<Object>> convertType(List<Map<Object, String>> table){
		DecimalFormat df = new DecimalFormat("#");
		df.setMaximumFractionDigits(3);

		ArrayList<ArrayList<Object>> tableConverted = new ArrayList<>();
		ArrayList<Object> convertedRow;

		for(Map<Object, String> row : table){
			convertedRow = new ArrayList<>();

			for(Map.Entry entry : row.entrySet()){
				Class valObject = new SQLToJava().getType().get(entry.getValue());

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


	//adds from a resultset to
	public List<Map<Object, String>> getTableFromSet(ResultSet set){
		Map<Object, String> row;
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
