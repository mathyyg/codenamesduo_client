package swing.controleur;

import swing.vue.FenetreRecherchePartie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class controleurQuitterRecherche implements ActionListener {
    private FenetreRecherchePartie fn;

    public controleurQuitterRecherche(FenetreRecherchePartie lafn) {
        fn = lafn;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        fn.dispose();
    }
}