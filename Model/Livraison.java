package Model;

import java.io.*;
import java.util.*;

public class Livraison {
    private int duree; //En minutes
    private Commande commande;
    private Vehicule vehicule;
    private Livreur livreur;

    public Livraison() {
    }
    public Livraison (int d , Vehicule v, Livreur liv ){
         duree = d ;
         vehicule = v ;
         livreur = liv ;
    }

    /**Méthode qui retourne vrai si la durée de livraison de la commande c dépasse 30 minutes et Faux sinon **/
    public boolean verifDuree (){
        if (duree > 30) return true ;
        return false ;
    }






}