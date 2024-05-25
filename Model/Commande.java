package Model;

import java.util.*;

public class Commande {
    private Date date ;
    private Client client;
    private Vector<Ligne_Commande> sous_commandes = new Vector<>();
    private Livraison livraison;
    public Commande() {
    }

    public Commande(Client client, Vector<Ligne_Commande> sous_commandes ){
        this.client = client ;
        this.sous_commandes = sous_commandes ;
    }

    public double calcul_prix(){
        double prix_total = 0 ;
        for (Ligne_Commande l : sous_commandes){
            prix_total += l.getPrix() ;
        }
        return prix_total ;
    }

    public Ligne_Commande ajoutSousCommande (Ligne_Commande l){
        for (Ligne_Commande ligne : sous_commandes){
            if (ligne.getPizza() == l.getPizza() && ligne.getTaille()==l.getTaille()){
                ligne.setQuantite(ligne.getQuantite()+l.getQuantite());
                ligne.setPrix(ligne.calcul_prix());
                return ligne;
            }
        }
        sous_commandes.add(l) ;
        return l ;
    }

    public void supprimerLignecmd(){
        //Supprimer la dernière ligne de commande
        sous_commandes.removeElementAt(sous_commandes.size()-1);
    }

    public int nbPizzasCommande(){
        int resu = 0 ;
        for (Ligne_Commande ligne : sous_commandes){
            resu = resu + ligne.getQuantite() ;
        }
        return resu ;
    }

    public Vector<Pizza> getListPizzas (){
        Vector<Pizza>  listpizzas = new Vector<>() ;
        for (Ligne_Commande ligne : sous_commandes){
            listpizzas.add(ligne.getPizza()) ;
        }
        return listpizzas ;
    }

    public void setDate(Date d){
        date = d ;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vector<Ligne_Commande> getSous_commandes() {
        return sous_commandes;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>")
                .append("Récapitulatif de la commande : <br>")
                .append("<ul>")
                .append("<li>Numéro de téléphone du client : ").append(client.getNum_tel()).append("</li>")
                .append("<li>Date : ").append(date).append("</li>")
                .append("<li>Prix total : ").append(calcul_prix()).append(" EURO</li>")
                .append("</ul>")
                .append("</html>");
        return sb.toString();

    }


    public Date getDate() {
        return date ;
    }
}
