/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Session;
import Entities.User;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
public class updateUserController implements Initializable {

    @FXML
    private JFXButton btn_all;
    @FXML
    private JFXButton deleteUser;
    @FXML
    private JFXButton addUser;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField enabled;
    @FXML
    private JFXTextField role;
     @FXML
    private JFXButton update;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void All_action(ActionEvent event) {
    }

    @FXML
    private void deleteUser_action(ActionEvent event) {
    }

    @FXML
    private void addUser_action(ActionEvent event) {
    }

    @FXML
    private void BtnUpdate_action(ActionEvent event) throws IOException {
        Session s = new Session();
        UserService FS = new UserService();
        User f;
        f = new User(username.getText(), email.getText(), Integer.parseInt(enabled.getText()), role.getText());
        FS.update(AdminController.id_ads, f);
        Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        update.getScene().setRoot(root);
    }
    
}
