package DataParser;

import DataParser.DBQueries.Queries;

import java.util.ArrayList;

public class TestRunner{

/*
    plan for the future:
        - add functionality to inputting and getting data (get data by symbol, date range, etc)
        - automate update of aws rds database using java or python
 */

	public static void main(String[] args){
		Queries queries = new Queries();
		String[] symbolsToAdd = {"SPY", "TSLA", "MSFT"};

//		queries.addToDatabaseSymbol(symbolsToAdd);

		queries.printOutTable(queries.getTableFromSymbol("SPY"));


		queries.closeConnection();
	}
}
