package swing.controleur;

import codenames.CodeNamesClient;
import swing.vue.FenetreRecherchePartie;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class controleurRechercheTours implements ItemListener {
    private FenetreRecherchePartie fn;

    public controleurRechercheTours(FenetreRecherchePartie lafn) {
        this.fn = lafn;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED){
            fn.toursEtat(true);
        }
        else if(e.getStateChange() == ItemEvent.DESELECTED){
            fn.toursEtat(false);
        }
    }
}
