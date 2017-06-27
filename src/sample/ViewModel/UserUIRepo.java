package sample.ViewModel;

import sample.Controllers.Controller;
import sample.Interfaces.IUserUI;
import sample.Logic.User;

import java.util.ArrayList;

/**
 * Created by maxhe on 18-6-2017.
 */
public class UserUIRepo {
    private IUserUI Context;

    public UserUIRepo(IUserUI context){
        Context = context;
    }

    public User newUser(String name, String email, String password, String phone){
       return Context.newUser(name,email,password, phone);
    }

    public User Login(String email, String password){
        return Context.Login(email,password);
    }
    public String SendSMS(String phoneNumber){return Context.SendSMS(phoneNumber);}
    public String sendNewPassword(String email){return Context.sendNewPassword(email);}

}
