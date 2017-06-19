package sample.Interfaces;

import sample.Logic.User;

/**
 * Created by maxhe on 18-6-2017.
 */
public interface IUserSQL {
    boolean newUser(User user);
    User loginUser(String email, String password);
}
