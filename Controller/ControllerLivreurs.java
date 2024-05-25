package Controller;

import Model.Livreur;
import Vue.FenetreDetailsLivraison;
import Vue.FenetreLivreurs;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControllerLivreurs implements ListSelectionListener , ActionListener {
    private FenetreLivreurs fenetreLivreurs ;

    public ControllerLivreurs(FenetreLivreurs fenetreLivreurs){
        this.fenetreLivreurs = fenetreLivreurs ;
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
          if (!e.getValueIsAdjusting()){
              Livreur livreurSelec = fenetreLivreurs.getJlistLivreursOccupes().getSelectedValue() ;
              if (livreurSelec != null){
                  new FenetreDetailsLivraison(livreurSelec) ;
              }
          }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fenetreLivreurs.getRefreshButton()){
           fenetreLivreurs.updateLivreurs();
        }
    }
}
