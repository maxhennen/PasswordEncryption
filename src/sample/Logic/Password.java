package sample.Logic;

import sample.Controllers.Controller;
import sample.Repos.PasswordRepositry;

import java.util.ArrayList;

/**
 * Created by maxhe on 21-6-2017.
 */
public class Password {
    private String Password;
    private String Content;

    private PasswordRepositry PasswordRepo;

    public String getPassword(){return Password;}
    public void setPassword(String password){Password = password;}

    public String getContent(){return Content;}
    public void setContent(String content){Content = content;}

    public ArrayList<String> passwordsUser(String email){
        return null;
    }
}
