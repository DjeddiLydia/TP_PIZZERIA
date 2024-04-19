package Model;

import java.util.Random;

public class Ligne_Commande {
    private int quantite;
    private Commande Commande;
    private Pizza pizza;
    private Format_Pizza taille;
    private double prix = 0 ;

    public Ligne_Commande(int q , Pizza p , Format_Pizza f) {
        quantite = q;
        pizza = p ;
        taille = f ;
        prix = calcul_prix() ;
    }

    public Ligne_Commande(int q ) {
        quantite = q;
    }
    public Pizza getPizza(){
        return pizza ;
    }
    public Format_Pizza getTaille(){
        return taille ;
    }
    public int getQuantite(){
        return quantite ;
    }
    public void setQuantite(int q){
        quantite = q ;
    }

    public double getPrix() {
        return prix;
    }

    public double calcul_prix (){
        if (taille == Format_Pizza.NAINE )  return (pizza.getPrix_base()/3) * quantite ;
        if ( taille == Format_Pizza.OGRESSE)  return  (pizza.getPrix_base()*3*quantite) ;
        return pizza.getPrix_base(); //Pizza de format "HUMAINE"
    }

    public void setRandFormat(){
        Random random = new Random();
        int randomNumber = random.nextInt(3) + 1;
        switch (randomNumber){
            case 1 :
                taille = Format_Pizza.NAINE ;
                break;
            case 2 :
                taille = Format_Pizza.HUMAINE ;
                break;
            case 3 :
                taille = Format_Pizza.OGRESSE ;
                break;
        }
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return quantite + " Pizza " + pizza.getNom() + " ( Format : " + taille + " ) ---- Prix : " + String.format("%.2f", prix)+"€";
    }

}