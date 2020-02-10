package DataParser.DBConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//class used solely for connecting to a mysql database
//in the future this might be used to connect to a cloud (aws) database

public class Connector {
    private final String user = "root";
    private final String password = "mnksp4yAcnD23yhkcLAbho7idbFm3lHn";

    private Connection con;
    private Statement statement;

    public Connector(String database){
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
}
