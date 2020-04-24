/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Session;
import java.sql.Connection;
import java.sql.Statement;
import Utils.DataSource;
import Entities.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author HP
 */
public class UserService {

    public Connection cnx;
    public Statement st;

    public UserService() {

        cnx = DataSource.getInstance().getConnection();

    }

    public void AjouterUser(User u) throws SQLException {
        String req = "insert into fos_user(username, username_canonical, email, email_canonical,password, enabled,roles) values(?,?,?,?,?,1,?)";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.setString(1, u.getUsername());
        pre.setString(2, u.getUsername().toLowerCase());
        pre.setString(3, u.getEmail());
        pre.setString(4, u.getEmail().toLowerCase());
        pre.setString(5, u.getPassword());
        pre.setString(7, "a:0:{}");
        pre.executeUpdate();
    }

    public Boolean verif_username(String username) {

        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery("select * from fos_user where username ='" + username + "'");

            while (rs.next()) {

                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Boolean Signin(String username, String email, String pwd) throws SQLException {
        String req = "INSERT INTO `fos_user` (`username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `password`, `roles`) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement;

        try {
            String generatedColumns[] = {"ID"};
            preparedStatement = cnx.prepareStatement(req, generatedColumns);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, username.toLowerCase());
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, email.toLowerCase());
            preparedStatement.setInt(5, 1);
            preparedStatement.setString(6, pwd);
            preparedStatement.setString(7, "a:0:{}");
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                long id = rs.getLong(1);
                Session.setId((int) id);
                Session.setUsername(username);
                Session.setEmail(email);
                Session.setRole("a:0:{}");
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
  

    public Boolean login(String userName, String Pwd) throws SQLException {
        String req = "SELECT * FROM fos_user WHERE username=? and password= ? and  enabled=1";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.setString(1, userName);
        pre.setString(2, Pwd);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            /* if (BCrypt.checkpw(Pwd, rs.getString("password"))) {
             */

            System.out.println(rs.getString("password"));
            Session.setId(rs.getInt("id"));
            Session.setUsername(rs.getString("username"));
            Session.setEmail(rs.getString("email"));
            Session.setRole(rs.getString("roles"));
            Session.setPassword(rs.getString("password"));


            return true;

        }

        return false;
    }

    public Boolean loginByfb(String username, String userid) throws SQLException {
        String req = "SELECT * FROM fos_user WHERE username=? and isFb=? and idFb=?";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.setString(1, username);
        pre.setBoolean(2, true);
        pre.setString(3, userid);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {

            Session.setId(rs.getInt("id"));
            Session.setUsername(rs.getString("username"));
            Session.setEmail(rs.getString("email"));
            Session.setRole(rs.getString("roles"));

            return true;

        }
        return false;
    }
    
    public Boolean SigninByfb(String username, String userid) throws SQLException {
        if (!Exist(username, userid)) {
            String req = "INSERT INTO `fos_user` (`username`, `username_canonical`, `isFb`, `idFb`, `enabled`, `roles`) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement;

            try {
                String generatedColumns[] = {"ID"};
                preparedStatement = cnx.prepareStatement(req, generatedColumns);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, username.toLowerCase());
                preparedStatement.setBoolean(3, true);
                preparedStatement.setString(4, userid);
                preparedStatement.setInt(5, 1);
                preparedStatement.setString(6, "a:0:{}");
                preparedStatement.execute();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    long id = rs.getLong(1);
                    Session.setId((int) id);
                    Session.setUsername(username);
                    Session.setEmail("null");
                    Session.setRole("a:0:{}");
                }

                return true;
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
      public Boolean Exist(String username, String userid) throws SQLException {
        String req = "SELECT * FROM fos_user WHERE username=? and isFb=? and idFb=?";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.setString(1, username);
        pre.setBoolean(2, true);
        pre.setString(3, userid);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            System.out.println("Exist" + rs.getString("id"));
            return true;

        }
        return false;
    }
      
    public ObservableList<User> searchById(int id) {
        String req = "SELECT * FROM `fos_user` WHERE id=" + id;
        PreparedStatement preparedStatement;
        ObservableList<User> Ads = FXCollections.observableArrayList();
        try {
            Statement statement = cnx.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                User u = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(4), resultSet.getInt(6), resultSet.getString(12));
                Ads.add(u);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }
    
        public ObservableList<User> searchUserbyname(String username) {
        String req = "SELECT * FROM `fos_user` WHERE username LIKE '%" + username + "%'";
        PreparedStatement preparedStatement;
        ObservableList<User> users = FXCollections.observableArrayList();
        try {
            Statement statement = cnx.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                User _user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(4), resultSet.getInt(6), resultSet.getString(12));
                users.add(_user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
        
    public void delete(int id) {

        String req = "DELETE FROM `fos_user` WHERE id = ?";
        try {
            PreparedStatement ste = cnx.prepareStatement(req);
            ste.setInt(1, id);
            ste.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      public void update(int id,User u) {
        String req = "UPDATE `fos_user` SET username = ? , email = ? , enabled = ? , role = ? WHERE id = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getEmail());
            ps.setInt(3, u.getEnabled());
            ps.setString(4, u.getRole());
            ps.setInt(5, u.getId());
// execute insert SQL statement
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
     
    public List<User> admin_selectAll() {
        String req = "SELECT * FROM `fos_user` ";
        PreparedStatement preparedStatement;
        List<User> Ads = new ArrayList<User>();
        try {
            Statement statement = cnx.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                User LO = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(4), resultSet.getInt(6), resultSet.getString(12));
                Ads.add(LO);

            }

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ads;
    }
    
       public boolean updatePassword(String mdp,int id) throws SQLException {
            PreparedStatement pre = cnx.prepareStatement("update fos_user set password='" + mdp + "' where id='" +id+"'");
        return pre.executeUpdate() == 1;

    }
       
        public boolean banir(int id) throws SQLException {
         PreparedStatement pre = cnx.prepareStatement("update fos_user set enabled=0 where id='" +id+"' and roles='a:0:{}'");
        return pre.executeUpdate() == 1;        
    }
        public String selectRole(int id) {
        String req = "SELECT roles FROM `fos_user` WHERE id=" + id;
        PreparedStatement preparedStatement;
        //return String role;
        String lsAdresse = "";
      try
      {
         Statement state = cnx.createStatement();
         ResultSet result = state.executeQuery(req);
         result.next();
         lsAdresse = result.getString(1);
         result.close();
         state.close();
      }
      catch(Exception e){ lsAdresse = "Introuvable"; }
      return lsAdresse;
       
        
    }
        
    
    

    
}
