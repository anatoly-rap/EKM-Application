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

public class AppUtil {  //helper class for changing scene, SQL, onAction/Events

    private static final String DB_URL = "";
    private static final String DB_USERNAME = "";
    private static final String DB_PASSWORD = "";


    public static void signUp(String email, String password, ActionEvent e) throws SQLException {
        Connection con = null;
        PreparedStatement psGen = null;
        PreparedStatement psCheck = null;
        ResultSet rs = null;

            try{
                con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                psCheck = con.prepareStatement("SELECT * FROM users WHERE username = ?");
                psCheck.setString(1, email);
                rs = psCheck.executeQuery();
             if(!rs.next()){        //empty resultSet- email not in table
                 psGen = con.prepareStatement("INSERT INTO users (?, ?)");
                 psGen.setString(1,email);
                 psGen.setString(2,password);
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
    public static void login(String email, String password) throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
            try{
                con = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
                ps = con.prepareStatement("SELECT password FROM user WHERE username = ?");
                ps.setString(1,email);
                rs = ps.executeQuery();     //eQ for SELECT statements, returns resultSet
            }catch(SQLException e){
        e.printStackTrace();
        }
    }

    public static void switchScene(String FXML,ActionEvent ev, String user){
        Parent rtMain = null;
        if(user != null){
            try{
                FXMLLoader loadIn = new FXMLLoader(AppUtil.class.getResource(FXML));
                rtMain = loadIn.load();
                LoggedInController loggedIn = loadIn.getController();
                loggedIn.welcomeUser(user);
            }catch(IOException e){
                e.printStackTrace();
        }
    }else{
        try{
            rtMain = FXMLLoader.load(AppUtil.class.getResource(FXML));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    Stage stg = (Stage) ((Node) ev.getSource()).getScene().getWindow();
        stg.setScene(new Scene(rtMain, 600, 400));
            stg.show();
    }
}
