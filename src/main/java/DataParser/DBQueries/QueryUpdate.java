package DataParser.DBQueries;

import org.apache.wink.json4j.JSONException;

import java.io.IOException;
import java.sql.SQLException;

public class QueryUpdate extends AbstractQuery{
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
