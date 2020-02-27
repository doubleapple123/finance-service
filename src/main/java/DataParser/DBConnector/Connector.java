package DataParser.DBConnector;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//class used solely for connecting to a mysql database
@Service
public class Connector {
    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.url}")
    private String databaseURL;


    private Connection con;
    private Statement statement;

    public Connector(String database){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = password + databaseURL + "/%s?allowMultiQueries=true";
            System.out.println(dbURL);
            con = DriverManager.getConnection(String.format(dbURL , database), user, password);
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
