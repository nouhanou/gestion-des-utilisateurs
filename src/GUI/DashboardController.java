package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entities.Session;
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
public class DashboardController implements Initializable {
    @FXML
    private JFXButton para;
    @FXML
    private JFXButton pass;
    @FXML
    private JFXButton stat;
    @FXML
    private JFXButton logout;
   

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ParametreAction(ActionEvent event) throws IOException {
        Session session = new Session();
        String role = session.getRole();
        System.out.println("role: " + role);
        System.out.println("role: " + role.length());
        if (role.length() == 6) {
            System.out.println("simple user");
            System.out.println("admin");
            Parent root = FXMLLoader.load(getClass().getResource("Parent.fxml"));
            para.getScene().setRoot(root);
            
        } else {
            System.out.println("admin");
            Parent root = FXMLLoader.load(getClass().getResource("AdminAcc.fxml"));
            para.getScene().setRoot(root);
        }
    }

    @FXML
    private void evenementAction(ActionEvent event) {
    }

    @FXML
    private void pass(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ChangePwd.fxml"));
            pass.getScene().setRoot(root);
    }

    @FXML
    private void stat(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("Admin_stat.fxml"));
            pass.getScene().setRoot(root);
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            logout.getScene().setRoot(root);
    }

    

    
}
