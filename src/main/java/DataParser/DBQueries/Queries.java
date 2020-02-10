package DataParser.DBQueries;

import DataParser.DBConnector.Connector;
import DataParser.DBConnector.ResultRow;
import DataParser.DataExport;
import org.apache.wink.json4j.JSONException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Queries{

	private Connection connection;
	private Statement statement;
	private QueryType queryType;

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

	public void addToDatabase(){
		try{
			DataExport dataExport = new DataExport();

			statement.executeUpdate("INSERT INTO stockdata VALUES" +
					dataExport.updateDatabase());

		} catch(SQLException e){
			System.out.println("Insert failed");
			e.printStackTrace();

		} catch(IOException | JSONException | NoSuchFieldException | IllegalAccessException | ParseException e){
			e.printStackTrace();
		}
	}

	public ArrayList<ArrayList<Object>> getAllTable(){
		try{
			ResultRow row = new ResultRow();
			String query = "SELECT * FROM stockdata";

			ResultSet resultSet = statement.executeQuery(query);
			List<Map<Object, String>> table = row.add(resultSet);

			resultSet.close();

			return row.convertType(table);

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
