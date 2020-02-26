package DataParser.DBQueries;

import org.apache.wink.json4j.JSONException;

import java.io.IOException;
import java.sql.SQLException;

public class QueryUpdate extends AbstractQuery{
	private String[] listOfTimeseries = {"TIME_SERIES_DAILY_ADJUSTED", "TIME_SERIES_WEEKLY_ADJUSTED"};

	public void addNotExist(String symbol){
		addNotExist(symbol, "full");
	}

	//TODO configure this when adding new data, need to setTimeser to corresponding correct database
	public void addNotExist(String symbol, String times){
		QueryExecute queryExecute = new QueryExecute();
		QueryUpdate queryUpdate = new QueryUpdate();

		if(!queryExecute.checkExist(symbol)){
			//if the symbol does not exist in table `stocksymbols`, then add data from past 20 yrs
			//to daily and weekly table
			for(String timeseries : listOfTimeseries){
				queryUpdate.setTimeser(timeseries);
				queryUpdate.addToDatabaseSymbol(symbol, times);
				queryUpdate.updateQuery();
			}

			queryUpdate.addToMainTable(symbol);
			queryUpdate.updateQuery();
		}

		queryExecute.closeConnection();
		queryUpdate.closeConnection();
	}

	public void addToMainTable(String symbol){
		setQuery(String.format("INSERT IGNORE INTO stocksymbols VALUES ('%s')", symbol));
	}

	public void addToDatabaseSymbol(String[] listOfSymbols, String outputsize){
		for(String symbol : listOfSymbols){
			addToDatabaseSymbol(symbol, outputsize);
			updateQuery();
		}
	}

	//uses data in
	public void addToDatabaseSymbol(String symbol, String outputsize){
		try{
			setQuery(String.format("INSERT IGNORE INTO %s VALUES %s",getDBtable(), dataIn.updateDatabase(symbol, getTimeser(), outputsize)));
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
}
