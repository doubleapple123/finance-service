package DataParser;

import DataParser.DBQueries.Queries;

public class TestRunner{

/*
    plan for the future:
    	- query functions overloading
        - add functionality to inputting and getting data (get data by symbol, date range, etc)
        - able to connect to amazon rds directly
 */

	public static void main(String[] args){
		Queries queries = new Queries();
		String[] symbolsToAdd = {"SPY", "TSLA", "MSFT"};

//		queries.addToDatabaseSymbol(symbolsToAdd); //adds to database
//		queries.updateQuery();

		queries.addToDatabaseSymbol("SPY");
		queries.updateQuery();

//		queries.getAllTable();
//		queries.printOutTable(queries.executeQuery()); //execute query

//		queries.inputSymbolsIntoSymbolDatabase();
//		queries.updateQuery();

		queries.closeConnection();
	}
}
