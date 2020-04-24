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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class Admin_statController implements Initializable {

    @FXML
    private PieChart Pi;
        private ArrayList<Enfant> arrayList;
    @FXML
    private JFXButton stat;
    @FXML
    private JFXButton back;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            EnfantService Eservice = new EnfantService();
            arrayList = (ArrayList) Eservice.selecttAll();
            Iterator<Enfant> it = arrayList.iterator();
            int x = 0;
            int y = 0;
            int z=0;
             int xp = 0;
            int yp = 0;
            int zp=0;
            int arrayLength = arrayList.size();
            for (int i = 0; i < arrayLength; i++) {
                if (arrayList.get(i).getGarderie().equals("temps partiel") && arrayList.get(i).getClasse().equals("classe préscolaire")) {
                    x = x + 1;
                }else if(arrayList.get(i).getGarderie().equals("temps partiel") && arrayList.get(i).getClasse().equals("garderie"))
                        {y =y +1;}
                else if(arrayList.get(i).getGarderie().equals("temps partiel") && arrayList.get(i).getClasse().equals("classe maternelle"))
                        {z = z+1;}
                else if(arrayList.get(i).getGarderie().equals("Plein-temps") && arrayList.get(i).getClasse().equals("classe préscolaire")) {
                    xp = x + 1;
                }else if(arrayList.get(i).getGarderie().equals("Plein-temps") && arrayList.get(i).getClasse().equals("garderie"))
                        {yp =y +1;}
                else 
                        {zp = zp+1;}
            }
                  
            

            ObservableList<PieChart.Data> PieChartData
                    = FXCollections.observableArrayList(
                            new PieChart.Data(" Enfant en classe préparatoire temps partiel ", x),
                            new PieChart.Data(" Enfant en garderie temps partiel ", y),
                            new PieChart.Data(" Enfant en classe maternelle temps partiel", z),
                            new PieChart.Data(" Enfant en classe préparatoire plein temps ", xp),
                            new PieChart.Data(" Enfant en garderie plein temps", yp),
                            new PieChart.Data(" Enfant en classe maternelle plein temps", zp)
                    );
            Pi.setData(PieChartData);
        } catch (SQLException ex) {
            Logger.getLogger(Admin_statController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    

    @FXML
    private void StatDetail_action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Admin_stat.fxml"));
        stat.getScene().setRoot(root);
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        back.getScene().setRoot(root);
    }

    
}
