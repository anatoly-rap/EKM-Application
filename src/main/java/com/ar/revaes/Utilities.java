package com.ar.revaes;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Utilities extends UserAuth{  //helper class for changing scene, SQL, onAction/Events

    private static final String DB_URL = " "; //your SQL DB link
    private static final String DB_USERNAME = "root"; //master, root by default
    private static final String DB_PASSWORD = " "; //master pass

    public static void signUp(String email, String password, ActionEvent e){
        Connection con = null;
        PreparedStatement psGen = null;
        PreparedStatement psCheck = null;
        ResultSet rs = null;
            try{
                con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                psCheck = con.prepareStatement("SELECT * FROM app_users WHERE user_email = ?");
                psCheck.setString(1, email);
                rs = psCheck.executeQuery();
             if(!rs.next()){        //empty resultSet- email not in table
                 psGen = con.prepareStatement("INSERT INTO app_users (user_email, pass) VALUES (?, ?)");
                 psGen.setString(1,email);
                 psGen.setString(2,hashGen(password));
                 psGen.executeUpdate(); // eU for INSERT/DELETE, DML(Data Manipulation Lang.)
                 switchScene("LoggedIn.fxml", e,email);
             }else{
                 do{
                 Alert emailTaken  = new Alert(Alert.AlertType.ERROR);
                 emailTaken.setContentText("email is already is registered");
                 emailTaken.show();
                }while(rs.next());
             }
            }catch(SQLException ex){
                ex.printStackTrace();
                // note: AutoCloseable in JDK 7+ close out connection, preparedStatements to prevent memory leakage
            }finally{
                if(con != null){
                    try{
                        con.close();
                    }catch(SQLException exception){
                        exception.printStackTrace();
                    }
                }if(psCheck != null){
                     try{
                        psCheck.close();
                     }catch(SQLException exception){
                         exception.printStackTrace();
                     }
                }if(psGen != null){
                    try{
                        rs.close();
                    }catch(SQLException exception){
                        exception.printStackTrace();
                    }
                }
            }
    }
    public static void login(String email, String password, ActionEvent event){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
            try{
                con = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
                ps = con.prepareStatement("SELECT pass FROM app_users WHERE user_email = ?");
                ps.setString(1,email);
                rs = ps.executeQuery(); //eQ for SELECT statements, returns resultSet
                if(!rs.next()){
                    Alert notFound  = new Alert(Alert.AlertType.ERROR);
                    notFound.setContentText("existing user email not registered");
                    notFound.show();
                }else{
                    String getHash;
                    do{
                        getHash = rs.getString("pass"); //hash from DB
                    }while(rs.next());
                    if(loginAuth(password,getHash)){
                        switchScene("LoggedIn.fxml",event,email);
                    }else{
                        Alert loginError  = new Alert(Alert.AlertType.ERROR);
                        loginError.setContentText("Incorrect password entered");
                        loginError.show();
                    }
                }
            }catch(SQLException e){
        e.printStackTrace();
        }finally{
                if(con != null){
                        try{
                            con.close();
                        }catch(SQLException exception){
                            exception.printStackTrace();
                        }
                    }if(ps != null){
                    try{
                        ps.close();
                    }catch(SQLException exception){
                        exception.printStackTrace();
                    }
                }if(rs != null){
                    try{
                        rs.close();
                    }catch(SQLException exception){
                        exception.printStackTrace();
                    }
                }
            }
    }
    public static void switchScene(String FXML,ActionEvent ev, String user){
        Parent rtMain = null;
        if(user != null){
            try{
                FXMLLoader loadIn = new FXMLLoader(Utilities.class.getResource(FXML));
                rtMain = loadIn.load();
                LoggedInController loggedIn = loadIn.getController();
                loggedIn.welcomeUser(user);
            }catch(IOException e){
                e.printStackTrace();
        }
    }else{
        try{
            rtMain = FXMLLoader.load(Utilities.class.getResource(FXML));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    Stage stg = (Stage) ((Node) ev.getSource()).getScene().getWindow();
        stg.setScene(new Scene(rtMain, 600, 400));
            stg.show();
    }
}