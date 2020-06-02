/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Session;
import Entities.User;
import Services.UserService;
import static GUI.AdminController.staticUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class updateUserController implements Initializable {

    @FXML
    private JFXTextField usernamee;
    @FXML
    private JFXTextField emaill;
    @FXML
    private JFXTextField enabledd;
    @FXML
    private JFXTextField rolee;
    @FXML
    private JFXButton update;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        usernamee.setText(staticUser.getUsername());
        emaill.setText(staticUser.getEmail());
        //enabled.setText(staticUser.getEnabled()));
        rolee.setText(staticUser.getRole());
        //datenaissance.setValue((staticEnfant.getDate_naissance());

    }

    @FXML
    private void BtnUpdate_action(ActionEvent event) throws IOException {
        staticUser.setUsername(usernamee.getText());
        staticUser.setEmail(emaill.getText());
        staticUser.setEnabled(Integer.parseInt(enabledd.getText()));

        staticUser.setRole(rolee.getText());

        UserService services = new UserService();
        System.out.print(staticUser);
            //User U = new User(staticUser.getId(), usernamee.getText(), emaill.getText(),Integer.parseInt(enabledd.getText()),rolee.getText());
            //UserService.update(staticUser);
        services.update(staticUser);
        showMessageDialog(null, "le profil est modifié");
        ((Node) event.getSource()).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        Stage stage = new Stage();
        Scene sc = new Scene(parent);
        stage.setScene(sc);
        stage.show();
    }

}
