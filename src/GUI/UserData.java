/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author HP
 */
public class UserData {
            private final IntegerProperty id;
            private final StringProperty username;
            private final StringProperty email;
            private final StringProperty password;
            private final StringProperty enabled;
            private final StringProperty last_login;
            private final StringProperty role;

    public UserData(int dataid,String datausername,String dataemail,String datapassword,String dataenabled,String datalogin,String datarole)
    {  
       this.id = new SimpleIntegerProperty(dataid);
       this.username=new SimpleStringProperty(datausername);
       this.email=new SimpleStringProperty(dataemail);
       this.password=new SimpleStringProperty(datapassword);

       this.enabled=new SimpleStringProperty(dataenabled);
       this.last_login=new SimpleStringProperty(datalogin);
       this.role=new SimpleStringProperty(datarole);
       
    }

   //Getters
   public int getId(){return id.get();}
   public String getUsername(){return username.get();}
   public String getEmail(){return email.get();}
   public String getEnabled(){return enabled.get();}
   public String getLogin(){return last_login.get();}
   public String getRole(){return role.get();}
   
   //Setters
   public void setId(int value){
       id.set(value);
   }
   public void setUsername(String value){
       username.set(value);
   }
   public void setEmail(String value){
       email.set(value);
   }
   public void setEnabled(String value){
       enabled.set(value);
   }
   public void setLogin(String value){
       last_login.set(value);
   }
   public void setRole(String value){
       role.set(value);
   }





   
    
    
}
