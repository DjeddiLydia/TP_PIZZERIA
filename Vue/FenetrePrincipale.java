package Vue ;

import Controller.ControllerPrincipal;
import Model.Pizzeria;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class FenetrePrincipale extends JFrame {
    private Image arriereplan ;
    private JButton btnCatalogue ;
    private JButton btnCommande ;
    private JButton btnClients ;
    private JButton btnLivreurs ;
    private Pizzeria pizzeria ;

    public FenetrePrincipale(Pizzeria pizzeria){
        super("Bienvenue à notre PIZZERIA !!");
        this.pizzeria = pizzeria ;

        //Charger l'image
        URL imgurl = getClass().getResource("arriereplan.jpg");
        if (imgurl != null){
            arriereplan = new ImageIcon(imgurl).getImage();
        }

        setContentPane(new BackgroundPanel());
        setLayout(new GridLayout(2,2,20,20));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        Color couleurprimaire = new Color(31,31,182);
        Color couleursecond = new Color(54,37,89);
        Color couleurtext = new Color(255,255,255);

        btnCatalogue = styleButton("MENU",couleurprimaire,couleurtext);
        btnCommande = styleButton("Commander",couleursecond,couleurtext);
        btnClients = styleButton("Espace Client",couleurprimaire,couleurtext);
        btnLivreurs = styleButton("Gestion des Livraisons",couleursecond,couleurtext);

        add(createCard(btnCatalogue, "Voir le menu des pizzas", couleurprimaire));
        add(createCard(btnCommande, "Passer une commande", couleursecond));
        add(createCard(btnClients, "Espace client", couleurprimaire));
        add(createCard(btnLivreurs, "Gérer les livraisons", couleursecond));

        ControllerPrincipal controllerPrincipal = new ControllerPrincipal(btnCatalogue,btnCommande,btnClients,btnLivreurs,pizzeria);
        btnLivreurs.addActionListener(controllerPrincipal);
        btnClients.addActionListener(controllerPrincipal);
        btnCommande.addActionListener(controllerPrincipal);
        btnCatalogue.addActionListener(controllerPrincipal);


        setLocation(70, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);


    }

    private class BackgroundPanel extends JPanel{
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            if (arriereplan!=null){
                g.drawImage(arriereplan,0,0,getWidth(),getHeight(),this);
            }
        }
    }

    private JPanel createCard(JButton button, String description , Color borderColor){
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(255, 255, 255, 150)); // Transparence
        card.setBorder(BorderFactory.createLineBorder(borderColor, 2));
        card.add(button, BorderLayout.CENTER);

        JLabel descriptionLabel = new JLabel(description, SwingConstants.CENTER);
        descriptionLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        descriptionLabel.setForeground(borderColor);
        card.add(descriptionLabel, BorderLayout.SOUTH);

        return card;
    }

    private JButton styleButton(String text , Color c , Color ctext){
        JButton button = new JButton(text) ;
        button.setFont(new Font("Arial",Font.BOLD,16));
        button.setBackground(c);
        button.setForeground(ctext);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        return button ;
    }
}