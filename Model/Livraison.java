package Model;

import Vue.FenetreLivreurs;

import java.util.*;

public class Livraison {
    private Commande commande;
    private Vehicule vehicule;
    private Livreur livreur;
    private Date startTime ;
    private Date endTime ;

    public Livraison() {
    }
    public Livraison ( Vehicule v, Livreur liv , Commande cmd){
         vehicule = v ;
         livreur = liv ;
         commande = cmd ;
         startTime = new Date() ;
    }
    public void endLivraison(){
        endTime = new Date() ;
        livreur.setLibre(true);
        vehicule.setLibre(true);
    }
    public int calculDuree(){
        if (startTime != null && endTime != null){
            return (int)((endTime.getTime() - startTime.getTime()) / (1000*60) );
        }
        return -1 ;
    }

    /**Méthode qui retourne vrai si la durée de livraison de la commande c dépasse 30 minutes et Faux sinon **/
    public boolean isLate (){
        if (calculDuree() > 30) return true ;
        return false ;
    }
    public String getLivraisonDetails() {
        StringBuilder details = new StringBuilder();
        details.append("<html>");
        details.append("<h1>Livreur : ").append(livreur.getNom()).append("</h1>");
        details.append("<p>Véhicule : ").append(vehicule.getMatricule()).append("</p>");
        details.append("<p>Commande : ").append(commande.toString()).append("</p>");
        details.append("<p>Heure de début : ").append(startTime).append("</p>");
        details.append("</html>");
        return details.toString();
    }


}