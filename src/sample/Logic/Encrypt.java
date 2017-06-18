package sample.Logic;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by maxhe on 18-6-2017.
 */
public class Encrypt extends EncryptDecrypt {
    public void Encryption(SecretKeySpec key, byte[] input){
        try {
            getCipher().init(Cipher.ENCRYPT_MODE, key, getIvSpec());
            byte[] encrypted = new byte[getCipher().getOutputSize(input.length)];
            int encLength = getCipher().update(input,0,input.length,encrypted);
            encLength += getCipher().doFinal(encrypted,encLength);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
