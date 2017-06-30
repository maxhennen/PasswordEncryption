package sample.Repos;

import sample.Controllers.Controller;
import sample.Interfaces.IUserSQL;
import sample.Logic.User;

import java.util.ArrayList;

/**
 * Created by maxhe on 18-6-2017.
 */
public class UserRepository {
    private IUserSQL Context;
    public UserRepository(IUserSQL context){
        Context = context;
    }

    public User newUser(User user){
        return Context.newUser(user);
    }

    public User loginUser(String email, String password){
        return Context.loginUser(email,password);
    }

    public void changePassword(String email, String password){Context.changePassword(email,password);}
    public User checkUser(String email){return Context.checkUser(email);}
}
