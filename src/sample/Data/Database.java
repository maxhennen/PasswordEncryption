package sample.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by maxhe on 18-6-2017.
 */
public abstract class Database {
    public Connection Conn;
    public PreparedStatement Prep;
    public ResultSet Results;

    public void getConnection(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-OBIGL5V;databaseName=PasswordSecurity;integratedSecurity=true");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
