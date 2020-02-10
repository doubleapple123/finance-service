package DataParser.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Connector {
    private String query = "";
    private String database = "financedatabase";
    private final String user = "root";
    private final String password = "mnksp4yAcnD23yhkcLAbho7idbFm3lHn";

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

    public Connection getCon(){
        return con;
    }

    public Statement getStatement(){
        return statement;
    }

    public String getUser(){
        return user;
    }

    public String getPassword(){
        return password;
    }

    public String getDatabase(){
        return database;
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
