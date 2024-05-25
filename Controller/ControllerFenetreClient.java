package Controller;

import Vue.FenetreEspaceClient;

import javax.swing.*;
import Model.* ;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class ControllerFenetreClient implements ActionListener, Client.ClientObserver {
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
        }
    }

    public void updateCommandes() {
        SwingUtilities.invokeLater(() -> {
            consulterClient();
        });
    }

    private void consulterClient() {
        String numClient = vue.getNumJTextField().getText();
        Client client = pizzeria.client_exist(numClient);
        if (client != null) {
            vue.getSoldeClientLabel().setText("" + client.getSolde());
            mettreAJourListeCommandes(client);
        } else {
            JOptionPane.showMessageDialog(null, "Client introuvable ! Veuillez vérifier le numéro de téléphone.", "Erreur", JOptionPane.ERROR_MESSAGE);
            DefaultListModel<String> commandesListModel = vue.getCommandesListModel();
            commandesListModel.clear();
            commandesListModel.addElement("Aucune commande");
            vue.getCommandesList().setModel(commandesListModel);
            vue.getCommandesList().setForeground(Color.RED);
        }
    }

    private void mettreAJourListeCommandes(Client client) {
        DefaultListModel<String> commandesListModel = vue.getCommandesListModel();
        commandesListModel.clear();
        if (!client.getListCommandes().isEmpty()) {
            for (Commande commande : client.getListCommandes()) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String dateStr = formatter.format(commande.getDate());
                commandesListModel.addElement(dateStr);
            }
            vue.getCommandesList().setForeground(Color.BLACK);
        } else {
            commandesListModel.addElement("Aucune commande");
            vue.getCommandesList().setForeground(Color.RED);
        }
        vue.getCommandesList().setModel(commandesListModel);
    }

    private void rechargerCompteClient() {
        String numClient = vue.getNumJTextField().getText();
        Client client = pizzeria.client_exist(numClient);
        if (client != null) {
            try {
                double montant = Double.parseDouble(vue.getSoldeJTextField().getText());
                if (montant > 0 && (int) montant == montant) {
                    client.chargerSolde(montant);
                    vue.getSoldeClientLabel().setText("Solde du client: " + client.getSolde());
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez saisir un montant entier positif.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Veuillez saisir un montant valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Client introuvable ! Veuillez vérifier le numéro de téléphone.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}

