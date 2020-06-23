package swing.controleur;

import code.Joueur;
import codenames.CodeNamesClient;
import codenames.exceptions.CnBadIdException;
import codenames.exceptions.CnBadLoginException;
import codenames.exceptions.CnFullPlayersException;
import codenames.exceptions.CnNetworkException;
import swing.vue.FenetreRecherchePartie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class controleurQuitterPartie implements ActionListener {
    private FenetreRecherchePartie fn;
    private CodeNamesClient serv;
    private Joueur joueur;

    public controleurQuitterPartie(FenetreRecherchePartie lafn, CodeNamesClient leserv) {
        fn = lafn;
        serv = leserv;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        fn.dispose();
    }
}
