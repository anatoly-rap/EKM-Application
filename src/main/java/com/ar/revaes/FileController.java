package com.ar.revaes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class FileController implements Initializable {
    protected ArrayList<String> strData = new ArrayList<>();
    FileChooser fileChooser = new FileChooser();
    @FXML
    private TextArea textArea;
    @FXML
    private Button encryptText;
    @FXML
    private Button importText;

    public void getText(ActionEvent ev){
        String line;
        File file = fileChooser.showOpenDialog(new Stage());
            try{
                Scanner scan = new Scanner(file);
                while(scan.hasNextLine()){
                    line = scan.nextLine();
                    strData.add(line);
                    textArea.appendText(line + '\n');
                }
            }catch(FileNotFoundException e) {
            e.printStackTrace();
            }
        }
    @Override
    public void initialize(URL location, ResourceBundle resources) {    //may need to make another scene switch
            importText.setOnAction(this::getText);
    }
}