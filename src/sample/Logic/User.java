package sample.Logic;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.twilio.exception.TwilioException;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.chat.v1.service.channel.MessageCreator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.hsqldb.Session;
import sample.Data.UserSQLContext;
import sample.Interfaces.IUserUI;
import sample.Repos.UserRepository;
import javax.net.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

import static sun.net.ftp.impl.FtpClient.create;

/**
 * Created by maxhe on 18-6-2017.
 */
public class User implements IUserUI {
    private String Name;
    private String Email;
    private String Password;
    private String PhoneNumber;
    private SecretKey Key;
    private UserRepository UserRepo;

    public String getName(){return Name;}
    public String getEmail(){return Email;}
    public String getPassword(){return Password;}
    public String getPhoneNumber(){return PhoneNumber;}
    public SecretKey getKey(){return Key;}

    public void setName(String name){Name = name;}
    public void setEmail(String email){Email = email;}
    public void setPassword(String password){Password = password;}
    public void setPhoneNumber(String phoneNumber){PhoneNumber = phoneNumber;}

    private Encrypt Encrypt = new Encrypt();
    private String TwilioSID = "AC0e7ecf6b3e893d1bc8ca45799f76f6bb";
    private String TwilioAuth = "8bc638717b8f3a0804e2ed1f24a695d6";

    public User newUser(String name, String email, String password, String phone){
        setName(name);
        setEmail(email);
        setPassword(MD5Password(password));
        setPhoneNumber(phone);
        UserRepo = new UserRepository(new UserSQLContext());
        return UserRepo.newUser(this);
    }


    public String MD5Password(String password){
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(password.getBytes(), 0, password.length());
            return new BigInteger(1,m.digest()).toString(16);
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }

    public User Login(String email, String password){
        UserRepo = new UserRepository(new UserSQLContext());
        User user = UserRepo.loginUser(email,MD5Password(password));
        return user;
    }

    public String SendSMS(String phoneNumber){
        try {
            Twilio.init(TwilioSID, TwilioAuth);

            String validationCode = randomString();

            Message message = Message.creator(new PhoneNumber(phoneNumber),new PhoneNumber("+3197004498649"),
                    "Here's your validation code: " + validationCode).create();
            return validationCode;
        }
        catch (TwilioException e){
            e.printStackTrace();
            return null;
        }
    }

    public String randomString(){
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    public String sendNewPassword(String email){
        try {
            String newPassword = randomString();

            SimpleEmail simpleEmail = new SimpleEmail();
            simpleEmail.setHostName("smtp.gmail.com");
            simpleEmail.addTo(email, "Unknown");
            simpleEmail.setFrom("no-reply@passwordSecurity.com", "PasswordSecurity");
            simpleEmail.setSubject("New Password PasswordSecurity");
            simpleEmail.setMsg("Here's your new pasword: " + newPassword);
            simpleEmail.send();
            return newPassword;
        }
        catch (EmailException e){
            e.printStackTrace();
            return null;
        }

    }
}
