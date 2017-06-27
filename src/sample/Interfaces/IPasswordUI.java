package sample.Interfaces;

import sample.Logic.Password;
import sample.Logic.User;

import java.util.ArrayList;

/**
 * Created by maxhe on 22-6-2017.
 */
public interface IPasswordUI {
    void newPassword(User user, String content, String password);
    ArrayList<Password> passwordsUser(User user);
}
