package sample.Interfaces;

import sample.Logic.User;

import java.util.ArrayList;

/**
 * Created by maxhe on 18-6-2017.
 */
public interface IUserUI {
    User newUser(String name, String email, String password, String phone);
    User Login(String email, String password);
    String SendSMS(String phoneNumber);
    void sendNewPassword(String email);
}
