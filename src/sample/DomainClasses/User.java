package sample.DomainClasses;

import sample.Interfaces.IUserUI;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public String getName(){return Name;}
    public String getEmail(){return Email;}
    public String getPassword(){return Password;}

    public void setName(String name){Name = name;}
    public void setEmail(String email){Email = email;}
    public void setPassword(String password){Password = password;}

    public void newUser(String name, String email, String password){
        setName(name);
        setEmail(email);
        setPassword(password);
        newKey();
    }

    public void newKey(){
        try {
            byte[] keyBytes = new byte[2];
            Key = new SecretKeySpec(keyBytes,"Des");

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
}
