package com.ar.revaes;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class UserAuth {
    private static final Argon2PasswordEncoder a2Encoder = new Argon2PasswordEncoder(
            16,
            32,
              1,
            7500,
            3);
    public static String hashGen(String password){
    return a2Encoder.encode(password);
    }
    public static Boolean loginAuth(String raw, String hashed){
        return a2Encoder.matches(raw, hashed);
    }
}
