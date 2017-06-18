package sample.Logic;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

/**
 * Created by maxhe on 18-6-2017.
 */
public abstract class EncryptDecrypt {
    private byte[] keyBytes;
    private byte[] ivBytes;
    private Cipher cipher;
    IvParameterSpec ivSpec;

    public Cipher getCipher(){return cipher;}
    public IvParameterSpec getIvSpec(){return ivSpec;}

    public void setEncryptDecrypt(){
        try {
            ivSpec = new IvParameterSpec(ivBytes);
            cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        catch (NoSuchPaddingException e){
            e.printStackTrace();
        }
    }
}
