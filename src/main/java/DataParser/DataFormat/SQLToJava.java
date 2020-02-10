package DataParser.DataFormat;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.*;
import java.util.*;

//used to convert mysql types to java types to use

public class SQLToJava {
    private Map<Object, Class> type;

    public SQLToJava(){
        addTypes();
    }

    public Map<Object, Class> getType(){
        return type;
    }

    public void addTypes(){
        type = new HashMap<>();
        type.put("CHAR", String.class);
        type.put("VARCHAR", String.class);
        type.put("LONGVARCHAR", String.class);
        type.put("NUMERIC", BigDecimal.class);
        type.put("DECIMAL", BigDecimal.class);
        type.put("BIT", Boolean.class);
        type.put("TINYINT", Byte.class);
        type.put("SMALLINT", Short.class);
        type.put("INTEGER", Integer.class);
        type.put("BIGINT", Long.class);
        type.put("REAL", Float.class);
        type.put("FLOAT", Double.class);
        type.put("DOUBLE", Double.class);
        type.put("BINARY", byte[].class);
        type.put("VARBINARY", byte[].class);
        type.put("LONGARBINARY", byte[].class);
        type.put("DATE", Date.class);
        type.put("TIME", Time.class);
        type.put("TIMESTAMP", Timestamp.class);
        type.put("TEXT", String.class);
    }

}
