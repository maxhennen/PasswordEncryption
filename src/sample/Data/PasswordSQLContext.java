package sample.Data;

import com.sun.org.apache.regexp.internal.RE;
import sample.Interfaces.IPasswordSQL;
import sample.Logic.Password;
import sample.Logic.User;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by maxhe on 21-6-2017.
 */
public class PasswordSQLContext extends Database implements IPasswordSQL {

    public ArrayList<Password> passwordUser(String email){
        try {
            ArrayList<Password> passwords = new ArrayList<>();
            getConnection();
            String query = "SELECT * FROM Passwords WHERE userMail = ?;";
            Prep = Conn.prepareStatement(query);
            Prep.setString(1,email);
            Results = Prep.executeQuery();

            while (Results.next()){
                Password password = new Password();
                password.setContent(Results.getString("nameContent"));
                password.setPassword(Results.getString("encryptedPassword"));
                passwords.add(password);
            }
            return passwords;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public void newPassword(User user, Password password){
        try {
            getConnection();
            String query = "INSERT INTO Passwords(userMail, nameContent, encryptedPassword)Values(?,?,?);";
            Prep = Conn.prepareStatement(query);
            Prep.setString(1,user.getEmail());
            Prep.setString(2,password.getContent());
            Prep.setString(3,password.getPassword());
            Prep.executeUpdate();
        }

        catch (SQLException e){

        }
    }
}
