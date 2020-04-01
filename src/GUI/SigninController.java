/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 *
 * @author HP
 */
public class SigninController implements Initializable {

    UserService UService;
    @FXML
    private JFXButton signin;

    @FXML
    private JFXTextField username_field;
    @FXML
    private JFXPasswordField password_field;
    @FXML
    private JFXTextField email_field;
    @FXML
    private JFXPasswordField repeatpassword_field;
    @FXML
    private JFXButton cnx;
    @FXML
    private Text existErr;
    @FXML
    private Text videErr;
    @FXML
    private Text VerifPwdErr;
   /* @FXML
    private Text pwdErr;
    @FXML
    private Text msgerreur;
    @FXML
    private Text emailErr;
   */

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Inscrir_action(ActionEvent event) throws IOException, SQLException {
        Boolean error = false;
        if (email_field.getText().equals("") || username_field.getText().equals("") || password_field.getText().equals("") || repeatpassword_field.getText().equals("")) {
            videErr.setVisible(true);
            error = true;
        } else {
            videErr.setVisible(false);
        
            if (!validate(email_field.getText())) {
              //  emailErr.setVisible(true);
                error = true;
            } else {
            //    emailErr.setVisible(false);
            }
            if (username_field.getLength() < 3 || username_field.getLength() > 15) {
               // videErr.setVisible(true);
                error = true;
            } else {
                videErr.setVisible(false);
            }
            if (!PasswordStrong(password_field.getText())) {
          //      pwdErr.setVisible(true);
                error = true;
            } else {
          //      pwdErr.setVisible(false);
            }
            if (repeatpassword_field.getText().equals(password_field.getText())) {
                VerifPwdErr.setVisible(false);

            } else {
                VerifPwdErr.setVisible(true);
                error = true;
            }
        }

        if (!error) {
            //int x=0;
            UService = new UserService();
            if (UService.Signin(username_field.getText(), email_field.getText(), password_field.getText())) {
                Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
                signin.getScene().setRoot(root);// 
            } else {
                existErr.setVisible(true);
            }
        }

    }


    @FXML
    private void Cnx_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        cnx.getScene().setRoot(root);
    }

    private void Close_action(MouseEvent event) {
        System.exit(0);
    }

    public static boolean validate(String email) {
        final String EMAIL_VERIFICATION = "^([\\w-\\.]+)@([\\w\\.]+)\\.([a-z]){2,}$";
        Pattern pattern = Pattern.compile(EMAIL_VERIFICATION);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }

    public static boolean PasswordStrong(String pass) {
        String expresion = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        Pattern patron = Pattern.compile(expresion);
        Matcher m = patron.matcher(pass);
        if (m.find()) {
            return true;
        }
        return false;
    }
    //AnchorErrorN7.setVisible(false);
    //TextEmail.getText().equals("")

    

}
