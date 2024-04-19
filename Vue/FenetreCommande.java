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
    JComboBox<String> pizzas = new JComboBox<>() ;
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
       //Le coté gauche de la fenetre
       JPanel panelGauche = new JPanel(new GridLayout(5,2)) ;
       panelGauche.add(new JLabel("Numéro de téléphone :"));
       panelGauche.add(numTel);
       panelGauche.add(new JLabel("Choisir la Pizza :"));
       panelGauche.add(pizzas);
       panelGauche.add(new JLabel("Saisir la quantité : "));
       panelGauche.add(quantite);
       panelGauche.add(new JLabel("Choisir le Format:"));
       panelGauche.add(formats);
       panelGauche.add(supprLigneC) ;
       panelGauche.add(addLigneC);

       //Le coté droit de la fenetre
       JPanel panelDroit = new JPanel(new BorderLayout());
       panelDroit.add(scrollLignesC, BorderLayout.CENTER) ;
       panelDroit.add(commander, BorderLayout.SOUTH) ;

       //Création de JSplitPane pour diviser la fenetre en 2
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,panelGauche,panelDroit) ;
        splitPane.setDividerLocation(300);
        getContentPane().add(splitPane) ;

        Controller.ControllerFenetreCommande crtl = new ControllerFenetreCommande(this,pizzeria) ;
        addLigneC.addActionListener(crtl);
        commander.addActionListener(crtl);
        supprLigneC.addActionListener(crtl);
        pack();
        setVisible(true);

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
