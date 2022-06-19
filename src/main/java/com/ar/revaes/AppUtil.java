package com.ar.revaes;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;

public class AppUtil {  //helper class for changing scene, SQL, onAction/Events

    private static final String DB_URL = "";	//Your SQL URL
    private static final String DB_USERNAME = "";	//server username, generally root by default
    private static final String DB_PASSWORD = "";	//server pw

    public static void signUp(String email, String password, ActionEvent e) throws SQLException {
        PreparedStatement psGen = null;
        PreparedStatement psCheck = null;
        ResultSet resultset = null;

            try(Connection con = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD)){
            psCheck = con.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheck.setString(1,email);
            }catch(SQLException ex){
                ex.printStackTrace();

            }
    }

    public static void login(String email, String password){


    }

    public static void switchScene(String FXML,ActionEvent ev, String user){
    Parent rtMain = null;
    if(user != null){
        try{
            FXMLLoader loadIn = new FXMLLoader(AppUtil.class.getResource(FXML));
            rtMain = loadIn.load();
            LoggedInController loggedIn = loadIn.getController();
            loggedIn.welcomeUser(user);

        }catch (IOException e){
            e.printStackTrace();
        }
    }else{
        try{
            rtMain = FXMLLoader.load(AppUtil.class.getResource(FXML));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    Stage stg = (Stage) ((Node) ev.getSource()).getScene().getWindow();
        stg.setScene(new Scene(rtMain, 600, 400));
            stg.show();
    }
}
