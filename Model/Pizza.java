package Model;

import java.util.*;

public class Pizza {

    private String nom;
    private double prix_base;
    private Vector<String> ingredients = new Vector<>();
    private Catalogue catalogue;
    private Vector<Ligne_Commande> lignes_commandes = new Vector<>();
    public Pizza() {
    }

    public Pizza(String n , double prix , Vector<String> ing ){
        nom = n ;
        prix_base = prix ;
        ingredients = ing ;
    }
    public String getNom(){
        return nom ;
    }

    public double getPrix_base(){
        return prix_base ;
    }




}