package com.ar.revaes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {

@FXML
    private Button logoutButton;
@FXML
    private Label welcomeText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        logoutButton.setOnAction(event ->
                AppUtil.switchScene("hello-view.fxml",event,null));
    }
    public void welcomeUser(String email){
        String user = email.substring(0,email.indexOf("@")-1);
        welcomeText.setText("User: " + user);
    }
}
