package DataParser.DBConnector;

import io.myfunstuff.stocks.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//class used solely for connecting to a mysql database
public class Connector {
    private Connection con;
    private Statement statement;

    public Connector(String database, PropertyValues propertyValues){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = propertyValues.getDataURL() + "/%s?allowMultiQueries=true";
            con = DriverManager.getConnection(String.format(dbURL, database), propertyValues.getDataUser(), propertyValues.getDataPass());
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
