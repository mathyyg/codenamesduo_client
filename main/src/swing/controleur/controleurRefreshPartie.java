package swing.controleur;

import code.Joueur;
import codenames.CodeNamesClient;
import swing.vue.FenetreRecherchePartie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class controleurRefreshPartie implements ActionListener {
    private FenetreRecherchePartie fn;
    private CodeNamesClient serv;
    private Joueur joueur;

    public controleurRefreshPartie(FenetreRecherchePartie lafn, CodeNamesClient leserv) {
        fn = lafn;
        serv = leserv;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        fn.refresh();
    }
}
