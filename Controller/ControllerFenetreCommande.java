package Controller;

import Model.*;
import Vue.FenetreCommande;
import Vue.FenetreLivreurs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class ControllerFenetreCommande implements  ActionListener{
    FenetreCommande fenetreCommande  ;
    Pizzeria pizzeria ;
    Commande cmd = new Commande();
    //Vector<Ligne_Commande> lignescommandes = new Vector<>();
    public ControllerFenetreCommande(FenetreCommande fenetreCommande, Pizzeria pizzeria){
        this.fenetreCommande = fenetreCommande ;
        this.pizzeria = pizzeria ;

    }

    public void actionPerformed(ActionEvent e) {
        //Bouton Ajouter
        if (e.getSource() == fenetreCommande.getAddLigneCJButton()){
            //Récupérer les informations
            gererAjoutLigneCmd();

        }
        //Bouton Commander
        if (e.getSource() == fenetreCommande.getCommanderJButton()) {
            if (!cmd.getSous_commandes().isEmpty()){
                String numtel = fenetreCommande.getNumTelJTextField().getText() ;
                Client c =  pizzeria.client_exist(numtel) ;
                if (c!=null){
                      fenetreCommande.getNumTelJTextField().setForeground(Color.BLACK);
                      cmd.setClient(c);
                      double nbretour = pizzeria.passerCommande(c,cmd) ;
                      showMessagesPasserCommande(nbretour);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Désolé, le numéro de téléphone que vous avez saisi n'est pas reconnu. Veuillez vérifier et réessayer. Merci !", "Client Introuvable", JOptionPane.ERROR_MESSAGE);
                    fenetreCommande.erreurNumTel();
                }
            }
            else JOptionPane.showMessageDialog(null, "Vous n'avez pas sélectionné de Pizzas !! Veuillez choisir au moins une Pizza, une Quantité et un Format avant de passer votre commande.","Commande Vide !", JOptionPane.ERROR_MESSAGE);

        }
        //Bouton Supprimer
        if (e.getSource() == fenetreCommande.getSupprLigneCJButton()){
            //Récupérer les informations
            gererSupprLigneCmd();

        }


    }

    public void showMessagesPasserCommande(double nbretour){
        if (nbretour == -1){
            JOptionPane.showMessageDialog(null,"Actuellement, aucun livreur ou véhicule n'est disponible. Veuillez réessayer ultérieurement.","Livraison Indisponible",JOptionPane.ERROR_MESSAGE);
        } else if (nbretour == -2) {
            JOptionPane.showMessageDialog(null,"Votre solde est insuffisant pour effectuer cette commande. Veuillez recharger votre compte avant de procéder. Merci !","Solde Insuffisant",JOptionPane.ERROR_MESSAGE);
        }
        else {
            String message = "Félicitations ! Votre commande a été passée avec succès.\n" + cmd.toString() ;
            JOptionPane.showMessageDialog(null, message, message, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //Gère l'affichage d'une ligne de commande ajoutée
    public void gererAjoutLigneCmd(){
        Pizza selectedPizza = pizzeria.getCatalogue().searchByName((String)(fenetreCommande.getPizzasJComboBox().getSelectedItem())) ;
        Format_Pizza selectedFormat = (Format_Pizza) fenetreCommande.getFormatsJComboBox().getSelectedItem() ;
        int selectedQuantite = Integer.parseInt(fenetreCommande.getQuantiteJTextField().getText()) ;


        if (fenetreCommande.verifQuantite(selectedQuantite)) {

            //Créer la ligne de commande à partir de ces informations
            Ligne_Commande ligneCommande = new Ligne_Commande(selectedQuantite, selectedPizza, selectedFormat);
            Ligne_Commande l = cmd.ajoutSousCommande(ligneCommande) ;
            if (l != ligneCommande){
                //On supprime le dernier élément
                fenetreCommande.getListModel().removeElementAt(fenetreCommande.getListModel().getSize() -1); ;
                ligneCommande = l ;

            }
            fenetreCommande.getListModel().addElement(ligneCommande.toString());

        }
    }
    public void gererSupprLigneCmd(){

        //Supprimer la dernière ligne de l'affichage
        fenetreCommande.getListModel().removeElementAt(fenetreCommande.getListModel().getSize() -1);
        //Supprimer la dernière ligne de la commande
        cmd.supprimerLignecmd();


    }




}
