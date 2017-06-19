package sample.ViewModel;

import sample.Controllers.Controller;
import sample.Interfaces.IUserUI;

/**
 * Created by maxhe on 18-6-2017.
 */
public class UserUIRepo {
    private IUserUI Context;

    public UserUIRepo(IUserUI context){
        Context = context;
    }

    public boolean newUser(String name, String email, String password){
       return Context.newUser(name,email,password);
    }

    public boolean Login(String email, String password){
        return Context.Login(email,password);
    }
}
