package sample.ViewModel;

import sample.Interfaces.IPasswordUI;
import sample.Logic.User;

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
}
