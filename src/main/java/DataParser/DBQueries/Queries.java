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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Queries{

	private Connection connection;
	private Statement statement;
	private QueryType queryType;
	private DataFormatIn dataIn = new DataFormatIn();
	private DataFormatOut dataOut = new DataFormatOut();
	private ResultSet resultSet;
	private List<Map<Object, String>> dataTable;

	public Queries(){
		Connector connector = new Connector();
		this.connection = connector.getCon();
		this.statement = connector.getStatement();
	}

	public Queries(QueryType queryType){
		this();
		this.queryType = queryType;
	}

	public void executeQuery(){

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
			statement.executeUpdate("INSERT INTO stockdata VALUES" +
					dataIn.updateDatabase(symbol, full));

		} catch(IOException | JSONException | SQLException e){
			e.printStackTrace();
		}
	}

	public void addToDatabaseSymbol(String symbol, LocalDate startDate, LocalDate endDate){
//		try{
//
//
//
//		}catch(SQLException e){
//			e.printStackTrace();
//		}
	}

	public ArrayList<ArrayList<Object>> getTableFromSymbol(String symbol){
		try{
			String query = String.format("SELECT * FROM stockdata WHERE `stock_symbol` = '%s'", symbol);
			System.out.println("query is : " + query);

			resultSet = statement.executeQuery(query);
			dataTable = dataOut.getTableFromSet(resultSet);

			resultSet.close();
			return dataOut.convertType(dataTable);

		}catch (SQLException e){
			System.out.println("fetch from symbol failed");
			e.printStackTrace();
			return null;
		}
	}

	//uses data out
	public ArrayList<ArrayList<Object>> getAllTable(){
		try{
			String query = "SELECT * FROM stockdata";

			resultSet = statement.executeQuery(query);
			dataTable = dataOut.getTableFromSet(resultSet);


			resultSet.close();
			return dataOut.convertType(dataTable);

		}catch(SQLException e){
			System.out.println("Fetch all data failed");
			e.printStackTrace();
			return null;
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
