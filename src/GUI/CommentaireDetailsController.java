package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entities.Commentaire;
import Services.EvennementServices;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class CommentaireDetailsController implements Initializable {
    public static ObservableList comment ;
    @FXML
    private ListView comments;
    @FXML
    private TextField tText;
    @FXML
    private Button env;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comment= FXCollections.observableArrayList();
        EvennementServices services = new EvennementServices();
        
        comments.setItems(comment);
    } 
    
    @FXML
    private void send (ActionEvent event) throws Exception {
        Commentaire c = new Commentaire();
        
        c.setId_user(7);
        c.setText(tText.getText());
        EvennementServices services = new EvennementServices();
        services.insertComment(c);
        showMessageDialog(null, "Success");
     
    }
    
}
