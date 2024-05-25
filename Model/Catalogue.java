package Model;

import java.io.*;
import java.util.*;

public class Catalogue {
    private String titre;
    private Vector<Pizza> listPizzas = new Vector<>();
    private Pizzeria pizzeria;
    public Catalogue() {
    }
    public Catalogue(String t,Vector<Pizza> pz, Pizzeria pizzeria ){
        titre = t ;
        listPizzas = pz ;
        this.pizzeria = pizzeria ;
    }
    public Vector<Pizza> getListPizzas(){
        return listPizzas ;
    }
    public Pizza random_pizza(){
        Random random = new Random();
        int randomNumber = random.nextInt(listPizzas.size()) ;
        Pizza p = listPizzas.elementAt(randomNumber) ;
        return  p ;
    }

    public Vector<String> getNomsPizzas(){
        Vector<String> nomsPz = new Vector<>() ;
        for (Pizza p : listPizzas){
            nomsPz.add(p.getNom()) ;
        }
        return nomsPz ;
    }

    public Pizza searchByName(String nom ){
        for (Pizza p : listPizzas){
            if (p.getNom().equals(nom)) return p ;
        }
        return null ;
    }



}