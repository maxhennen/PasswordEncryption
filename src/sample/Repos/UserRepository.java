package sample.Repos;

import sample.Controllers.Controller;
import sample.Interfaces.IUserSQL;
import sample.Logic.User;

/**
 * Created by maxhe on 18-6-2017.
 */
public class UserRepository {
    private IUserSQL Context;
    public UserRepository(IUserSQL context){
        Context = context;
    }

    public void newUser(User user){
        Context.newUser(user);
    }
}
