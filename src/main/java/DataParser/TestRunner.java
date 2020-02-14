package DataParser;

import DataParser.DBQueries.Queries;

public class TestRunner{

/*
    plan for the future:

     --TODO   - join table functionality
    --TODO	- check if stock input is in database, if not add info to database, then display data
        - add functionality to inputting and getting data (get data by symbol, date range, etc)
        - able to connect to amazon rds directly
 */

	public static void main(String[] args){
		Queries queries = new Queries();
		String[] symbolsToAdd = {"SPY", "TSLA", "MSFT"};

//		queries.addToDatabaseSymbol(symbolsToAdd, "TIME_SERIES_DAILY"); //adds to database
//		queries.addToDatabaseSymbol(symbolsToAdd, "TIME_SERIES_WEEKLY");

		queries.setTimeser("TIME_SERIES_DAILY");
		queries.setDBtable("TIME_SERIES_DAILY");
		queries.addToDatabaseSymbol("SPY");
		queries.updateQuery();

//		queries.getAllTable();
//		queries.printOutTable(queries.executeQuery()); //execute query

//		queries.inputSymbolsIntoSymbolDatabase();
//		queries.updateQuery();

		queries.closeConnection();
	}
}
