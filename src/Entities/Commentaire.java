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
    private String text;
    private int id_user;
    private int id_enfant;

    public Commentaire(int id, String text, int id_user, int id_enfant) {
        this.id = id;
        this.text = text;
        this.id_user = id_user;
        this.id_enfant = id_enfant;
    }

    

    @Override
    public String toString() {
        return this.text ;
    }

    public int getId_enfant() {
        return id_enfant;
    }

    public void setId_enfant(int id_enfant) {
        this.id_enfant = id_enfant;
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
