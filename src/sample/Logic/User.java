package sample.Logic;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.twilio.exception.TwilioException;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.chat.v1.service.channel.MessageCreator;
import jdk.nashorn.internal.scripts.JO;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.hsqldb.Session;
import sample.Data.UserSQLContext;
import sample.Interfaces.IUserUI;
import sample.Repos.UserRepository;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
        String TwilioSID = "AC0e7ecf6b3e893d1bc8ca45799f76f6bb";
        String TwilioAuth = "8bc638717b8f3a0804e2ed1f24a695d6";
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

    public void sendNewPassword(String email){
        try {
            UserRepo = new UserRepository(new UserSQLContext());
            User user = UserRepo.checkUser(email);
            if(user.getEmail() != null) {

                String newPassword = randomString();

                String userSMTP = "hennenmax@gmail.com";
                String passwordSMTP = getSMTPPassword();

                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");
                javax.mail.Session session = javax.mail.Session.getDefaultInstance(props, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userSMTP, passwordSMTP);
                    }
                });

                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress("PasswordSecurity" + "<" + "no-reply@passwordsecurity.com" + ">"));
                message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(email));
                message.setSubject("New password PasswordSecurity");
                String content = "Hey User,\n Here's your new password: " + newPassword + "\n Regardings PasswordSecurity";
                message.setContent(content, "text/html; charset=UTF-8");

                UserRepo.changePassword(email,MD5Password(newPassword));

                Transport.send(message);
                JOptionPane.showMessageDialog(null,"Mail is send to your adres");
            }

            else {
                JOptionPane.showMessageDialog(null,"Email adress is unknown!");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public String getSMTPPassword(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader( "smtp.txt"));
            String line = reader.readLine();
            return line;
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
