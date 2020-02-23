package DataParser.DBQueries;

import java.sql.SQLException;
import java.util.ArrayList;

public class QueryExecute extends AbstractQuery{
	public void getDistinctSymbols(){
		setQuery(String.format("select distinct symbol from stockadjusteddaily"));
	}

	public boolean checkExist(String symbol){
		setQuery(String.format("select exists(select * from %s where `symbol` = '%s')", "stocksymbols", symbol));
		ArrayList<ArrayList<Object>> exist = executeQuery();
		if(Integer.parseInt(exist.get(0).get(0).toString()) == 0){
			return false;
		}else{
			return true;
		}
	}

	public void getTableFromSymbol(String symbol, String startDate, String endDate){
		setQuery(String.format("SELECT * FROM %s where symbol = '%s' and date between '%s' and '%s'",getDBtable(), symbol, startDate, endDate));
	}

	public void getTableFromSymbol(String symbol){
		setQuery(String.format("SELECT * FROM %s WHERE `symbol` = '%s'",getDBtable(), symbol));
	}

	//uses data out
	public void getAllTable(){
		setQuery(String.format("SELECT * FROM %s",getDBtable()));
	}

	//to update database
	public void updateDatabase(String table){
		describeTable(table);
		String thisTable = executeQuery().toString();
		System.out.println(thisTable);

	}

	public void describeTable(String table){
		setQuery(String.format("SELECT column_name\n" +
				"FROM information_schema.columns\n" +
				"WHERE  table_name = '%s'\n" +
				"   AND table_schema = 'financedatabase'", table));
	}

	public ArrayList<ArrayList<Object>> executeQuery(){
		try{
			resultSet = statement.executeQuery(getQuery());
			dataTable = dataOut.getTableFromSet(resultSet);

			return dataTable;

		}catch (SQLException e){
			System.out.println("fetch from symbol failed");
			e.printStackTrace();
			return null;
		}
	}

	public void printOutTable(ArrayList<ArrayList<Object>> table){
		for(ArrayList<?> row : table){
			System.out.println(row);
		}
	}


}
