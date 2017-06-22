package sample.Logic;

import sample.Controllers.Controller;
import sample.Data.PasswordSQLContext;
import sample.Interfaces.IPasswordSQL;
import sample.Interfaces.IPasswordUI;
import sample.Repos.PasswordRepositry;

import java.util.ArrayList;

/**
 * Created by maxhe on 21-6-2017.
 */
public class Password implements IPasswordUI {
    private String Password;
    private String Content;

    private Encrypt Encrypt = new Encrypt();
    //private Decrypt Decryt;
    private PasswordRepositry PasswordRepo;

    public String getPassword(){return Password;}
    public void setPassword(String password){Password = password;}

    public String getContent(){return Content;}
    public void setContent(String content){Content = content;}

    public ArrayList<String> passwordsUser(String email){
        return null;
    }

    public void newPassword(User user, String password, String content){
        setPassword(Encrypt.Encryption(password.getBytes(),user.loadKey(user.getEmail())));
        setContent(content);
        PasswordRepo = new PasswordRepositry(new PasswordSQLContext());
        PasswordRepo.newPassword(user,this);
    }
}
