/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class CodeController implements Initializable {

    @FXML
    private JFXTextField code;
    @FXML
    private Button check;

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void code(ActionEvent event) {
    }

    @FXML
    private void check(ActionEvent event) throws IOException {
        Parent fxml;
        if (Integer.parseInt(code.getText()) == User.code) {
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Check");
            info.setContentText("Done");
            info.show();

            Parent root = FXMLLoader.load(getClass().getResource("ChangePwd.fxml"));
            check.getScene().setRoot(root);

        }
    }

}
