package sample.Data;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import sample.Logic.User;
import sample.Interfaces.IUserSQL;

import javax.swing.*;
import java.sql.SQLException;

/**
 * Created by maxhe on 18-6-2017.
 */
public class UserSQLContext extends Database implements IUserSQL  {

    public void newUser(User user){
        try {
            getConnection();
            String query = "INSERT INTO Users(email,userPassword,userName)VALUES(?,?,?);";
            Prep = Conn.prepareStatement(query);
            Prep.setString(1,user.getEmail());
            Prep.setString(2,user.getPassword());
            Prep.setString(3,user.getName());
            Prep.executeUpdate();
        }
        catch (SQLServerException e){
            JOptionPane.showMessageDialog(null,"Emai adress is already used");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
