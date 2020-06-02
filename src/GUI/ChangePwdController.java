/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Session;
import Entities.User;
import Services.UserService;
import com.jfoenix.controls.JFXPasswordField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ChangePwdController implements Initializable {

    @FXML
    private JFXPasswordField old;
    @FXML
    private JFXPasswordField repeatNew;
    @FXML
    private Button change;
    @FXML
    private JFXPasswordField newp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void chaange_action(ActionEvent event) throws SQLException {
        Parent fxml;
        UserService ser = new UserService();
        Session session = new Session();
        String password = session.getPassword();
                int id = session.getId();
        if ((BCrypt.checkpw(old.getText(), password)) && (newp.getText().equals(repeatNew.getText()))) {
            try {
                System.out.println(id);

                ser.updatePassword(BCrypt.hashpw(newp.getText(), BCrypt.gensalt(13)), id);
                Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Password changed");
            info.setContentText("votre mot de passe a été changé avec succées");
            info.show();
                

                Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
                change.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println("error login");
            }
        } else {
            Alert info = new Alert(Alert.AlertType.WARNING);
            info.setTitle("Password Error");
            info.setContentText("Vérifier votre mot de passe");
            info.show();
        }
    }


}
