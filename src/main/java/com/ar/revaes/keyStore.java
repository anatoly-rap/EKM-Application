package com.ar.revaes;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class keyStore {
    public keyStore() {
    }
    public void ksGen(String alias, String filename, String password) throws NoSuchAlgorithmException, CertificateException, KeyStoreException {
        //generate new keystore
        KeyStore ks = KeyStore.getInstance("JKES");
        char[] pass = password.toCharArray();
        File Filename = new File(filename);
        try{
            if(!Filename.exists()){
                ks.load(null, pass);
                FileOutputStream fos = new FileOutputStream(filename);
                ks.store(fos, pass);
                fos.close();
            }else{
                FileInputStream fis = new FileInputStream(filename);
                ks.load(fis,pass);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        if(!ks.containsAlias(alias)){
            SecretKey sk = KeyGen.getAESKey();
            ks.setEntry(alias,new KeyStore.SecretKeyEntry(sk),new KeyStore.PasswordProtection(pass));
        }
    }
}


