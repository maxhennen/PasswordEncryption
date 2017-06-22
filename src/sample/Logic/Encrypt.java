package sample.Logic;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by maxhe on 18-6-2017.
 */
public class Encrypt  {

    private SecretKey Key;
    public SecretKey getKey(){return Key;}

    public String Encryption(byte[] input, SecretKey myKey){
        try {
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,myKey);
            byte[] encrypted = cipher.doFinal(input);
            return encrypted.toString();
    }

        catch (NoSuchPaddingException e){
            e.printStackTrace();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        catch (InvalidKeyException e){
            e.printStackTrace();
        }
        catch (IllegalBlockSizeException e){
            e.printStackTrace();
        }
        catch (BadPaddingException e){
            e.printStackTrace();
        }
        return null;
    }
    public SecretKey RandomKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            SecretKey myKey = keyGenerator.generateKey();
            return myKey;
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }
}
