package DataParser.DBConnector;

import io.myfunstuff.stocks.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//class used solely for connecting to a mysql database
public class Connector {
    @Autowired
    private PropertyValues propertyValues;

    private Connection con;
    private Statement statement;

    public Connector(String database){
        System.out.println(propertyValues.getApikey());
        System.out.println(propertyValues.getDataUser());
        System.out.println(propertyValues.getDataURL());
        System.out.println(propertyValues.getDataDatabase());
        System.out.println(propertyValues.getDataPass());
        try {
            System.out.println(propertyValues.apikey);
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = propertyValues.apikey + propertyValues.dataURL + "/%s?allowMultiQueries=true";
            System.out.println(dbURL);
            con = DriverManager.getConnection(String.format(dbURL, database), propertyValues.dataUser, propertyValues.dataPass);
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
