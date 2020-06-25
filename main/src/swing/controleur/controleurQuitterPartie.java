package swing.controleur;

import swing.vue.FenetrePartie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class controleurQuitterPartie implements ActionListener {
    private FenetrePartie fn;

    public controleurQuitterPartie(FenetrePartie lafn) {
        fn = lafn;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        fn.retour(true);
    }
}