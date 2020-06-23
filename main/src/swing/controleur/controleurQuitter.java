package swing.controleur;

import code.Joueur;
import codenames.CodeNamesClient;
import swing.vue.FenetreRecherchePartie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class controleurQuitter implements ActionListener {
    private FenetreRecherchePartie fn;


    public controleurQuitter(FenetreRecherchePartie lafn) {
        fn = lafn;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        fn.dispose();
    }
}
