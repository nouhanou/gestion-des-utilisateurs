/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import Entities.Session;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author HP
 */
public class LoginController implements Initializable {

    UserService UService;
    private Label label;
    @FXML
    private Text msgerreur;
    @FXML
    private Text nullErr;
    @FXML
    private JFXButton cnx;
    @FXML
    private JFXButton auth;
    @FXML
    private JFXTextField username_field;
    @FXML
    private JFXPasswordField password_field;
    @FXML
    private JFXButton signin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void seConnecter_action(ActionEvent event) throws SQLException, IOException {
        Boolean error = false;

        System.out.println("login: " + username_field.getText());
        System.out.println("password_field: " + password_field.getText());

        if (username_field.getText().equals("") || password_field.getText().equals("")) {
            nullErr.setVisible(true);
            error = true;
        } else {
            nullErr.setVisible(false);
        }

        if (!error) {
            int x = 0;
            UService = new UserService();
            if (UService.login(username_field.getText(), password_field.getText())) {
                Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));

                cnx.getScene().setRoot(root);

            } else {
                msgerreur.setVisible(true);
            }
        }
    }

    @FXML
    private void Inscrir_action(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Signin.fxml"));
        cnx.getScene().setRoot(root);
    }

    private void Close_action(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void authUser(ActionEvent event) throws IOException, SQLException {
        String domaine = "https://kawami.io";
        String appId = "353823941796971";

        String autUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + appId + "&redirect_uri=" + domaine + "&scope=email,public_profile";
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
      
        driver.get(autUrl);
        String accessToken;
        while (true) {

            if (!driver.getCurrentUrl().contains("facebook.com")) {
                String url = driver.getCurrentUrl();
                accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");

                driver.quit();
                driver.quit();
                driver.quit();
                driver.quit();

                FacebookClient fbClient = new DefaultFacebookClient(accessToken);
                com.restfb.types.User user = fbClient.fetchObject("me", com.restfb.types.User.class);

                if (user.getId().length() != 0) {
                    UService = new UserService();
                    if (UService.loginByfb(user.getName(), user.getId())) {
                        Parent root = FXMLLoader.load(getClass().getResource("Users.fxml"));
                        cnx.getScene().setRoot(root);
                    } else {
                        msgerreur.setVisible(true);
                    }
                }
            }

        }

    }
}
