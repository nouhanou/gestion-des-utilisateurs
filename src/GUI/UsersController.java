/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Utils.DataSource;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class UsersController implements Initializable {

    @FXML
    private TextField stsid;
    @FXML
    private TextField stsusername;
    @FXML
    private TextField stsemail;
    @FXML
    private TextField stspassword;
    @FXML
    private TextField stsconfirm;

    @FXML
    private TableView<UserData> usertable;
    @FXML
    private TableColumn<UserData, String> dataid;
    @FXML
    private TableColumn<UserData, String> datausername;
    @FXML
    private TableColumn<UserData, String> dataemail;
    @FXML
    private TableColumn<UserData, String> datapassword;
    @FXML
    private TableColumn<UserData, String> dataenabled;
    @FXML
    private TableColumn<UserData, String> datalogin;
    @FXML
    private TableColumn<UserData, String> datarole;
    @FXML
    private TextField filterField;
    @FXML
    private TableColumn colEdit;
    @FXML
    private JFXTextField search;
    private ObservableList<UserData> data;
    private DataSource dc;
    //Observable list to store data
    private final ObservableList<UserData> dataList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {

        // TODO
        dc = new DataSource();
        FilteredList<UserData> filteredData = new FilteredList<>(dataList, b -> true);
        // 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(UserData -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (UserData.getUsername().toLowerCase().contains(lowerCaseFilter) ) {
					return true; // Filter matches first name.
				} else if (UserData.getEmail().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
				else return String.valueOf(UserData.getId()).contains(lowerCaseFilter); // Does not match.
                                
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		      SortedList<UserData> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(usertable.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		usertable.setItems(sortedData);
               
        
    }    
    

    public void LoadUserData(ActionEvent event) {
        try {
            Connection conn;
            conn = dc.getConnection();
            this.data = FXCollections.observableArrayList();

            //execute query and store result in resultset
            ResultSet rs = conn.createStatement().executeQuery("select * from fos_user");
            while (rs.next()) {
                this.data.add(new UserData(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(8),
                        rs.getString(6), rs.getString(9), rs.getString(12)));
            }

        } catch (SQLException e) {
            System.out.println(e);

        }

        //set cell value factory to tableview
        //NB.property value factory must be the same with the one set in model class
        this.dataid.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.datausername.setCellValueFactory(new PropertyValueFactory<>("username"));
        this.dataemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.datapassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        this.dataenabled.setCellValueFactory(new PropertyValueFactory<>("enabled"));
        this.datalogin.setCellValueFactory(new PropertyValueFactory<>("last_login"));
        this.datarole.setCellValueFactory(new PropertyValueFactory<>("role"));
        this.usertable.setItems(null);
        this.usertable.setItems(this.data);

    }
    //Set  the costum factory to action column
    //colEdit.setCell(cellFactory);
    //lets create a cell factory to insert a button in every row
    //Callback<TableColumn<UserData, String>, TableCell<UserData, String>> cellFactory;

    //@Override
    //public void initialize(URL location, ResourceBundle resources) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}

    public void AddData(ActionEvent event) throws SQLException
    {
        
        
            Connection conn;
            conn = dc.getConnection();
            PreparedStatement Prs;
            Prs = conn.prepareStatement("INSERT INTO `fos_user` (`username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `password`, `roles`) VALUES (?,?,?,?,?,?,?)");
            //String generatedColumns[] = {"id"};
            Prs.setString(2, this.stsusername.getText());
            Prs.setString(4, this.stsemail.getText());
            //Prs.setString(4, BCrypt.hashpw(this.stspassword, BCrypt.gensalt(13)));
            Prs.setString(12, "a:0:{}");
            Prs.execute();
            conn.close();
             
            

            

            
            
            

        
    }
  
    public void deleteRow(ActionEvent event){
   /*     try
    {
      /*  Connection conn = SqliteConnection.Connector();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM fos_user WHERE id = ?");
        UserData userdata = usertable.getSelectionModel().getSelectedItem();
        ps.setString(1, studentdata.getID());
wini redirectiio
        ps.execute();
        conn.close();
    }
    catch (SQLException e)
    {
        System.out.println(e);
    }*/
        
    }
  
    
}
