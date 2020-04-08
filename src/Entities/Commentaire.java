/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author HP
 */
public class Commentaire {

    private int id;
    private int id_user;
    private String text;
    //private int id_event;

    public Commentaire(int id, String text, int id_user) {
        this.id = id;
        this.text = text;
        this.id_user = id_user;
        //this.id_event = id_event;
        
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", id_user=" + id_user + ", text=" + text + '}';
    }

    

    public Commentaire() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
