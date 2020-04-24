/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Commentaire;
import Entities.Enfant;
import Entities.Session;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

/**
 *
 * @author HP
 */
public class EnfantService {

    Connection cnx = null;

    public EnfantService() throws SQLException {
        cnx = DataSource.getInstance().getConnection();
        cnx.setAutoCommit(true);

    }

    public void insert(Enfant e) {

        String req = "INSERT INTO `enfant` (`name`,  `date_naissance`,  `place`, `garderie`, `classe`, `info`, `img`, `id_user`) VALUES (?,?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement;
        try {
            String generatedColumns[] = {"ID"};

            preparedStatement = cnx.prepareStatement(req, generatedColumns);

            preparedStatement.setString(1, e.getName());
            preparedStatement.setDate(2, e.getDate_naissance());

            preparedStatement.setString(3, e.getPlace());
            preparedStatement.setString(4, e.getGarderie());
            preparedStatement.setString(5, e.getClasse());
            preparedStatement.setString(6, e.getInfo());
            preparedStatement.setString(7, "img");
            preparedStatement.setInt(8, e.getId_user());

            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                long id = rs.getLong(1);
                System.out.println("Inserted ID -" + id); // display inserted record
            }

        } catch (SQLException ex) {
            Logger.getLogger(EnfantService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void delete(int idEnfant) {
        
        String req = "DELETE FROM `enfant` WHERE id = ?";
        try {
            PreparedStatement ste = cnx.prepareStatement(req);
            ste.setInt(1, idEnfant);
            ste.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EnfantService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void update(Enfant e) {
        String req = "UPDATE `enfant` SET name = ?,date_naissance = ? ,place = ?,garderie = ?,classe = ?,info = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setString(1, e.getName());
            preparedStatement.setDate(2, e.getDate_naissance());
            preparedStatement.setString(3, e.getPlace());
            preparedStatement.setString(4, e.getGarderie());
            preparedStatement.setString(5, e.getClasse());
            preparedStatement.setString(6, e.getInfo());

            preparedStatement.setInt(7, e.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EnfantService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Enfant> selectFrontAll() {
        Session s = new Session();
        int ids = s.getId();
        String req = "SELECT * FROM `enfant` where id_user=" + ids;
        PreparedStatement preparedStatement;
        ArrayList<Enfant> enfant = new ArrayList<Enfant>();
        try {
            Statement statement = cnx.createStatement();
            ResultSet resultSet = statement.executeQuery(req);

            while (resultSet.next()) {
                Enfant e = new Enfant(resultSet.getInt(1),resultSet.getString(2), resultSet.getDate(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7));
                enfant.add(e);

            }

        } catch (SQLException ex) {
            Logger.getLogger(EnfantService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return enfant;
    }

    public ArrayList<Enfant> selecttAll() {

        String req = "SELECT * FROM `enfant`";
        PreparedStatement preparedStatement;
        ArrayList<Enfant> enfant = new ArrayList<Enfant>();
        try {
            Statement statement = cnx.createStatement();
            ResultSet resultSet = statement.executeQuery(req);

            while (resultSet.next()) {
                Enfant e = new Enfant(resultSet.getInt(1),resultSet.getString(2), resultSet.getDate(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7));
                enfant.add(e);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EnfantService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return enfant;
    }
    
     public void insertComment(Commentaire c) {
        try {
            String req = "INSERT INTO `commentaire` (text,id_user,id_enfant) VALUES (?,?,?)";

            PreparedStatement preparedStatement;
        
            preparedStatement = cnx.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, c.getText());
            preparedStatement.setInt(2, c.getId_user());

            preparedStatement.setInt(3, c.getId_enfant());
            

            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(EnfantService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
      public ArrayList<Commentaire> getComments(int idEnfant) {

        String req = "SELECT * FROM `commentaire` WHERE id_enfant="+idEnfant;
        
        ArrayList <Commentaire>  c = new ArrayList <Commentaire>();
        try {
            //PreparedStatement preparedStatement = connection.prepareStatement(req);
            //preparedStatement.setInt(1,idEvennement);
            Statement statement = cnx.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            
            while (resultSet.next()) {
               Commentaire e = new Commentaire(resultSet.getInt(1), resultSet.getString(2),resultSet.getInt(3),
                       resultSet.getInt(4));
               c.add(e);

            }

        } catch (SQLException ex) {
            Logger.getLogger(EnfantService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
      
      
 public Commentaire findMycomment(int id) {

        String req = "SELECT * FROM `commentaire` WHERE id="+id;
        
        Commentaire  c = new Commentaire();
        try {
            //PreparedStatement preparedStatement = connection.prepareStatement(req);
            //preparedStatement.setInt(1,idEvennement);
            Statement statement = cnx.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            
            while (resultSet.next()) {
               Commentaire e = new Commentaire(resultSet.getInt(1), resultSet.getString(2),resultSet.getInt(3),
                       resultSet.getInt(4));
               c=e;

            }

        } catch (SQLException ex) {
            Logger.getLogger(EnfantService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
      
}
