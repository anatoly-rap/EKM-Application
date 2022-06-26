package com.ar.revaes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

@FXML
    private Button loginUsers;
@FXML
    private Button regUsers;
@FXML
    private TextField newEmail;
@FXML
    private PasswordField newPass;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginUsers.setOnAction(event ->
                Utilities.switchScene("hello-view.fxml",event,null));
        regUsers.setOnAction(event ->
                Utilities.signUp(newEmail.getText(),newPass.getText(),event));
    }
}
