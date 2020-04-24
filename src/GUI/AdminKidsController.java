/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Enfant;
import Services.EnfantService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AdminKidsController implements Initializable {

    public static Enfant staticEnfant;
    public ObservableList<Enfant> newobservableList;
    @FXML
    private TableView<Enfant> ListEnfant;
    @FXML
    private TableColumn<Enfant, String> name;
    @FXML
    private TableColumn<Enfant, LocalDate> dt;
    @FXML
    private TableColumn<Enfant, String> place;
    @FXML
    private TableColumn<Enfant, String> grd;
    @FXML
    private TableColumn<Enfant, String> classe;
    @FXML
    private TableColumn<Enfant, Integer> id;
    @FXML
    private TableColumn<Enfant, String> info;
    @FXML
    private JFXButton details;
    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            // TODO
            staticEnfant = new Enfant();
            EnfantService Eservice = new EnfantService();
            ArrayList arrayList;
            arrayList = (ArrayList) Eservice.selecttAll();
            newobservableList = FXCollections.observableArrayList(arrayList);
            ListEnfant.setItems(newobservableList);
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            dt.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
            place.setCellValueFactory(new PropertyValueFactory<>("place"));
            
            grd.setCellValueFactory(new PropertyValueFactory<>("garderie"));
            classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
            
            info.setCellValueFactory(new PropertyValueFactory<>("info"));
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            
            ListEnfant.setOnMouseClicked((MouseEvent event) -> {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    staticEnfant = ListEnfant.getSelectionModel().getSelectedItem();
                    
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(ListeEnfantController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void details_action(ActionEvent event) throws IOException {
        if(staticEnfant.getId()==0){
           showMessageDialog(null, "Please choose the account that you want to show"); 
        }else{
            ((Node) event.getSource()).getScene().getWindow().hide(); 
            Parent parent = FXMLLoader.load(getClass().getResource("AdminKid.fxml"));
            Stage stage = new Stage();
            Scene sc = new Scene(parent);
            stage.setScene(sc);
            stage.show();
        
        }
    }

    @FXML
    private void delete_Action(ActionEvent event) throws SQLException, IOException {
        if (staticEnfant.getId() == 0) {
            showMessageDialog(null, "Please choose the profile that you want to delete");
        } else {
            showMessageDialog(null, "Success");
            EnfantService services = new EnfantService();
            services.delete(staticEnfant.getId());

            ((Node) event.getSource()).getScene().getWindow().hide();
            Parent parent = FXMLLoader.load(getClass().getResource("AdminKids.fxml"));
            Scene scene = new Scene(parent);
            Stage x = (Stage) this.ListEnfant.getScene().getWindow();
            x.setScene(scene);
            x.show();

        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminAcc.fxml"));
            back.getScene().setRoot(root);
    }
    
}
