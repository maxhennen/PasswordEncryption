package sample.Interfaces;

/**
 * Created by maxhe on 18-6-2017.
 */
public interface IUserUI {
    boolean newUser(String name, String email, String password);
    boolean Login(String email, String password);
}
