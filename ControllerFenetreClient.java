package Controleur;

import TP.*;
import Vue.FenetreEspaceClient;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;

public class ControllerFenetreClient implements ActionListener {
    private FenetreEspaceClient vue;
    private Pizzeria pizzeria;

    public ControllerFenetreClient(FenetreEspaceClient fenetreClient, Pizzeria pizzeria) {
        this.vue = fenetreClient;
        this.pizzeria = pizzeria;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vue.getConsulterButton()) {
            consulterClient();
        } else if (e.getSource() == vue.getIncrementerButton()) {
            rechargerCompteClient();
        } else if (e.getSource() == vue.getClearButton()) {
            clearClientInfo();
        } else if (e.getSource() == vue.getComboBoxCommandes()) {
            afficherLignesCommande();
        }
    }

    private void consulterClient() {
        String numClient = vue.getNumJTextField().getText();
        Client client = pizzeria.client_exist(numClient);
        if (client != null) {
            // Afficher les informations du client
            JTextArea soldeClientTextArea = vue.getSoldeClientTextArea();
            soldeClientTextArea.setText(""); // Effacer le contenu précédent
            soldeClientTextArea.append("Numéro de téléphone : " + client.getNum_tel() + "\n");
            soldeClientTextArea.append("Solde : " + client.getSolde() + "\n");

            // Afficher les commandes du client dans la liste déroulante
            DefaultComboBoxModel<String> comboBoxModel = vue.getComboBoxModel();
            comboBoxModel.removeAllElements(); // Effacer le contenu précédent
            for (Commande commande : client.getListCommandes()) {
                comboBoxModel.addElement(commande.getDate().toString());
            }

            // Ajout du code de vérification ici
            JComboBox<String> comboBoxCommandes = vue.getComboBoxCommandes();
            for (int i = 0; i < comboBoxCommandes.getItemCount(); i++) {
                System.out.println(comboBoxCommandes.getItemAt(i));
            }
        } else {
            // Afficher un message d'erreur si le client n'existe pas
            JOptionPane.showMessageDialog(null, "Client introuvable ! Veuillez vérifier le numéro de téléphone.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void mettreAJourComboBox(Client client) {
        // Afficher les commandes du client dans la liste déroulante
        DefaultComboBoxModel<String> comboBoxModel = vue.getComboBoxModel();
        comboBoxModel.removeAllElements(); // Effacer le contenu précédent
        for (Commande commande : client.getListCommandes()) {
            comboBoxModel.addElement(commande.getDate().toString());
        }
    }

    public void afficherLignesCommande() {
        // Récupérer l'index de l'élément sélectionné dans la liste déroulante
        int selectedIndex = vue.getComboBoxCommandes().getSelectedIndex();
        if (selectedIndex != -1) {
            // Récupérer la commande correspondant à l'index sélectionné
            String selectedDate = (String) vue.getComboBoxCommandes().getSelectedItem();
            String numClient = vue.getNumJTextField().getText();
            Client client = pizzeria.client_exist(numClient);
            if (client != null) {
                Commande selectedCommande = client.getListCommandes().get(selectedIndex);
                JTextArea lignesCommandeTextArea = vue.getLignesCommandeTextArea();
                
                // Effacer le contenu précédent de la zone de texte
                lignesCommandeTextArea.setText("");
                
                // Afficher les lignes de commande de la commande sélectionnée
                for (Ligne_Commande ligneCommande : selectedCommande.getSouCommandes()) {
                    lignesCommandeTextArea.append(ligneCommande.toString() + "\n");
                }
                
                // Ajouter une ligne vide après les lignes de commande
                lignesCommandeTextArea.append("\n");
                
                // Afficher le solde après avoir affiché les lignes de commande
                lignesCommandeTextArea.append("Numéro de téléphone : " + client.getNum_tel() + "\n");
                lignesCommandeTextArea.append("Solde : " + client.getSolde() + "\n");
                
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Commande introuvable !", "Erreur", JOptionPane.ERROR_MESSAGE);
    }


    private void rechargerCompteClient() {
        String numClient = vue.getNumJTextField().getText();
        Client client = pizzeria.client_exist(numClient);
        if (client != null) {
            try {
                // Recharger le compte du client avec le montant saisi
                double montant = Double.parseDouble(vue.getSoldeJTextField().getText());
                client.chargerSolde(montant);

                // Afficher le solde mis à jour
                JTextArea soldeClientTextArea = vue.getSoldeClientTextArea();
                soldeClientTextArea.setText(""); // Effacer le contenu précédent
                soldeClientTextArea.append("Numéro de téléphone : " + client.getNum_tel() + "\n");
                soldeClientTextArea.append("Solde : " + client.getSolde() + "\n");
            } catch (NumberFormatException ex) {
                // Afficher un message d'erreur si le montant saisi n'est pas valide
                JOptionPane.showMessageDialog(null, "Veuillez saisir un montant valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Afficher un message d'erreur si le client n'existe pas
            JOptionPane.showMessageDialog(null, "Client introuvable ! Veuillez vérifier le numéro de téléphone.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearClientInfo() {
        // Effacer le contenu de la zone de texte des informations du client
        JTextArea soldeClientTextArea = vue.getSoldeClientTextArea();
        soldeClientTextArea.setText("");
        
        // Effacer la liste déroulante
        vue.getComboBoxModel().removeAllElements();
        
        // Effacer les lignes de commande
        JTextArea lignesCommandeTextArea = vue.getLignesCommandeTextArea();
        lignesCommandeTextArea.setText("");
    }
}
