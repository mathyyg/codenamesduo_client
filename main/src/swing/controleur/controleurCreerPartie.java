package swing.controleur;

import code.*;
import codenames.CodeNamesClient;
import codenames.exceptions.CnBadLoginException;
import codenames.exceptions.CnNetworkException;
import swing.vue.FenetreRecherchePartie;
import swing.vue.LoginPan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class controleurCreerPartie implements ActionListener{
    private FenetreRecherchePartie fn;
    private CodeNamesClient serv;
    private joueur joueur;

    public controleurCreerPartie(FenetreRecherchePartie lafn, CodeNamesClient leserv, joueur lejoueur) {
        fn = lafn;
        serv = leserv;
        joueur = lejoueur;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int idPartie = 0;
        partie partie;
        if (fn.getNbTourSelect()) {
            try {
                idPartie = serv.createGame(fn.getJoueur(), fn.getNbTour());
            } catch (CnNetworkException ex) {
                ex.printStackTrace();
            } catch (CnBadLoginException ex) {
                ex.printStackTrace();
            }
            partie = new partie(fn.getNbTour(), joueur, idPartie);
        } else {
            try {
                idPartie = serv.createGame(fn.getJoueur());
            } catch (CnNetworkException ex) {
                ex.printStackTrace();
            } catch (CnBadLoginException ex) {
                ex.printStackTrace();
            }
        }
    }
}
