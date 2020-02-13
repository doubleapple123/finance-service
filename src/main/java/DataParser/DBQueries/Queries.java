package DataParser.DBQueries;

import DataParser.DBConnector.Connector;
import DataParser.DataFormat.DataFormatIn;
import DataParser.DataFormat.DataFormatOut;
import org.apache.wink.json4j.JSONException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Queries{
	private String curDatabase = "financedatabase";
	private String table = "stockdaily";

	private String query;
	private Connection connection;
	private Statement statement;
	private QueryType queryType;
	private DataFormatIn dataIn = new DataFormatIn();
	private DataFormatOut dataOut = new DataFormatOut();
	private ResultSet resultSet;
	private ArrayList<ArrayList<Object>> dataTable;

	public Queries(){
		Connector connector = new Connector(curDatabase);
		this.connection = connector.getCon();
		this.statement = connector.getStatement();
	}

	public Queries(QueryType queryType){
		this();
		this.queryType = queryType;
	}

	public void setQuery(String query){
		this.query = query;
	}

	public String getQuery(){
		return query;
	}

	public boolean checkExist(String table, String symbol){
		setQuery(String.format("select exists(select * from %s where `symbol` = '%s'", table, symbol));
		ArrayList<ArrayList<Object>> exist = executeQuery();
		if(Integer.parseInt(exist.get(0).get(0).toString()) == 0){
			return false;
		}else{
			return true;
		}
	}

	public void addToDatabaseSymbol(String[] listOfSymbols, String timeseries){
		for(String symbol : listOfSymbols){
			addToDatabaseSymbol(symbol, timeseries);
		}
	}

	public void addToDatabaseSymbol(String symbol){
		addToDatabaseSymbol(symbol, "TIME_SERIES_DAILY");
	}

	//uses data in
	public void addToDatabaseSymbol(String symbol, String timeseries){
		try{
			setQuery("INSERT INTO " + table + " VALUES" + dataIn.updateDatabase(symbol,timeseries));
		} catch(IOException | JSONException e){
			e.printStackTrace();
		}
	}

	public void updateQuery(){
		try{
			statement.executeUpdate(getQuery());
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void getTableFromSymbol(String symbol, String startDate, String endDate){
		setQuery(String.format("SELECT * FROM %s where symbol = '%s' and date between '%s' and '%s'",table, symbol, startDate, endDate));
	}

	public void getTableFromSymbol(String symbol){
		setQuery(String.format("SELECT * FROM %s WHERE `symbol` = '%s'",table, symbol));
	}

	//uses data out
	public void getAllTable(){
		setQuery(String.format("SELECT * FROM %s",table));
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

	public void inputSymbolsIntoSymbolDatabase(){
		try{
			//gets distinct values from stockdata and inputs them into stocksymbols, a table
			//which has all the stock symbols
			StringBuilder stringBuilder = new StringBuilder();
			setQuery(String.format("SELECT DISTINCT symbol FROM %s",table)); //gets all symbols
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

	public void printOutTable(ArrayList<ArrayList<Object>> table){
		for(ArrayList<?> row : table){
			System.out.println(row);
		}
	}

	public void closeConnection(){
		try{
			statement.close();
			connection.close();
		}catch(SQLException e){
			System.out.println("Close failed");
			e.printStackTrace();
		}
	}
}
