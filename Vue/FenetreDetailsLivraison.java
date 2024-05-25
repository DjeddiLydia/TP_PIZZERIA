package Vue;

import Controller.ControllerDetailsLivraison;
import Model.Livraison;
import Model.Livreur;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FenetreDetailsLivraison extends JFrame {

    private Livreur livreur ;
    private Livraison livraison;
    private JLabel detailsLabel;
    private JButton terminerButton;

    public FenetreDetailsLivraison(Livreur livreur ) {
        super("Détails de la Livraison en Cours");
        this.livreur = livreur ;
        this.livraison = livreur.getCurrentLivraison();

        //palette de couleurs
        Color primaryColor = new Color(18, 18, 177);
        Color secondColor = new Color(153,102,255);
        Color accentcolor = new Color(204,204,255);
        Color textcolor = new Color(255,255,255);


        detailsLabel = new JLabel(detailsLivraisonHTML(livraison.getLivraisonDetails()));
        detailsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        detailsLabel.setForeground(primaryColor);

        terminerButton = new JButton("Marquer comme effectuée");
        terminerButton.setFont(new Font("Arial",Font.BOLD,16));
        terminerButton.setBackground(secondColor);
        terminerButton.setForeground(textcolor);
        terminerButton.setFocusPainted(false);
        terminerButton.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        ControllerDetailsLivraison controllerDetailsLivraison = new ControllerDetailsLivraison(terminerButton,livreur);
        terminerButton.addActionListener(controllerDetailsLivraison);

        setLayout(new BorderLayout());
        JPanel detailsPanel = new JPanel() ;
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(new EmptyBorder(20,20,20,20));
        detailsPanel.setBackground(accentcolor);
        detailsPanel.add(detailsLabel);

        JPanel buttonPanel = new JPanel() ;
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(accentcolor);
        buttonPanel.add(terminerButton) ;

        add(detailsLabel, BorderLayout.CENTER);
        add(terminerButton, BorderLayout.SOUTH);


        setLocation(600,150);
        pack();
        setVisible(true);
    }

    public String detailsLivraisonHTML ( String details){
        return "<html><body style='width: 400px; font-family: Arial, sans-serif; font-size: 14px; color: #6666ff;'>" + details +"</body></html>" ;
    }


}

