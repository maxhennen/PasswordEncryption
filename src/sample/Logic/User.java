package sample.Logic;

import sample.Data.UserSQLContext;
import sample.Interfaces.IUserUI;
import sample.Repos.UserRepository;

import javax.crypto.spec.SecretKeySpec;
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
    private SecretKeySpec Key;

    private UserRepository UserRepo;

    public String getName(){return Name;}
    public String getEmail(){return Email;}
    public String getPassword(){return Password;}

    public void setName(String name){Name = name;}
    public void setEmail(String email){Email = email;}
    public void setPassword(String password){Password = password;}

    public boolean newUser(String name, String email, String password){
        setName(name);
        setEmail(email);
        setPassword(MD5Password(password));
        saveNewKey();

        UserRepo = new UserRepository(new UserSQLContext());
        return UserRepo.newUser(this);
    }

    public void saveNewKey(){
        try {
            randomKey();
            ArrayList<String> lines = new ArrayList();
            String key = Base64.getEncoder().encodeToString(Key.getEncoded());
            lines.add(key);
            Path file = Paths.get(getEmail() + ".txt");
            Files.write(file, lines, Charset.forName("UTF-8"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void randomKey(){
        SecureRandom random = new SecureRandom();
        String key = new BigInteger(130,random).toString(32);
        Key = new SecretKeySpec(key.getBytes(),"Des");
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

    public boolean Login(String email, String password){
        UserRepo = new UserRepository(new UserSQLContext());

        if(UserRepo.loginUser(email,MD5Password(password)) != null){
            return true;
        }
        else {
            return false;
        }
    }
}
