package Model;

import java.io.*;
import java.util.*;
import java.util.Date ;
public class Client {
    private String num_tel;
    private double solde = 200 ;
    private Vector<Commande> listCommandes = new Vector<>() ;
    private Pizzeria pizzeria;
    private int nbpizzasAchetees = 0 ;
    private List<ClientObserver> observers = new ArrayList<>();

    public Client() {
    }
    public Client(String num_tel,Pizzeria pizzeria ){
        this.num_tel = num_tel ;
        this.pizzeria = pizzeria ;
    }
    public String getNum_tel(){
        return num_tel ;
    }
    public void setNbpizzasAchetees(int nbpizzasAchetees) {
        this.nbpizzasAchetees = nbpizzasAchetees;
    }

    public boolean pizzaGratuite (){
        if (nbpizzasAchetees > 10) return true ;
        return false ;
    }

    public boolean passerCommande(Commande c){
        System.out.println("solde ="+solde + "\n");
        System.out.println("prix commande  ="+ c.calcul_prix()+ "\n");
        if (solde >= c.calcul_prix()) {

            solde = solde - c.calcul_prix() ;
            listCommandes.add(c);
            return true ;

        }
        else return false ; //Le solde est insuffisant pour passer la commande
    }

    public int getNbpizzasAchetees(){
        return nbpizzasAchetees ;
    }




    public void chargerSolde (double montant){
        solde += montant ;
    }


    public double getSolde() {
        return solde ;
    }

    public Vector<Commande> getListCommandes() {
        return listCommandes ;
    }

    public void addObserver(ClientObserver observer) {

        observers.add(observer);

    }



    public void removeObserver(ClientObserver observer) {

        observers.remove(observer);

    }



    private void notifyObservers() {

        for (ClientObserver observer : observers) {

            observer.updateCommandes();

        }

    }

    public interface ClientObserver {

        void updateCommandes();

    }
}