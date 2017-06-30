package sample.Data;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.sun.org.apache.regexp.internal.RE;
import sample.Logic.User;
import sample.Interfaces.IUserSQL;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by maxhe on 18-6-2017.
 */
public class UserSQLContext extends Database implements IUserSQL  {

    public User newUser(User user){
        try {
            getConnection();
            String query = "INSERT INTO Users(email,userPassword,userName,phoneNumber)VALUES(?,?,?,?);";
            Prep = Conn.prepareStatement(query);
            Prep.setString(1,user.getEmail());
            Prep.setString(2,user.getPassword());
            Prep.setString(3,user.getName());
            Prep.setString(4,user.getPhoneNumber());
            Prep.executeUpdate();
            Conn.close();
            return user;
        }
        catch (SQLServerException e){
            JOptionPane.showMessageDialog(null,"Emai adress is already used");
            return null;
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Something went wrong!");
            return null;
        }
    }

    public User loginUser(String email, String password){
        try {
            getConnection();
            String query = "SELECT * FROM Users WHERE email = ? and userPassword = ?;";
            Prep = Conn.prepareStatement(query);
            Prep.setString(1,email);
            Prep.setString(2,password);
            Results = Prep.executeQuery();

            User user = null;

            while (Results.next()){
                user = new User();
                user.setEmail(Results.getString("email"));
                user.setName(Results.getString("userName"));
                user.setPhoneNumber(Results.getString("phoneNumber"));
            }

            Conn.close();
            return user;
        }
        catch (SQLServerException e){
            e.printStackTrace();
            return null;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public void changePassword(String email, String newPassword){
        try {
            getConnection();
            String query = "UPDATE Users SET userPassword = ? WHERE email = ?;";
            Prep = Conn.prepareStatement(query);
            Prep.setString(1,newPassword);
            Prep.setString(2,email);
            Prep.executeUpdate();

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public User checkUser(String email){
        try {
            getConnection();
            String query = "SELECT * FROM Users WHERE email = ?;";
            Prep = Conn.prepareStatement(query);
            Prep.setString(1,email);

            Results = Prep.executeQuery();
            User user = new User();

            while (Results.next()){
                user.setEmail(Results.getString("email"));
                user.setName(Results.getString("userName"));
                user.setPhoneNumber(Results.getString("phoneNumber"));
            }
            return user;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
