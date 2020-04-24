/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import static GUI.ListeEnfantController.staticEnfant;
import Services.EnfantService;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
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
public class EditEnfantController implements Initializable {

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
    private JFXButton edit;
    @FXML
    private JFXButton back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         garderie.getItems().addAll("temps complet","mi_temps");
        classe.getItems().addAll("primaire","secondaire");
        info.setText(staticEnfant.getInfo());
        lieux.setText(staticEnfant.getPlace());
        name.setText(staticEnfant.getName());
        //datenaissance.setValue((staticEnfant.getDate_naissance());

        garderie.getSelectionModel().select(staticEnfant.getGarderie());
        classe.getSelectionModel().select(staticEnfant.getClasse());
    }    

    @FXML
    private void edit(ActionEvent event) throws IOException, SQLException {
        staticEnfant.setName(name.getText());
        staticEnfant.setPlace(lieux.getText());
        staticEnfant.setGarderie(garderie.getSelectionModel().getSelectedItem());
        staticEnfant.setClasse(classe.getSelectionModel().getSelectedItem());
        Date d = Date.valueOf(datenaissance.getValue());
        staticEnfant.setDate_naissance(d);
        staticEnfant.setInfo(info.getText());
        EnfantService services = new EnfantService();
        System.out.print(staticEnfant);
        services.update(staticEnfant);
        showMessageDialog(null, "le profil est modifié");
        ((Node) event.getSource()).getScene().getWindow().hide(); 
        Parent parent = FXMLLoader.load(getClass().getResource("ListeEnfant.fxml"));
        Stage stage = new Stage();
        Scene sc = new Scene(parent);
        stage.setScene(sc);
        stage.show();
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide(); 
        Parent parent = FXMLLoader.load(getClass().getResource("ListeEnfant.fxml"));
        Stage stage = new Stage();
        Scene sc = new Scene(parent);
        stage.setScene(sc);
        stage.show();
    }
    
}
