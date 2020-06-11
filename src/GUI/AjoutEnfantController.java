/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Enfant;
import Entities.Session;
import Services.EnfantService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.sql.Date;
import java.util.Properties;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;

import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjoutEnfantController implements Initializable {

    @FXML
    private JFXTextField name;
    @FXML
    private JFXDatePicker datenaissance;
    @FXML
    private JFXTextField lieux;
    @FXML
    private JFXComboBox<String> garderie;
    @FXML
    private JFXComboBox<String> classe;
    @FXML
    private JFXTextArea info;
    @FXML
    private JFXButton add;
    @FXML
    private JFXButton back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        garderie.getItems().addAll("temps partiel", "Plein-temps");
        classe.getItems().addAll("classe préscolaire", "garderie", "classe maternelle");
    }

    @FXML
    private void Add_Action(ActionEvent event) throws SQLException, IOException, MessagingException {
        Session s = new Session();

        Date d = Date.valueOf(datenaissance.getValue());
       
        java.sql.Date dnow = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        long diff = dnow.getYear() - d.getYear();
        //Boolean error = false;
        if (name.getText().equals("")
                || lieux.getText().equals("")
                || garderie.getSelectionModel().getSelectedItem().equals("")
                || classe.getSelectionModel().getSelectedItem().equals("")
                //|| datenaissance.getValue().equals(null)
                ) {
                        showMessageDialog(null, "champs vides");

            
        }

        else if (classe.getSelectionModel().getSelectedItem().equals("classe préscolaire") && diff != 5) {
            showMessageDialog(null, "l'age de votre enfant est " + diff + " ans ,pour inscrire votre enfant en classe preparatoire il doit etre agé 5 ans");
        } else if (classe.getSelectionModel().getSelectedItem().equals("classe maternelle") && diff != 4) {
            showMessageDialog(null, "l'age de votre enfant est " + diff + " ans,pour inscrire votre enfant en classe maternelle il doit etre agé  4 ans");
        } else if (classe.getSelectionModel().getSelectedItem().equals("garderie") && diff != 3 && diff != 2) {
            showMessageDialog(null, "l'age de votre enfant est " + diff + " ans,pour inscrire votre enfant en garderie il doit etre agé entre 2 et 3 ans");
        }  else {
            EnfantService Eservices = new EnfantService();
            Enfant e = new Enfant(name.getText(), d, lieux.getText(), garderie.getSelectionModel().getSelectedItem(), classe.getSelectionModel().getSelectedItem(), info.getText(), s.getId());
            Eservices.insert(e);
            //Parent root = FXMLLoader.load(getClass().getResource("ListeEnfant.fxml"));
            //add.getScene().setRoot(root);

            //send Mail
            mailling("nouha.benfarhat@esprit.tn");
            showMessageDialog(null, "Votre enfant est bien inscrit,un mail de confirmation a été envoyé a votre boite mail");
            ((Node) event.getSource()).getScene().getWindow().hide();
            Parent parent = FXMLLoader.load(getClass().getResource("ListeEnfant.fxml"));
            Stage stage = new Stage();
            Scene sc = new Scene(parent);
            stage.setScene(sc);
            stage.show();
        }

    }

    public static void mailling(String recipient) throws MessagingException {

       

        Properties properties = new Properties();//is a key value store
        properties.put("mail.smtp.auth", "true");//defines whether a note indication is nedded for rmail server
        properties.put("mail.smtp.starttls.enable", "true");//for tls encryption
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
         //authentication info
         String username = "nouha.noreply@gmail.com";//eli yab3eth
         String password = "PIDEV2020";
        //String fromEmail = "test.nom2020@gmail.com";

        javax.mail.Session session = javax.mail.Session.getInstance(properties, new Authenticator() {//we use session to login
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }

        });
        //Start our mail message
        Message message = prepareMessage(session, username, recipient);
        Transport.send(message);
        System.out.println("email sent successfuly");
        
    }   
        private static Message prepareMessage(javax.mail.Session session,String username,String recipient)
        {
        try {
            MimeMessage msg = new MimeMessage(session);//instancier mon msg using the session

            msg.setFrom(new InternetAddress(username));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            msg.setSubject("inscription");
            msg.setText("Votre enfant est bien inscrit !");
            return msg;


        } catch (MessagingException ex) {
            
            
        }
        // TODO Auto-generated catch block
        return null;

    }


    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Parent.fxml"));
        back.getScene().setRoot(root);

    }

}
