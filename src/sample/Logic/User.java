package sample.Logic;

import sample.Data.UserSQLContext;
import sample.Interfaces.IUserUI;
import sample.Repos.UserRepository;

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
import java.util.ArrayList;
import java.util.Base64;
import java.util.SplittableRandom;

/**
 * Created by maxhe on 18-6-2017.
 */
public class User implements IUserUI {
    private String Name;
    private String Email;
    private String Password;
    private SecretKey Key;
    private UserRepository UserRepo;

    public String getName(){return Name;}
    public String getEmail(){return Email;}
    public String getPassword(){return Password;}
    public SecretKey getKey(){return Key;}

    public void setName(String name){Name = name;}
    public void setEmail(String email){Email = email;}
    public void setPassword(String password){Password = password;}
    private Encrypt Encrypt = new Encrypt();

    public User newUser(String name, String email, String password){
        setName(name);
        setEmail(email);
        setPassword(MD5Password(password));
        UserRepo = new UserRepository(new UserSQLContext());
        saveKey();
        return UserRepo.newUser(this);
    }

    public void saveKey(){
        try {
            ArrayList<String> lines = new ArrayList();
            String key = Base64.getEncoder().encodeToString(Encrypt.RandomKey().getEncoded());
            lines.add(key);
            Path file = Paths.get(getEmail() + ".txt");
            Files.write(file, lines, Charset.forName("UTF-8"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
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
        return UserRepo.loginUser(email,MD5Password(password));
    }

    public SecretKey loadKey(String email){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(email + ".txt"));
            String line = reader.readLine();
            byte[] decode = Base64.getDecoder().decode(line);
            Key = new SecretKeySpec(decode,0,decode.length,"DES");
            return Key;
        }

        catch (FileNotFoundException e){
            return null;
        }

        catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
