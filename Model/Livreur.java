package Model;

import java.util.*;

public class Livreur {
    private String nom;
    private boolean libre = true ;
    private Pizzeria pizzeria;
    private Vector<Livraison> listLivraisons = new Vector<>();
    public Livreur() {
    }
    public Livreur(String nom,Pizzeria pizzeria){
        this.nom = nom ;
        this.pizzeria = pizzeria ;
    }
    public boolean isLibre() {
        return libre;
    }
    public void setLibre(boolean libre){
        this.libre = libre ;
    }
    public void ajouterLivraison(Livraison liv){
        listLivraisons.add(liv) ;
    }
    public String toString(){
        return nom ;
    }

    public String getNom() {
        return nom ;
    }

    public Livraison getCurrentLivraison(){
        return listLivraisons.lastElement() ;
    }
    public Pizzeria getPizzeria(){
        return pizzeria ;
    }
}