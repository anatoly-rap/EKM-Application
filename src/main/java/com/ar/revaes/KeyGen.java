package com.ar.revaes;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class KeyGen {
    private static final SecureRandom sRandom = new SecureRandom();
    public static SecretKey getAESKey() throws NoSuchAlgorithmException{
        int size = 256;
        KeyGenerator aesKey = KeyGenerator.getInstance("AES");
        aesKey.init(size,sRandom);
        return aesKey.generateKey();
    }
    public static KeyPair getRSAKeys() throws NoSuchAlgorithmException{
        KeyPairGenerator pairGenerator = KeyPairGenerator.getInstance("RSA");
        pairGenerator.initialize(2048,sRandom);
        return pairGenerator.generateKeyPair();
    }
}
