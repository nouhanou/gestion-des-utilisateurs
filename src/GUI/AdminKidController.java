/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import Services.EnfantService;
import Entities.Commentaire;
import Entities.Enfant;
import Entities.Session;
import static GUI.AdminKidsController.staticEnfant;
import Services.EnfantService;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AdminKidController implements Initializable {

    public static ObservableList comment;

    @FXML
    private JFXListView<Commentaire> comments;
    @FXML
    private JFXTextArea tText;
    @FXML
    private JFXButton send;
    @FXML
    private JFXButton back;
    @FXML
    private Label lname;
    @FXML
    private Label linfo;
    @FXML
    private Label lclasse;
    @FXML
    private Label lgarderie;
    @FXML
    private Label lplace;
    @FXML
    private Label ldate;
    @FXML
    private JFXButton banir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            // TODO
            comment = FXCollections.observableArrayList();
            lname.setText(staticEnfant.getName());
            lplace.setText(staticEnfant.getPlace());
            ldate.setText(staticEnfant.getDate_naissance().toString());
            lgarderie.setText(staticEnfant.getGarderie());
            lclasse.setText(staticEnfant.getClasse());
            linfo.setText(staticEnfant.getInfo());

            EnfantService services = new EnfantService();
            List l = services.getComments(staticEnfant.getId());
            
            l.stream().filter((c) -> (c instanceof Commentaire)).forEachOrdered((c) -> {
                comment.add(((Commentaire) c));
            });
            comments.setItems(comment);
        } catch (SQLException ex) {
            Logger.getLogger(ProfilEnfantController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//eli 9balha anehi ?

    @FXML
    private void send(ActionEvent event) throws SQLException, IOException {
        Session d = new Session();
        Commentaire c = new Commentaire();
        c.setId_enfant(staticEnfant.getId());
        c.setId_user(d.getId());
        c.setText(tText.getText());
        EnfantService services = new EnfantService();
        services.insertComment(c);
        showMessageDialog(null, "Votre commentaire a été saisi avec succès");
        ((Node) event.getSource()).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("AdminKid.fxml"));
        Stage stage = new Stage();
        Scene sc = new Scene(parent);
        stage.setScene(sc);
        stage.show();

    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("AdminKids.fxml"));
        Stage stage = new Stage();
        Scene sc = new Scene(parent);
        stage.setScene(sc);
        stage.show();
    }
    
    int valeurID() {
        Commentaire ev = comments.getSelectionModel().getSelectedItem();

        return ev.getId();
        
    }

    @FXML
    private void banir(ActionEvent event) throws SQLException {
           EnfantService xs=new EnfantService();
        Commentaire x =xs.findMycomment(valeurID());
      
       UserService user = new UserService();
         user.banir(x.getId_user());


 
}
}
