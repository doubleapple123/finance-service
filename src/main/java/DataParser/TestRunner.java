package DataParser;

import DataParser.DBQueries.QueryExecute;
import DataParser.DBQueries.QueryUpdate;
import org.apache.commons.lang3.builder.EqualsExclude;

public class TestRunner{

/*
    plan for the future:

     --TODO     - add more tables and functionality for technical data
     			- start researching charting libraries and functions
     			- fix and generify more classes

 */

	public static void main(String[] args){
		QueryExecute execute = new QueryExecute();
		QueryUpdate update = new QueryUpdate();

		String[] symbolsToAdd = {"SPY", "TSLA", "MSFT"};

//		queries.addToDatabaseSymbol(symbolsToAdd, "TIME_SERIES_DAILY"); //adds to database
//		queries.addToDatabaseSymbol(symbolsToAdd, "TIME_SERIES_WEEKLY");

		update.setTimeser("TIME_SERIES_DAILY");
		update.addToDatabaseSymbol("F");
		System.out.println(update.getQuery());
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
