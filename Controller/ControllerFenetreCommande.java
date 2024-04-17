package Controller;

import Model.*;
import Vue.FenetreCommande;

import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;

public class ControllerFenetreCommande implements  ActionListener{
    FenetreCommande fenetreCommande  ;
    Pizzeria pizzeria ;
    Vector<Ligne_Commande> lignescommandes = new Vector<>();
    public ControllerFenetreCommande(FenetreCommande fenetreCommande, Pizzeria pizzeria){
        this.fenetreCommande = fenetreCommande ;
        this.pizzeria = pizzeria ;

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fenetreCommande.getAddLigneCJButton()){
            //Récupérer les informations
            Pizza selectedPizza = pizzeria.getCatalogue().searchByName((String)(fenetreCommande.getPizzasJComboBox().getSelectedItem())) ;
            Format_Pizza selectedFormat = (Format_Pizza) fenetreCommande.getFormatsJComboBox().getSelectedItem() ;
            int selectedQuantite = Integer.parseInt(fenetreCommande.getQuantiteJTextField().getText()) ;

            //Créer la ligne de commande à partir de ces informations
            Ligne_Commande ligneCommande = new Ligne_Commande(selectedQuantite,selectedPizza,selectedFormat) ;
            lignescommandes.add(ligneCommande) ;

            //Ajouter la ligne commande à l'affichage
            fenetreCommande.getListModel().addElement(ligneCommande.toString());

        } else if (e.getSource() == fenetreCommande.getCommanderJButton()) {
            if (!lignescommandes.isEmpty()){
                String numtel = fenetreCommande.getNumTelJTextField().getText() ;
                Client c =  pizzeria.client_exist(numtel) ;
                if (c!=null){
                      Commande cmd = new Commande(c, lignescommandes) ;
                      double nbretour = pizzeria.passerCommande(c,cmd) ;
                      System.out.println("nbretour = "+nbretour+"\n");
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
                else {
                       JOptionPane.showMessageDialog(null,"Désolé, le numéro de téléphone que vous avez saisi n'est pas reconnu. Veuillez vérifier et réessayer. Merci !","Client Introuvable",JOptionPane.ERROR_MESSAGE);
                }
            }
            else JOptionPane.showMessageDialog(null, "Vous n'avez pas sélectionné de Pizzas !! Veuillez choisir au moins une Pizza, une Quantité et un Format avant de passer votre commande.","Commande Vide !", JOptionPane.ERROR_MESSAGE);

        }

    }
}
