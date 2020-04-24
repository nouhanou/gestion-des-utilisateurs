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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ParentController implements Initializable {

   
    @FXML
    private JFXButton enfant_inscri;
    @FXML
    private JFXButton profil_enf;
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
    private void enfant_inscri(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AjoutEnfant.fxml"));
            enfant_inscri.getScene().setRoot(root);
    }

   

    @FXML
    private void profil_enf(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("ListeEnfant.fxml"));
            profil_enf.getScene().setRoot(root);
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            back.getScene().setRoot(root);
    }
    
}
