/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Session;
import Services.EmailService;
import Services.UserService;
import Entities.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class forgetController implements Initializable {

    @FXML
    private JFXTextField email;
    @FXML
    private JFXButton forget;
    @FXML
    private AnchorPane bck;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                //email.isDisabled();

    }    

    @FXML
    private void forget_Action(ActionEvent event) throws IOException, MessagingException, SQLException {
            Parent fxml;
            EmailService ser = new EmailService();
            //User.code=ser.getcode();
            User.code=ser.getcode();
            System.out.println(User.user);
            if (ser.VerificationEmail(email.getText())){
                
              
                    User.user=ser.getUser(email.getText());
                    
                
                    
                   // ser.sendEmail(String.valueOf(User.code),"Password",email.getText());
                    //EmailService.sendMail(email.getText(),"Password",String.valueOf(User.code));
                    
                    
                    Parent root = FXMLLoader.load(getClass().getResource("Code.fxml"));
                    forget.getScene().setRoot(root);
                
            }else{
                Alert info = new Alert(Alert.AlertType.ERROR);
                info.setTitle("Email");
                info.setContentText("Email n'existe pas");
                info.show();
            }
        } 
    }
    

