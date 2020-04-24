/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AdminAccController implements Initializable {

    @FXML
    private JFXButton parent;
    @FXML
    private JFXButton enfant;
    @FXML
    private JFXButton back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void parent(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
            parent.getScene().setRoot(root);
    }

    @FXML
    private void enfant(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminKids.fxml"));
            enfant.getScene().setRoot(root);
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            back.getScene().setRoot(root);
    }
    
}
