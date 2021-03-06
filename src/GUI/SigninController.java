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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

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
    private Button plus;

    private Text existErr;

    private Text videErr;

    private Text VerifPwdErr;

    private Text pwdErr;

    private Text msgerreur;

    private Text emailErr;
    @FXML
    private VBox pane_main_grid;
    @FXML
    private JFXTextField cin_field;
    @FXML
    private JFXTextField child_field;
    @FXML
    private JFXButton authf;

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
        if (email_field.getText().equals("")
                || username_field.getText().equals("")
                || password_field.getText().equals("")
                || repeatpassword_field.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("Champs vides");

            alert.showAndWait();
            error = true;
        } else {

            if (!validate(email_field.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Look, an Error Dialog");
                alert.setContentText("Ooops, Vérifier votre mail!");

                alert.showAndWait();
                error = true;
            }  
            else if (username_field.getLength() < 3 || username_field.getLength() > 10) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Look, an Error Dialog");
                alert.setContentText("Ooops, username doit contenir au minimum 3 caractères et maximum 10!");

                alert.showAndWait();
                error = true;
            } 

            else if (!PasswordStrong(password_field.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Look, an Error Dialog");
                alert.setContentText("Ooops, Le nombre de caractéres de mot de passe doit étre entre 4 et 15, et comporter des lettres et des chiffres(des minuscules ET des majuscules)!");

                alert.showAndWait();

                error = true;
            } 

            else if (cin_field.getLength() != 8) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Look, an Error Dialog");
                alert.setContentText("Ooops,Verifier votre cin");

                alert.showAndWait();
                error = true;
            } 

            else if (repeatpassword_field.getText().equals(password_field.getText())) {
                error = false;

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Look, an Error Dialog");
                alert.setContentText("Vérifier votre mot de passe!");

                alert.showAndWait();
                error = true;
            }
        }

        if (!error) {

            UService = new UserService();
            if (UService.Signin(username_field.getText(), email_field.getText(), password_field.getText())) {
                Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
                signin.getScene().setRoot(root);// 
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Look, an Error Dialog");
                alert.setContentText("utilisateur existe deja!");

                alert.showAndWait();
            }
        }

    }

    @FXML
    private void Cnx_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        cnx.getScene().setRoot(root);
    }

    /**
     * private void Close_action(MouseEvent event) { System.exit(0); }*
     */
    public static boolean validate(String email) {
        final String EMAIL_VERIFICATION = "^([\\w-\\.]+)@([\\w\\.]+)\\.([a-z]){2,}$";
        Pattern pattern = Pattern.compile(EMAIL_VERIFICATION);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }

    public static boolean PasswordStrong(String pass) {
        String expresion = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])";
        Pattern patron = Pattern.compile(expresion);
        Matcher m = patron.matcher(pass);
        return m.find();

    }

    @FXML
    private void authUser(ActionEvent event) throws IOException, SQLException {
        String domaine = "https://nouhanoreply.wixsite.com/website";
        String appId = "569917590593517";
        String autUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + appId + "&redirect_uri=" + domaine + "&scope=email,public_profile";
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(autUrl);
        String accessToken;
        while (true) {

            if (!driver.getCurrentUrl().contains("facebook.com")) {
                String url = driver.getCurrentUrl();
                accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
                accessToken = accessToken.substring(0,accessToken.indexOf("&"));

                
                driver.quit();
                driver.quit();
                driver.quit();

                FacebookClient fbClient = new DefaultFacebookClient(accessToken);
                User user = fbClient.fetchObject("me", User.class);

                if (user.getId().length() != 0) {
                    UService = new UserService();
                    if (UService.SigninByfb(user.getName(), user.getId())) {
                        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
                        authf.getScene().setRoot(root);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Look, an Error Dialog");
                alert.setContentText("utilisateur existe deja!");

                alert.showAndWait();
                       
                    }
                }
            }

        }
    }

    @FXML
    private void AddTextField(ActionEvent event) {
        JFXTextField newField = new JFXTextField();
        pane_main_grid.getChildren().add(newField);
    }


}
