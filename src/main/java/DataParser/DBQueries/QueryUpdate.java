package DataParser.DBQueries;

import org.apache.wink.json4j.JSONException;

import java.io.IOException;
import java.sql.SQLException;

public class QueryUpdate extends AbstractQuery{
	public void addToMainTable(String symbol){
		setQuery(String.format("INSERT IGNORE INTO stocksymbols VALUES ('%s')", symbol));
		System.out.println(getQuery());
	}

	public void addToDatabaseSymbol(String[] listOfSymbols){
		for(String symbol : listOfSymbols){
			addToDatabaseSymbol(symbol);
			updateQuery();
		}
	}

	//uses data in
	public void addToDatabaseSymbol(String symbol){
		try{
			setQuery(String.format("INSERT IGNORE INTO %s VALUES %s",getDBtable(), dataIn.updateDatabase(symbol, getTimeser())));
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
