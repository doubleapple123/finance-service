package DataParser.DBQueries;

import DataParser.DBConnector.Connector;
import DataParser.DataFormat.StockDataTable.DataFormatIn;
import DataParser.DataFormat.StockDataTable.DataFormatOut;
import org.apache.wink.json4j.JSONException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Queries{
	private String query;
	private Connection connection;
	private Statement statement;
	private QueryType queryType;
	private DataFormatIn dataIn = new DataFormatIn();
	private DataFormatOut dataOut = new DataFormatOut();
	private ResultSet resultSet;
	private List<Map<Object, String>> dataTable;

	public Queries(){
		Connector connector = new Connector("financedatabase");
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

	public void addToDatabaseSymbol(String[] listOfSymbols){
		for(String symbol : listOfSymbols){
			addToDatabaseSymbol(symbol);
		}
	}

	public void addToDatabaseSymbol(String symbol){
		addToDatabaseSymbol(symbol, false);
	}

	//uses data in
	public void addToDatabaseSymbol(String symbol, boolean full){
		try{
			setQuery("INSERT INTO stockdata VALUES" + dataIn.updateDatabase(symbol,full));
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
		setQuery(String.format("SELECT * FROM stockdata where stock_symbol = '%s' and date between '%s' and '%s'", symbol, startDate, endDate));
	}

	public void getTableFromSymbol(String symbol){
		setQuery(String.format("SELECT * FROM stockdata WHERE `stock_symbol` = '%s'", symbol));
	}

	//uses data out
	public void getAllTable(){
		setQuery("SELECT * FROM stockdata");
	}

	public ArrayList<ArrayList<Object>> executeQuery(){
		try{
			resultSet = statement.executeQuery(getQuery());
			dataTable = dataOut.getTableFromSet(resultSet);

			resultSet.close();
			return dataOut.convertType(dataTable);

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
			setQuery("SELECT DISTINCT stock_symbol FROM stockdata"); //gets all symbols
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
		for(ArrayList<Object> row : table){
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
