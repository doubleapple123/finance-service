package DataParser.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Connector {
    private String query = "";
    private String database = "financeDatabase";
    private final String user = "root";
    private final String password = "budgettestapple";

    private ResultRow row = new ResultRow();
    private Connection con;
    private Statement statement;

    public Connector(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(String.format("jdbc:mysql://localhost/%s", database), user, password);
            statement = con.createStatement();

        } catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    public Connector(String query){
        this();
        this.query = query;
    }

    public Connector(String database, String query){
        this();
        this.database = database;
        this.query = query;
    }

    public void addToDatabase(String insertValues){
        try{
            statement.executeUpdate("INSERT INTO stockData VALUES (" + insertValues + ")");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ArrayList<Object>> getTable() {
        try {
            query = "select * from stockData";
            ResultSet resultSet = statement.executeQuery(query);
            List<Map<Object, String>> table = row.add(resultSet);

            resultSet.close();

            return row.convertType(table);

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnections() throws SQLException {
        statement.close();
        con.close();
    }
}
