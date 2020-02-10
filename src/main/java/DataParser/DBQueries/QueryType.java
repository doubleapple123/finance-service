package DataParser.DBQueries;

public enum QueryType{
	GET, PUT, POST, DELETE;

	private String query;

	QueryType(){

	}

	QueryType(String query){

	}

	public String getQuery(){
		return query;
	}
}
