package Vue;

import Controller.ControllerLivreurs;
import Model.Livreur;
import Model.Pizzeria;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FenetreLivreurs extends JFrame {
    private Pizzeria pizzeria;
    private  JList<Livreur> listLivreursOccupes;
    private  JList<Livreur> listLivreursNonOccupes;
    private  DefaultListModel<Livreur> modelLivreursOccupes;
    private  DefaultListModel<Livreur> modelLivreursNonOccupes;
    JButton refreshButton = new JButton("Rafraîchir");

    public FenetreLivreurs(Pizzeria pizzeria) {
        super("Gestion des Livraisons");
        this.pizzeria = pizzeria;

        // Initialisation des modèles de liste
        modelLivreursOccupes = new DefaultListModel<>();
        modelLivreursNonOccupes = new DefaultListModel<>();
        listLivreursOccupes = new JList<>(modelLivreursOccupes);
        listLivreursNonOccupes = new JList<>(modelLivreursNonOccupes);

        // Configuration des styles pour les JList
        listLivreursOccupes.setCellRenderer(new LivreursListCellRenderer());
        listLivreursNonOccupes.setCellRenderer(new LivreursListCellRenderer());

        ControllerLivreurs controllerLivreurs = new ControllerLivreurs(this) ;
        listLivreursOccupes.addListSelectionListener(controllerLivreurs);

        // Création des panneaux avec titres
        JPanel panelOccupes = new JPanel(new BorderLayout());
        panelOccupes.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED, 2), "Livreurs Occupés", 0, 0, new Font("Arial", Font.BOLD, 18), Color.RED));
        panelOccupes.add(new JScrollPane(listLivreursOccupes), BorderLayout.CENTER);

        JPanel panelNonOccupes = new JPanel(new BorderLayout());
        JPanel centerPanel = new JPanel(new GridLayout(1,2)) ;
        panelNonOccupes.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE, 2), "Livreurs Disponibles", 0, 0, new Font("Arial", Font.BOLD, 18), Color.BLUE));
        panelNonOccupes.add(new JScrollPane(listLivreursNonOccupes), BorderLayout.CENTER);
        centerPanel.add(panelOccupes);
        centerPanel.add(panelNonOccupes) ;

        // Configuration de la disposition
        setLayout(new BorderLayout());
        add(centerPanel,BorderLayout.CENTER);



        //Bouton refresh
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBorder(new EmptyBorder(10, 0, 10, 0)); // Ajout d'un peu d'espacement
        bottomPanel.add(refreshButton);
        add(bottomPanel,BorderLayout.SOUTH);

        refreshButton.addActionListener(controllerLivreurs);
        updateLivreurs();

        // Configuration de la fenêtre
        setLocation(600,150);
        pack();
        setVisible(true);
    }
    public JList<Livreur> getJlistLivreursOccupes (){
        return listLivreursOccupes ;
    }
    public JButton getRefreshButton(){
        return refreshButton ;
    }


    public void updateLivreurs() {
        modelLivreursOccupes.clear();
        modelLivreursNonOccupes.clear();

        for (Livreur livreur : pizzeria.getListLivreurs()) {
            if (livreur.isLibre()) {
                modelLivreursNonOccupes.addElement(livreur);
            } else {
                modelLivreursOccupes.addElement(livreur);
            }
        }
    }

    // Custom cell renderer for JList
    private static class LivreursListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Livreur) {
                Livreur livreur = (Livreur) value;
                label.setText(livreur.getNom()); // Affiche le nom du livreur
                label.setFont(new Font("Arial", Font.PLAIN, 16));
                if (isSelected) {
                    label.setBackground(new Color(173, 216, 230)); // Light Blue for selection
                    label.setForeground(Color.WHITE);
                } else {
                    label.setBackground(Color.WHITE);
                    label.setForeground(new Color(0, 0, 128)); // Navy Blue for text
                }
            }
            return label;
        }
    }

}
