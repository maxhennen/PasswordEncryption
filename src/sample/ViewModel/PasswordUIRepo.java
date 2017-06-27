package sample.ViewModel;

import sample.Interfaces.IPasswordUI;
import sample.Logic.Password;
import sample.Logic.User;

import java.util.ArrayList;

/**
 * Created by maxhe on 22-6-2017.
 */
public class PasswordUIRepo {
    private IPasswordUI Context;

    public PasswordUIRepo(IPasswordUI context){
        Context = context;
    }

    public void newPassword(User user, String content, String password){
        Context.newPassword(user,content,password);
    }

    public ArrayList<Password> passwordsUser(User user){
        return Context.passwordsUser(user);
    }
}
