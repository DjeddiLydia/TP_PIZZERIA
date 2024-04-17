package Model;

import java.util.*;

public class Pizzeria {

    private Vector<Client> clients = new Vector<>();
    private Catalogue catalogue;
    private Vector<Livreur> listLivreurs = new Vector<>();
    private Vector<Vehicule> vehicules = new Vector<>();
    public Pizzeria() {
    }

    public Catalogue getCatalogue(){
        return catalogue;
    }
    public void setCatalogue(Catalogue catalogue){
        this.catalogue = catalogue ;
    }
    public void setClients(Vector<Client> clients) {
        this.clients = clients;
    }
    public void setListLivreurs(Vector<Livreur> listLivreurs) {
        this.listLivreurs = listLivreurs;
    }
    public void setVehicules(Vector<Vehicule> vehicules) {
        this.vehicules = vehicules;
    }

    public Client client_exist(String numtel ){
        for (Client c : clients){
            if (c.getNum_tel().equals(numtel)) return c ;
        }
        return null ;
    }

    public Vehicule vehicule_Libre (){
        for (Vehicule v : vehicules){
            if (v.isLibre()) return v ;
        }
        return null ;
    }

    public Livreur livreur_libre () {
        for (Livreur liv : listLivreurs) {
            if (liv.isLibre()) return liv ;
        }
        return null ;
    }

    public double passerCommande(Client cl , Commande c){
            //On vérifie s'il y'a un livreur et un vehicule libre
            Livreur liv = livreur_libre() ;
            Vehicule v = vehicule_Libre() ;
            if (liv!=null && v!=null) {

                if (cl.passerCommande(c)) {
                    Date date = new Date();
                    c.setDate(date); //mettre la date actuelle à la commande
                    cl.setNbpizzasAchetees( cl.getNbpizzasAchetees() + c.nbPizzasCommande());
                    if (cl.pizzaGratuite()) {
                        //Créer une ligne de commande qui contient une pizza et un format choisis aléatoirement
                        Ligne_Commande ligne = new Ligne_Commande(1) ;
                        ligne.setRandFormat();
                        ligne.setPizza(catalogue.random_pizza());
                        c.ajoutSousCommande(ligne);
                    }
                    cl.setNbpizzasAchetees( 0 );
                    Livraison livraison = new Livraison(0,v,liv);
                    liv.ajouterLivraison(livraison);
                    liv.setLibre(false);
                    v.setLibre(false);
                    return c.calcul_prix();
                } else return -2; //Le solde du client est insuffisant

            }
            else return -1 ; //Pas de livreur ou véhicule libre

    }


}