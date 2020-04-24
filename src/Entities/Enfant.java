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
public class Enfant {
    private int id;
    private String name;
    private Date date_naissance;
    private String place;
    private String garderie;
    private String classe;
    private String info;
    private String img;
    private int id_user;

    public Enfant(int id, String name, Date date_naissance, String place, String garderie, String classe, String info, String img, int id_user) {
        this.id = id;
        this.name = name;
        this.date_naissance = date_naissance;
        this.place = place;
        this.garderie = garderie;
        this.classe = classe;
        this.info = info;
        this.img = img;
        this.id_user = id_user;
    }

    public Enfant(String name, Date date_naissance, String place, String garderie, String classe, String info, int id_user) {
        this.name = name;
        this.date_naissance = date_naissance;
        this.place = place;
        this.garderie = garderie;
        this.classe = classe;
        this.info = info;
        this.id_user = id_user;
    }

  

    public Enfant(String name, Date date_naissance, String place, String garderie, String classe, String info) {
        this.name = name;
        this.date_naissance = date_naissance;
        this.place = place;
        this.garderie = garderie;
        this.classe = classe;
        this.info = info;
    }

    public Enfant(int id, String name, Date date_naissance, String place, String garderie, String classe, String info) {
        this.id = id;
        this.name = name;
        this.date_naissance = date_naissance;
        this.place = place;
        this.garderie = garderie;
        this.classe = classe;
        this.info = info;
    }

    public Enfant(String name) {
        this.name = name;
    }

    

    

   
    public Enfant(){
    
}

   

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getGarderie() {
        return garderie;
    }

    public void setGarderie(String garderie) {
        this.garderie = garderie;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImg() {
        return img;
    }

    public int getId_user() {
        return id_user;
    }
    

    @Override
    public String toString() {
        return "Enfant{" + "id=" + id + ", name=" + name + ", date_naissance=" + date_naissance + ", place=" + place + ", garderie=" + garderie + ", classe=" + classe + ", info=" + info + '}';
    }

    
    
}
