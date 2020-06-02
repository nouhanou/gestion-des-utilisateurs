/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
import java.sql.Date;




/**
 *
 * @author HP
 */

public class User {
    int id;
    String username;
    String email;
    String password;
    int enabled;
    Date last_login;
    String role;
    public static int code;
    public static User user=null;


    public User(){
    
    
}
    public User(int id, String username, String email, String password, int enabled, Date last_login, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.last_login = last_login;
        this.role = role;
    }
    
    public User(int id, String username, String email, String password, int enabled, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
    }
    
    public User(int id, String username, String email, int enabled, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.enabled = enabled;
        this.role = role;
    }
    
     public User(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;

    }
    
    public User(int id) {
        this.id = id;
    }

    public User(String username, int enabled, String role) {
        this.username = username;
        this.enabled = enabled;
        this.role = role;
    }
    
    public User(String username, String email, int enabled, String role) {
        this.username = username;
        this.email = email;
        this.enabled = enabled;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        User.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", enabled=" + enabled + ", last_login=" + last_login + ", role=" + role + '}';
    }
}