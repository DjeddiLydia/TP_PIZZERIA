package Vue;
import java.util.*;
import Model.* ;


public class Main {
    public static void main (String[] args){
        //Création de la pizzeria
        Pizzeria pizzeria = new Pizzeria() ;
        //Création du catalogue
        List<Pizza> pizzas = Arrays.asList(
          new Pizza("Margherita", 8.99, new Vector<>(Arrays.asList("Tomate", "Mozzarella"))),
          new Pizza("Pepperoni", 10.99, new Vector<>(Arrays.asList("Pepperoni", "Tomate", "Mozzarella"))),
          new Pizza("Quattro Stagioni", 12.99, new Vector<>(Arrays.asList("Champignons", "Olives", "Tomate", "Mozzarella"))),
          new Pizza("Hawaiian", 11.99, new Vector<>(Arrays.asList("Ananas", "Jambon", "Tomate", "Mozzarella")))
        ) ;
        Catalogue catalogue = new Catalogue("Menu Pizzas", new Vector<>(pizzas),pizzeria) ;
        pizzeria.setCatalogue(catalogue);

        //Création des clients
        Vector<Client> clients = new Vector<>();
        clients.add(new Client("0660123456", pizzeria));
        clients.add(new Client("0671234567",pizzeria));
        clients.add(new Client("0652345678",pizzeria));
        clients.add(new Client("0713456789",pizzeria));
        clients.add(new Client("0764567890",pizzeria));
        pizzeria.setClients(clients);

        //Création des livreurs
        Vector<Livreur> livreurs = new Vector<>();
        livreurs.add(new Livreur("Jean",pizzeria)) ;
        livreurs.add(new Livreur("Paul",pizzeria)) ;
        livreurs.add(new Livreur("Marie",pizzeria)) ;
        livreurs.add(new Livreur("Sophie",pizzeria)) ;
        pizzeria.setListLivreurs(livreurs);

        /*for(Livreur liv : livreurs){
            liv.setLibre(false);
        }*/

        //Création des véhicules
        Vector<Vehicule> vehicules = new Vector<>();
        vehicules.add(new Vehicule("Toyota", "1234AB", Type_Vehicule.Voiture, pizzeria));
        vehicules.add(new Vehicule("Honda", "5678CD", Type_Vehicule.Voiture, pizzeria));
        vehicules.add(new Vehicule("Yamaha", "9012EF", Type_Vehicule.Moto, pizzeria));
        pizzeria.setVehicules(vehicules);

        FenetreCommande fenetreCommande = new FenetreCommande(pizzeria) ;


    }
}
