package sample.Interfaces;

import sample.Logic.User;

import java.util.ArrayList;

/**
 * Created by maxhe on 18-6-2017.
 */
public interface IUserSQL {
    User newUser(User user);
    User loginUser(String email, String password);
}
