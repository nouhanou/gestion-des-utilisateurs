/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.DataSource;
import Entities.User;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication; 
import javax.mail.Transport;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author HP
 */
public class EmailService {

    /**
     *
     */
    public Connection cnx;

    /**
     *
     */
    public Statement st;

    /**
     *
     */
    public EmailService() {

        cnx = DataSource.getInstance().getConnection();

    }

   
   
    /**
     *
     * @return
     */
    public int getcode() {
        return (int) (Math.random() * (9999 - 1000));

    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public String getMail(int id) throws SQLException {
        List<Integer> arr = new ArrayList<>();
        st = cnx.createStatement();
        String c = "";
        ResultSet rs = st.executeQuery("SELECT email FROM `fos_user` WHERE id='" + id + "'");
        while (rs.next()) {
            c = rs.getString("emailUser");

        }
        return c;
    }

    /**
     *
     * @param email
     * @return
     * @throws SQLException
     */
    public boolean VerificationEmail(String email) throws SQLException {

        st = cnx.createStatement();
        ResultSet rs = st.executeQuery("select * from `fos_user` where email='" + email + "'");

        return rs.first();

    }

    /**
     *
     * @param email
     * @return
     * @throws SQLException
     */
    public User getUser(String email) throws SQLException {

        st = cnx.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM `fos_user` WHERE email = '" + email + "'");
        System.out.println(rs);
        while (rs.next()) {
            System.out.println(rs.getInt("id"));
            int id = rs.getInt("id");
            String username = rs.getString("username");
            //String email = rs.getString("email");
            String password = rs.getString("password");
            int enabled = rs.getInt("enabled");
            //Date last_login = rs.getString("last_login");
            String role = rs.getString("roles");

            User.user = new User(id, username, password, enabled, role);
        }
        return User.user;
    }

}
