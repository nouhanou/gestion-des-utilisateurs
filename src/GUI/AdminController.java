/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AdminController implements Initializable {

    public ObservableList<User> newobservableList;
    @FXML
    private JFXButton btn_all;
    @FXML
    private JFXTextField search;
    @FXML
    private JFXCheckBox checkId;
    @FXML
    private JFXCheckBox checkName;

    @FXML
    private Text msgerr;
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, Integer> id;
    @FXML
    private TableColumn<User, String> username;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, Integer> enabled;
    @FXML
    private TableColumn<User, String> role;
    
    static int id_ads;
    @FXML
    private AnchorPane child;
    @FXML
    private JFXButton deleteUser;
    @FXML
    private JFXButton addUser;
    @FXML
    private JFXButton back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserService Uservice = new UserService();
        ArrayList arrayList;
        arrayList = (ArrayList) Uservice.admin_selectAll();
        newobservableList = FXCollections.observableArrayList(arrayList);
        table.setItems(newobservableList);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        enabled.setCellValueFactory(new PropertyValueFactory<>("enabled"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));

        //created_at.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        // TODO

        FilteredList<User> filteredData = new FilteredList(newobservableList, p -> true);
        search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

            if (!checkId.isSelected() && !checkName.isSelected()) {
                msgerr.setVisible(true);
            } else {
                msgerr.setVisible(false);
                if (newValue.equals("")) {
                    table.setItems(newobservableList);
                    username.setCellValueFactory(new PropertyValueFactory<>("username"));
                    email.setCellValueFactory(new PropertyValueFactory<>("email"));
                    enabled.setCellValueFactory(new PropertyValueFactory<>("enabled"));
                    role.setCellValueFactory(new PropertyValueFactory<>("role"));

                    //created_at.setCellValueFactory(new PropertyValueFactory<>("created_at"));

                } else {
                    if (checkId.isSelected()) {

                        newobservableList = Uservice.searchById(Integer.parseInt(newValue));
                        table.setItems(newobservableList);
                        username.setCellValueFactory(new PropertyValueFactory<>("username"));
                        email.setCellValueFactory(new PropertyValueFactory<>("email"));
                        enabled.setCellValueFactory(new PropertyValueFactory<>("enabled"));
                        role.setCellValueFactory(new PropertyValueFactory<>("role"));

                        //created_at.setCellValueFactory(new PropertyValueFactory<>("created_at"));

                    } else if (checkName.isSelected()) {

                        newobservableList = Uservice.searchUserbyname(newValue);
                        table.setItems(newobservableList);
                        username.setCellValueFactory(new PropertyValueFactory<>("username"));
                        email.setCellValueFactory(new PropertyValueFactory<>("email"));
                        enabled.setCellValueFactory(new PropertyValueFactory<>("enabled"));
                        role.setCellValueFactory(new PropertyValueFactory<>("role"));

                        //created_at.setCellValueFactory(new PropertyValueFactory<>("created_at"));
                    }
                }
            }
        });
    }

    @FXML
    private void All_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        btn_all.getScene().setRoot(root);
    }

    @FXML
    private void checkId_action(ActionEvent event) {

        checkName.selectedProperty().set(false);
    }

    @FXML
    private void checkName_action(ActionEvent event) {
        checkId.selectedProperty().set(false);
    }

    @FXML
    private void deleteUser_action(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Look, an Error Dialog");
        alert.setContentText("selectionner un utilisateur de la table");

        alert.showAndWait();
        int id = table.getSelectionModel().getSelectedItem().getId();
        System.out.println(id);
        id_ads = id;

        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("confirmation");
        alert1.setHeaderText(null);
        alert1.setContentText("voulez vous supprimer cet utilisateur");
        alert1.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                int idS = table.getSelectionModel().getSelectedItem().getId();
                UserService s = new UserService();
                s.delete(idS);
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
                    Scene scene = new Scene(root);
                    Stage x = (Stage) this.table.getScene().getWindow();
                    x.setScene(scene);
                    x.show();
                } catch (IOException ex) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }

    @FXML
    private void addUser_action(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Look, an Error Dialog");
        alert.setContentText("selectionner un utilisateur de la table");

        alert.showAndWait();

        int id = table.getSelectionModel().getSelectedItem().getId();
        System.out.println(id);
        id_ads = id;
        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader;
        loader = new FXMLLoader(getClass().getResource("updateUser.fxml"));
        updateUserController controller = loader.getController();

        System.out.println("TEST : " + table.getSelectionModel().getSelectedItem());

        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.initOwner(table.getScene().getWindow());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminAcc.fxml"));
            back.getScene().setRoot(root);
        
        
    }
}
