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

    public void ajoutSousCommande (Ligne_Commande l){
        for (Ligne_Commande ligne : sous_commandes){
            if (ligne.getPizza() == l.getPizza() && ligne.getTaille()==l.getTaille()){
                ligne.setQuantite(ligne.getQuantite()+l.getQuantite());
                return;
            }
        }
        sous_commandes.add(l) ;
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

    public String toString(){
       return  "Récapitulatif de la commande :\n" +
                "- Numéro de téléphone : " + client.getNum_tel() + "\n" +
                "- Date de la commande : " + date +" \n" +
                "- Prix total : "+calcul_prix()+" EURO \n";
    }





}
