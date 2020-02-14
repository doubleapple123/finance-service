package DataParser;

import DataParser.DBQueries.AbstractQuery;
import DataParser.DBQueries.QueryExecute;
import DataParser.DBQueries.QueryUpdate;

import javax.management.Query;

public class TestRunner{

/*
    plan for the future:

     --TODO   - join table functionality
    --TODO	- check if stock input is in database, if not add info to database, then display data
        - add functionality to inputting and getting data (get data by symbol, date range, etc)
        - able to connect to amazon rds directly
 */

	public static void main(String[] args){
		QueryExecute execute = new QueryExecute();
		QueryUpdate update = new QueryUpdate();

		String[] symbolsToAdd = {"SPY", "TSLA", "MSFT"};

//		queries.addToDatabaseSymbol(symbolsToAdd, "TIME_SERIES_DAILY"); //adds to database
//		queries.addToDatabaseSymbol(symbolsToAdd, "TIME_SERIES_WEEKLY");

		update.setTimeser("TIME_SERIES_DAILY");
		update.addToDatabaseSymbol("F");
		update.updateQuery();

//		execute.setTimeser("TIME_SERIES_DAILY");
//		execute.getAllTable();
//		execute.printOutTable(execute.executeQuery()); //execute query

//		queries.inputSymbolsIntoSymbolDatabase();
//		queries.updateQuery();

		update.closeConnection();
		execute.closeConnection();
	}
}
