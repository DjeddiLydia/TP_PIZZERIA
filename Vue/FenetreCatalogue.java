package Vue;

import Model.Catalogue;
import Model.Pizza;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class FenetreCatalogue extends JFrame {
    private Catalogue catalogue;
    private JPanel mainPanel;

    public FenetreCatalogue(Catalogue catalogue) {
        super("Menu de la Pizzeria");
        this.catalogue = catalogue;

        // Configuration de la fenêtre principale
        mainPanel = new JPanel(new GridLayout(0, 3, 10, 10)); // 3 colonnes, avec espace de 10 pixels entre les composants
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espacement autour des bords
        //palette de couleurs
        Color couleurprimaire = new Color(31,31,182);
        Color couleursecond = new Color(54,37,89);
        Color couleurtext = new Color(255,255,255);
        // Ajout des pizzas au panneau principal
        afficherMenu(couleurprimaire,couleursecond,couleurtext);

        // Ajout du panneau principal à la fenêtre
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        getContentPane().add(scrollPane);

        // Configuration de la fenêtre
        setLocation(600,150);
        pack();
        setVisible(true);
    }

    private void afficherMenu(Color c1 , Color c2 , Color c3) {
        for (Pizza pizza : catalogue.getListPizzas()) {
            JPanel pizzaPanel = createPizzaPanel(pizza,c1,c2,c3);
            mainPanel.add(pizzaPanel);
        }
    }

    private JPanel createPizzaPanel(Pizza pizza, Color c1 , Color c2 , Color c3) {
        JPanel pizzaPanel = new JPanel(new BorderLayout());
        pizzaPanel.setBorder(BorderFactory.createLineBorder(c1, 2)); // Utilisation de la couleur primaire pour la bordure
        pizzaPanel.setBackground(new Color(255, 255, 255, 150)); // Transparence

        JLabel nameLabel = new JLabel(pizza.getNom(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Police en gras
        nameLabel.setForeground(c1); // Utilisation de la couleur primaire pour le texte

        JTextArea ingredientsArea = new JTextArea(pizza.getIngredientsAsString());
        ingredientsArea.setWrapStyleWord(true);
        ingredientsArea.setLineWrap(true);
        ingredientsArea.setEditable(false);
        ingredientsArea.setOpaque(false); // Rendre transparent
        ingredientsArea.setFont(new Font("Arial", Font.PLAIN, 14)); // Police normale
        ingredientsArea.setForeground(c2); // Utilisation de la couleur secondaire pour le texte

        JLabel priceLabel = new JLabel("Prix : " + pizza.getPrix_base() + " €", SwingConstants.CENTER);
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Police normale
        priceLabel.setForeground(c2); // Utilisation de la couleur secondaire pour le texte

        pizzaPanel.add(nameLabel, BorderLayout.NORTH);
        pizzaPanel.add(ingredientsArea, BorderLayout.CENTER);
        pizzaPanel.add(priceLabel, BorderLayout.SOUTH);

        return pizzaPanel;
    }
    }





