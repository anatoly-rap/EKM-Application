package com.ar.revaes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label title;
    @FXML
    private TextField emailLogin;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registerButton.setOnAction(event ->
                Utilities.switchScene("register.fxml",event,null));

        loginButton.setOnAction(event ->
                Utilities.login(emailLogin.getText(),passwordField.getText(),event));
    }
}