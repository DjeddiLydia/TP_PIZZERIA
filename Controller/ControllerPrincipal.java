package Controller;

import Model.Pizzeria;
import Vue.FenetreCatalogue;
import Vue.FenetreCommande;
import Vue.FenetreEspaceClient;
import Vue.FenetreLivreurs;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerPrincipal implements ActionListener {
    private JButton btnCatalogue;
    private JButton btnCommande;
    private JButton btnClients;
    private JButton btnLivreurs;
    private Pizzeria pizzeria ;
    public ControllerPrincipal(JButton btnCatalogue , JButton btnCommande , JButton btnClients , JButton btnLivreurs,Pizzeria pizzeria){
        this.btnCatalogue = btnCatalogue ;
        this.btnCommande = btnCommande ;
        this.btnClients = btnClients ;
        this.btnLivreurs = btnLivreurs ;
        this.pizzeria = pizzeria ;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCatalogue){
            FenetreCatalogue fenetreCatalogue = new FenetreCatalogue(pizzeria.getCatalogue()) ;
        }
        if (e.getSource() == btnCommande) {
            FenetreCommande fenetreCommande = new FenetreCommande(pizzeria) ;
        }
        if (e.getSource() == btnLivreurs){
            FenetreLivreurs fenetreLivreurs = new FenetreLivreurs(pizzeria) ;
        }
        if (e.getSource() == btnClients){
            FenetreEspaceClient fenetreEspaceClient = new FenetreEspaceClient(pizzeria) ;
        }
    }
}
