package swing.controleur;

import swing.vue.FenetreRecherchePartie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class controleurRefreshPAttente implements ActionListener {
    private FenetreRecherchePartie fn;

    public controleurRefreshPAttente(FenetreRecherchePartie lafn) {
        fn = lafn;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        fn.refresh();
    }
}
