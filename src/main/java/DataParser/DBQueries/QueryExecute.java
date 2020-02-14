package DataParser.DBQueries;

import java.sql.SQLException;
import java.util.ArrayList;

public class QueryExecute extends AbstractQuery{
	public boolean checkExist(String symbol){
		setQuery(String.format("select exists(select * from %s where `symbol` = '%s')", "stocksymbols", symbol));
		ArrayList<ArrayList<Object>> exist = executeQuery();
		if(Integer.parseInt(exist.get(0).get(0).toString()) == 0){
			System.out.println("FALSE");
			return false;
		}else{
			System.out.println("TRUE");
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

	public ArrayList<ArrayList<Object>> executeQuery(){
		try{
			resultSet = statement.executeQuery(getQuery());
			dataTable = dataOut.getTableFromSet(resultSet);
			resultSet.close();

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

	public void inputSymbolsIntoSymbolDatabase(){
		try{
			//gets distinct values from stockdata and inputs them into stocksymbols, a table
			//which has all the stock symbols
			StringBuilder stringBuilder = new StringBuilder();
			setQuery(String.format("SELECT DISTINCT symbol FROM %s",getDBtable())); //gets all symbols
			resultSet = statement.executeQuery(getQuery());

			while(resultSet.next()){
				stringBuilder.append(String.format("INSERT INTO `stocksymbols` (`symbol`) select '%1$s' from dual where not exists" +
						"(select * from `stocksymbols` where `symbol` = '%1$s' limit 1);",resultSet.getString(1)));
			}

			setQuery(stringBuilder.toString());

		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
