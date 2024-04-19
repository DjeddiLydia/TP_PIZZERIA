package Vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import Controleur.ControllerFenetreClient;

public class FenetreEspaceClient extends JFrame {
    private TP.Pizzeria pizzeria;
    private JTextField numeroClientTextField = new JTextField("0000000000");
    private JTextField incrementSoldeTextField = new JTextField("0");
    private JTextArea soldeClientTextArea = new JTextArea();
    private JButton incrementerSoldeButton = new JButton("Recharger");
    private JButton consulterClientButton = new JButton("Consulter");
    private JButton clearButton = new JButton("Clear");
    private DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
    private JComboBox<String> comboBoxCommandes = new JComboBox<>(comboBoxModel);
    private JScrollPane scrollCommandes = new JScrollPane(comboBoxCommandes);
    private JTextArea lignesCommandeTextArea = new JTextArea();

    public FenetreEspaceClient(TP.Pizzeria piz) {
        super("Espace Client");
        this.pizzeria = piz;

        // Création des composants
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Panneau pour saisir le numéro du client
        JPanel saisieNumeroPanel = new JPanel(new GridLayout(1, 2));
        saisieNumeroPanel.add(new JLabel("Numéro du client: "));
        saisieNumeroPanel.add(numeroClientTextField);

        // Panneau pour afficher le solde du client
        JPanel soldePanel = new JPanel(new BorderLayout());
        soldePanel.add(new JLabel("Solde du client: "), BorderLayout.NORTH);
        soldePanel.add(new JScrollPane(soldeClientTextArea), BorderLayout.CENTER);

        // Panneau pour sélectionner une commande
        JPanel selectionCommandePanel = new JPanel(new BorderLayout());
        selectionCommandePanel.add(new JLabel("Sélectionner une commande: "), BorderLayout.NORTH);
        selectionCommandePanel.add(scrollCommandes, BorderLayout.CENTER);

        // Panneau pour afficher les lignes de commande
        JPanel lignesCommandePanel = new JPanel(new BorderLayout());
        lignesCommandePanel.add(new JLabel("Lignes de commande: "), BorderLayout.NORTH);
        lignesCommandePanel.add(new JScrollPane(lignesCommandeTextArea), BorderLayout.CENTER);

        // Panneau pour saisir le montant à recharger
        JPanel saisieRechargePanel = new JPanel(new BorderLayout());
        saisieRechargePanel.add(new JLabel("Recharger: "), BorderLayout.WEST);
        JPanel saisieRechargeTextFieldPanel = new JPanel(new GridLayout(1, 2));
        saisieRechargeTextFieldPanel.add(incrementSoldeTextField);
        saisieRechargeTextFieldPanel.add(incrementerSoldeButton);
        saisieRechargePanel.add(saisieRechargeTextFieldPanel, BorderLayout.CENTER);

        // Panneau pour les boutons "Recharger", "Consulter" et "Clear"
        JPanel boutonsPanel = new JPanel(new GridLayout(3, 1));
        boutonsPanel.add(consulterClientButton);
        boutonsPanel.add(clearButton);

        // Ajout des panneaux au panneau principal
        panelPrincipal.add(saisieNumeroPanel, BorderLayout.NORTH);
        panelPrincipal.add(soldePanel, BorderLayout.WEST);
        panelPrincipal.add(selectionCommandePanel, BorderLayout.CENTER);
        panelPrincipal.add(lignesCommandePanel, BorderLayout.SOUTH);
        panelPrincipal.add(saisieRechargePanel, BorderLayout.SOUTH);
        panelPrincipal.add(boutonsPanel, BorderLayout.EAST);

        // Ajout du panneau principal à la fenêtre
        getContentPane().add(panelPrincipal);
        ControllerFenetreClient crtl = new ControllerFenetreClient(this, pizzeria);
        incrementerSoldeButton.addActionListener(crtl);
        consulterClientButton.addActionListener(crtl);
        clearButton.addActionListener(crtl);
        pack();
        setVisible(true);
    }
   
    // Getters pour les éléments nécessaires au contrôleur
    public JButton getIncrementerButton() {
        return incrementerSoldeButton;
    }

    public JButton getConsulterButton() {
        return consulterClientButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public JTextArea getSoldeClientTextArea() {
        return soldeClientTextArea;
    }

    public JTextField getNumJTextField() {
        return numeroClientTextField;
    }

    public JTextField getSoldeJTextField() {
        return incrementSoldeTextField;
    }

    public DefaultComboBoxModel<String> getComboBoxModel() {
        return comboBoxModel;
    }

    public JComboBox<String> getComboBoxCommandes() {
        return comboBoxCommandes;
    }

    public JTextArea getLignesCommandeTextArea() {
        return lignesCommandeTextArea;
    }
}
