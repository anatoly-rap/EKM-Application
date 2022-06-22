package com.ar.revaes;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class UserAuth {

public static String saltGen(String username) throws NoSuchAlgorithmException {
    byte[] toByte = new byte[username.length()];
    SecureRandom.getInstanceStrong().nextBytes(toByte);
    return new String(toByte);
    }



}
