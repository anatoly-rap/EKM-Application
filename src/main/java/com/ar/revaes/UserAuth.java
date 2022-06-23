package com.ar.revaes;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class UserAuth {

public static String saltGen(String username) throws NoSuchAlgorithmException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
    byte[] byteArray = username.getBytes();
    byte[] myvar = "kseil".getBytes(); //additional salt padding
        try {
            outputStream.write(myvar);
        }catch(IOException e){
        e.printStackTrace();
        }try{
            outputStream.write(byteArray);
        }catch(IOException e){
        e.printStackTrace();
    }
    byte[] salt = outputStream.toByteArray();
    return new String(salt);
        }
    }

