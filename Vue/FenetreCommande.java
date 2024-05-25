package Vue;

import Controller.ControllerFenetreCommande;
import Model.Pizzeria;
import Model.Format_Pizza;

import javax.swing.*;
import java.awt.*;

public class FenetreCommande extends JFrame {
    Model.Pizzeria pizzeria ;
    JTextField quantite = new JTextField("0") ;
    JTextField numTel = new JTextField("0000000000") ;
    JComboBox<String> pizzas  ;
    JComboBox<Format_Pizza> formats = new JComboBox<>(Format_Pizza.values()) ;
    JButton addLigneC = new JButton("Ajouter");
    JButton supprLigneC = new JButton("Supprimer");
    JButton commander = new JButton("Commander");
    DefaultListModel<String> listModel = new DefaultListModel<>();
    JList<String> listLignesCommande = new JList<>(listModel);
    JScrollPane scrollLignesC = new JScrollPane(listLignesCommande);
    public FenetreCommande(Pizzeria pizzeria){
        super("Commander");
       this.pizzeria = pizzeria ;
       pizzas = new JComboBox<>(pizzeria.getCatalogue().getNomsPizzas()) ;
        // Définition des couleurs
        Color couleurPrimaire = new Color(31, 31, 182);
        Color couleurSecondaire = new Color(54, 37, 89);
        Color couleurTexte = new Color(255, 255, 255);

       //Le coté gauche de la fenetre
        JPanel panelGauche = new JPanel(new GridLayout(5, 2));
        panelGauche.add(createStyledLabel("Numéro de téléphone :", couleurPrimaire));
        panelGauche.add(numTel);
        panelGauche.add(createStyledLabel("Choisir la Pizza :", couleurPrimaire));
        panelGauche.add(pizzas);
        panelGauche.add(createStyledLabel("Saisir la quantité :", couleurPrimaire));
        panelGauche.add(quantite);
        panelGauche.add(createStyledLabel("Choisir le Format:", couleurPrimaire));
        panelGauche.add(formats);

        // Boutons de gauche
        supprLigneC = styleButton("Supprimer", couleurSecondaire, couleurTexte);
        addLigneC = styleButton("Ajouter", couleurSecondaire, couleurTexte);
        panelGauche.add(supprLigneC);
        panelGauche.add(addLigneC);

        // Le côté droit de la fenêtre
        JPanel panelDroit = new JPanel(new BorderLayout());
        panelDroit.add(scrollLignesC, BorderLayout.CENTER);
        commander = styleButton("Commander", couleurPrimaire, couleurTexte);
        panelDroit.add(commander, BorderLayout.SOUTH);

       //Création de JSplitPane pour diviser la fenetre en 2
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,panelGauche,panelDroit) ;
        splitPane.setDividerLocation(300);
        getContentPane().add(splitPane) ;

        Controller.ControllerFenetreCommande crtl = new ControllerFenetreCommande(this,pizzeria) ;
        addLigneC.addActionListener(crtl);
        commander.addActionListener(crtl);
        supprLigneC.addActionListener(crtl);
        setLocation(600,150);
        pack();
        setVisible(true);

    }

    private JLabel createStyledLabel(String text, Color textColor) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(textColor);
        return label;
    }

    private JButton styleButton(String text, Color bgColor, Color textColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    public JButton getAddLigneCJButton() {
        return addLigneC;
    }

    public JButton getCommanderJButton() {
        return commander;
    }

    public JTextField getQuantiteJTextField() {
        return quantite;
    }

    public JComboBox<String> getPizzasJComboBox() {
        return pizzas;
    }

    public JComboBox<Format_Pizza> getFormatsJComboBox() {
        return formats;
    }

    public DefaultListModel<String> getListModel() {
        return listModel;
    }

    public JTextField getNumTelJTextField() {
        return numTel;
    }
    public JButton getSupprLigneCJButton(){
        return  supprLigneC ;
    }

    public boolean verifQuantite(int q){
        if (q>0){
            quantite.setForeground(Color.BLACK);
            return true ;
        }
        quantite.setText("Invalide");
        quantite.setForeground(Color.RED);
        return false ;

    }


    public void erreurNumTel(){
        numTel.setText("HERE !");
        numTel.setForeground(Color.RED);
    }


}
