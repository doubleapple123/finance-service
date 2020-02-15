package DataParser.DBQueries;

import DataParser.DBConnector.Connector;
import DataParser.DataFormat.DataFormatIn;
import DataParser.DataFormat.DataFormatOut;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class AbstractQuery{
	protected String curDatabase = "financedatabase";
	protected String DBtable;
	protected String timeser;

	protected String query;
	protected Connection connection;
	protected Statement statement;
	protected DataFormatIn dataIn = new DataFormatIn();
	protected DataFormatOut dataOut = new DataFormatOut();
	protected ResultSet resultSet;
	protected ArrayList<ArrayList<Object>> dataTable;

	public AbstractQuery(){
		Connector connector = new Connector(curDatabase);
		this.connection = connector.getCon();
		this.statement = connector.getStatement();
		this.DBtable = "stocksymbols";
	}

	public void setTimeser(String timeser){
		this.timeser = timeser;
		setDBtable(timeser);
	}

	public String getTimeser(){
		return this.timeser;
	}

	//TODO: add cases and set tables for different data inputs(technical indicators)
	public void setDBtable(String timeseries){
		switch(timeseries){
			case "TIME_SERIES_DAILY" : this.DBtable = "stockdaily"; break;
			case "TIME_SERIES_WEEKLY" : this.DBtable = "stockweekly"; break;
			case "TIME_SERIES_DAILY_ADJUSTED" : this.DBtable = "stockadjusteddaily"; break;
			default: break;
		}
	}

	public String getDBtable(){
		return this.DBtable;
	}

	public void setQuery(String query){
		this.query = query;
	}

	public String getQuery(){
		return query;
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
