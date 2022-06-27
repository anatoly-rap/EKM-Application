package com.ar.revaes;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;
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
    private static InetAddress userInet() throws UnknownHostException {//initial use case, will work for most standard personal/home computer's networks
        return InetAddress.getLocalHost();
    }
    private static Boolean networkInterfaces() throws SocketException, UnknownHostException {   //test case for multiple interfaces,seeing if userInet() is functional(not a VM or something)
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while(interfaces.hasMoreElements()){
            NetworkInterface networkInterface = interfaces.nextElement();
            if(Collections.list(networkInterface.getInetAddresses()).contains(userInet()) && networkInterface.isUp()){ //check for userInet methods.
                return true;
                }
            }
            return false;
    }
    //need to link MAC with user credentials
    private static byte[] getMAC(InetAddress address) throws SocketException{
        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(address);
        return networkInterface.getHardwareAddress();
    }
    public static void main(String[] args) throws UnknownHostException, SocketException { //testing purposes
        System.out.println("test ip: " + userInet());
        System.out.println("testing Network Interfaces Test:  "+ networkInterfaces().toString());
    }
}