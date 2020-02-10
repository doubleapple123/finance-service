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
import java.util.List;
import java.util.Map;

public class Queries{

	private Connection connection;
	private Statement statement;
	private QueryType queryType;
	private DataFormatIn dataIn = new DataFormatIn();
	private DataFormatOut dataOut = new DataFormatOut();

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

	//uses data in
	public void addToDatabase(String symbol){
		try{
			statement.executeUpdate("INSERT INTO stockdata VALUES" +
					dataIn.updateDatabase(symbol));

		} catch(IOException | JSONException | SQLException e){
			e.printStackTrace();
		}
	}

	//uses data out
	public ArrayList<ArrayList<Object>> getAllTable(){
		try{
			String query = "SELECT * FROM stockdata";

			ResultSet resultSet = statement.executeQuery(query);
			List<Map<Object, String>> table = dataOut.getTableFromSet(resultSet);

			resultSet.close();

			return dataOut.convertType(table);

		}catch(SQLException e){
			System.out.println("Fetch failed");
			e.printStackTrace();
			return null;
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
