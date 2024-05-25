package Model;

import java.util.*;

public class Vehicule {
    private String marque;
    private String matricule;
    private Vector<Livraison> livraisons = new Vector<>();
    private Type_Vehicule type;
    private Pizzeria pizzeria ;
    boolean libre = true ;

    public Vehicule() {
    }

    public Vehicule(String marque, String matricule, Type_Vehicule type, Pizzeria pizzeria){
        this.marque = marque ;
        this.matricule = matricule ;
        this.type = type ;
        this.pizzeria = pizzeria ;
    }

    public boolean isLibre() {
        return libre;
    }

    public void setLibre(boolean libre) {
        this.libre = libre;
    }
    public String getMatricule(){
        return matricule ;
    }
}