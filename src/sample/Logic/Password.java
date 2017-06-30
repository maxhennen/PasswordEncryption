package sample.Logic;

import sample.Controllers.Controller;
import sample.Data.PasswordSQLContext;
import sample.Interfaces.IPasswordSQL;
import sample.Interfaces.IPasswordUI;
import sample.Repos.PasswordRepositry;

import java.util.ArrayList;

/**
 * Created by maxhe on 21-6-2017.
 */
public class Password implements IPasswordUI {
    private String Password;
    private String Content;

    private Encrypt Encrypt = new Encrypt();
    //private Decrypt Decryt;
    private PasswordRepositry PasswordRepo;

    public String getPassword(){return Password;}
    public void setPassword(String password){Password = password;}

    public String getContent(){return Content;}
    public void setContent(String content){Content = content;}

    public ArrayList<Password> passwordsUser(User user){
        PasswordRepo = new PasswordRepositry(new PasswordSQLContext());

        ArrayList<Password> passwords = new ArrayList<>();

        passwords.clear();

        for(sample.Logic.Password p: PasswordRepo.passwordUser(user.getEmail())){
            String encrypted = p.getPassword();
           // p.setPassword(Encrypt.Decryption(user.loadKey(user.getEmail()),encrypted));
            passwords.add(p);
        }

        return passwords;
    }

    public void newPassword(User user, String password, String content){
       // setPassword(Encrypt.Encryption(password,user.loadKey(user.getEmail())));
        setContent(content);
        Encrypt.Start();
        PasswordRepo = new PasswordRepositry(new PasswordSQLContext());
        PasswordRepo.newPassword(user,this);
    }
}
