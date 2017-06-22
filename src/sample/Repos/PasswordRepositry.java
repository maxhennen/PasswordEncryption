package sample.Repos;

import sample.Controllers.Controller;
import sample.Interfaces.IPasswordSQL;
import sample.Logic.Password;
import sample.Logic.User;

import java.util.ArrayList;

/**
 * Created by maxhe on 21-6-2017.
 */
public class PasswordRepositry {
    private IPasswordSQL Context;

    public PasswordRepositry(IPasswordSQL context){
        Context = context;
    }

    public ArrayList<Password> passwordUser(String email){
        return Context.passwordUser(email);
    }

    public void newPassword(User user, Password password){
        Context.newPassword(user,password);
    }
}
