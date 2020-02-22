package DataParser.DataFormat;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class DataFormatOut{
	//should already be generified to handle any data object
	//adds from a resultset to datatable
	public ArrayList<ArrayList<Object>> getTableFromSet(ResultSet set){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		dtf = dtf.withLocale(Locale.ENGLISH);
		LocalDate date;
		DecimalFormat df = new DecimalFormat("#");
		df.setMaximumFractionDigits(3);
		ArrayList<Object> arrayRow;
		ArrayList<ArrayList<Object>> table = new ArrayList<>();

		int colCount;
		try {
			ResultSetMetaData metaData = set.getMetaData();
			colCount = metaData.getColumnCount();

			while(set.next()) {
				arrayRow = new ArrayList<>();

				for (int i = 1; i <= colCount; i++) {
					Object object = set.getObject(i);
					if(object != null){
						if(object.getClass().equals(Double.class)){
							arrayRow.add(df.format(object));

						}
						else if (object.getClass().equals(Date.class)){
							arrayRow.add(((Date) object).toLocalDate());

						}
						else{
							arrayRow.add(object);
						}
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
