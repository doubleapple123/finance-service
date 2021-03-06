package DataParser.DBQueries;

import DataParser.DBConnector.Connector;
import DataParser.DataFormat.DataFormatIn;
import DataParser.DataFormat.DataFormatOut;
import io.myfunstuff.stocks.PropertyValues;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class AbstractQuery{
	protected String DBtable;
	protected String timeser;
	protected String query;
	protected Connection connection;
	protected Statement statement;
	protected DataFormatIn dataIn = new DataFormatIn();
	protected DataFormatOut dataOut = new DataFormatOut();
	protected ResultSet resultSet;
	protected ArrayList<ArrayList<Object>> dataTable;

	public AbstractQuery(PropertyValues propertyValues){
		Connector connector = new Connector(propertyValues.getDataDatabase(), propertyValues);
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
	//TODO: configure this when adding new data
	public void setDBtable(String timeseries){
		switch(timeseries){
			case "TIME_SERIES_DAILY_ADJUSTED" : this.DBtable = "stockadjusteddaily"; break;
			case "TIME_SERIES_WEEKLY_ADJUSTED" : this.DBtable = "stockadjustedweekly"; break;
			case "TIME_SERIES_INTRADAY" : this.DBtable = "stockintraday";
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
			if(resultSet != null){
				resultSet.close();
			}
			if(statement != null){
				statement.close();
			}
			if(connection != null){
				connection.close();
			}

		}catch(SQLException e){
			System.out.println("Close failed");
			e.printStackTrace();
		}
	}
}
