package Controller;

import Model.Livreur;
import Vue.FenetreLivreurs;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerDetailsLivraison implements ActionListener {
    private JButton terminerButton;
    private Livreur livreur ;

    public ControllerDetailsLivraison(JButton terminerButton , Livreur livreur){
        this.terminerButton = terminerButton ;
        this.livreur = livreur ;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == terminerButton) {
            livreur.getCurrentLivraison().endLivraison();
            ((JFrame) SwingUtilities.getWindowAncestor(terminerButton)).dispose();
        }
    }
}
