/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Commentaire;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class EvennementServices {
    
    Connection connection = null;

    public EvennementServices() {
        connection = DataSource.getInstance().getConnection();
    }
     public void insertComment(Commentaire c) {
        try {
            String req = "INSERT INTO `commentaire` (text,id_user) VALUES (?,?)";

            PreparedStatement preparedStatement;
        
            preparedStatement = connection.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, c.getText());
            preparedStatement.setInt(2, c.getId_user());

            //preparedStatement.setInt(3, c.getId_event());
            

            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(EvennementServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
     
        public ArrayList<Commentaire> getComments() {

        String req = "SELECT * FROM `commentaire`";
        
        ArrayList <Commentaire>  c = new ArrayList <Commentaire>();
        try {
            //PreparedStatement preparedStatement = connection.prepareStatement(req);
            //preparedStatement.setInt(1,idEvennement);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            
            while (resultSet.next()) {
               Commentaire e = new Commentaire(resultSet.getInt(1), resultSet.getString(2),resultSet.getInt(3)
                       );
               c.add(e);

            }

        } catch (SQLException ex) {
            Logger.getLogger(EvennementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
}
