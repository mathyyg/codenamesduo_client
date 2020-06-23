package swing.controleur;

import code.Joueur;
import codenames.CodeNamesClient;
import codenames.exceptions.CnBadLoginException;
import codenames.exceptions.CnNetworkException;
import swing.vue.FenetreRecherchePartie;
import swing.vue.FenetreLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

public class rechercheToursControleur implements ItemListener {
    private FenetreRecherchePartie pan;
    private CodeNamesClient serv;

    public rechercheToursControleur(FenetreRecherchePartie pan) {
        this.pan = pan;
        this.serv = new CodeNamesClient("http://51.178.49.138:3000/api/v0");
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED){
            pan.toursEtat(true);
        }
        else if(e.getStateChange() == ItemEvent.DESELECTED){
            pan.toursEtat(false);
        }
    }
}
