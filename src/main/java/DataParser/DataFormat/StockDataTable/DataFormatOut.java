package DataParser.DataFormat.StockDataTable;

import DataParser.DataFormat.SQLToJava;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.*;

public class DataFormatOut{

	//used in conjuction with method below
	//converts mysql data type to java objects
//	public ArrayList<ArrayList<T>> convertType(List<Map<T, T>> table){
//		DecimalFormat df = new DecimalFormat("#");
//		df.setMaximumFractionDigits(3);
//
//		ArrayList<ArrayList<T>> tableConverted = new ArrayList<>();
//		ArrayList<T> convertedRow;
//
//		for(Map<T, T> row : table){
//			convertedRow = new ArrayList<>();
//
//			for(Map.Entry<T, T> entry : row.entrySet()){
//				Class valObject = new SQLToJava().getType().get(entry.getValue());
//
//				if(valObject.equals(Double.class)){
//					convertedRow.add((T) df.format(entry.getKey()));
//				}else{
//					convertedRow.add(entry.getKey());
//				}
//
//			}
//			tableConverted.add(convertedRow);
//		}
//
//		return tableConverted;
//	}


	//adds from a resultset to
	public ArrayList<ArrayList<Object>> getTableFromSet(ResultSet set){
		DecimalFormat df = new DecimalFormat("#");
		df.setMaximumFractionDigits(3);
		SQLToJava sqlToJava = new SQLToJava();
		ArrayList<Object> arrayRow;
		ArrayList<ArrayList<Object>> table = new ArrayList<>();

		int colCount;

		try {

			ResultSetMetaData metaData = set.getMetaData();
			colCount = metaData.getColumnCount();

			if(colCount == 0){
				System.out.println("Table has no columns");
				return null;
			}

			while(set.next()) {
				arrayRow = new ArrayList<>();

				for (int i = 1; i <= colCount; i++) {
					Object object = set.getObject(i);

					if(object.getClass().equals(Double.class)){
						arrayRow.add(df.format(object));
					}else{
						arrayRow.add(object);
					}
				}

				table.add(arrayRow);
			}


			return table;

		}catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}




}
