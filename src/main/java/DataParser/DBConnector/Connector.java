package DataParser.DBConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//class used solely for connecting to a mysql database

public class Connector {
    private final String user = "appleFinance";
    private final String password = "fUIBix0pA1JfN0NmnqyZVg8cI7HEBpF2UJJchC9bF";

    private Connection con;
    private Statement statement;

    public Connector(String database){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(String.format("jdbc:mysql://sqlfinancedata.cguhxi2fh1vg.us-west-1.rds.amazonaws.com/%s?allowMultiQueries=true", database), user, password);
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
}
