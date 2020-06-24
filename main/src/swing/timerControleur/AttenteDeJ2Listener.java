package swing.timerControleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Partie;
import codenames.CodeNamesClient;
import codenames.exceptions.CnBadIdException;
import codenames.exceptions.CnNetworkException;
import codenames.states.STATE_STEP;
import swing.vue.*;

public class AttenteDeJ2Listener implements ActionListener {
    FenetrePartie fn;
    CodeNamesClient serv;
    Partie partie;
    public AttenteDeJ2Listener(FenetrePartie lafn, Partie lapartie, CodeNamesClient leserv) {
        fn = lafn;
        partie = lapartie;
        serv = leserv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            System.out.println("En attente d'un autre joueur");
            if (serv.consultGame(partie.getIdPartie()).state().equals(STATE_STEP.GAME_JOIN)){
                System.out.println(partie.getEtat().state() + "\nFin de l'attente");
                fn.initGame();
                fn.startState();
                fn.stopAttenteJ2();
            }
        } catch (CnNetworkException ex) {
            ex.printStackTrace();
        } catch (CnBadIdException ex) {
            ex.printStackTrace();
        }

    }
}
