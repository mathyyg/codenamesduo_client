package swing.timerControleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Partie;
import codenames.CodeNamesClient;
import codenames.exceptions.CnBadIdException;
import codenames.exceptions.CnNetworkException;
import codenames.states.STATE_STEP;
import codenames.states.State;
import swing.vue.*;

public class StateListener implements ActionListener {
    FenetrePartie fn;
    CodeNamesClient serv;
    Partie partie;
    public StateListener(FenetrePartie lafn, Partie lapartie, CodeNamesClient leserv) {
        fn = lafn;
        partie = lapartie;
        serv = leserv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean majListIndice = true;
        try {
            partie.setEtat(serv.consultGame(partie.getIdPartie()));
            System.out.println("Etat actuel de la partie : "+partie.getEtat().state());
        } catch (CnNetworkException ex) {
            ex.printStackTrace();
        } catch (CnBadIdException ex) {
            ex.printStackTrace();
        }
        if (partie.getEtat().state().equals(STATE_STEP.CLUE_SENT)) {
            try {
                if (partie.getEtat().clueSender().equals(partie.getJ().login())){
                    System.out.println(partie.getEtat().clueSender() + "==" + partie.getJ().login());
                    System.out.println("vous avez envoyé un indice, ce n'est plus votre tour\nOn attend sa réponse");
                    //On vient d'envoyer l'indice, ce n'est plus notre tour.
                } else {
                    System.out.println(partie.getEtat().clueSender() + "=/=" + partie.getJ().login());
                    System.out.println("Le joueur adverse a envoyé un indice, c'est à notre tour.\nIl faut choisir une réponse");
                    // récupère la currentClue et currentClueNumber
                    // on doit envoyer une réponse
                    if (majListIndice){
                        fn.majListIndice();
                        majListIndice = false;
                    }
                }
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        if (partie.getEtat().state().equals(STATE_STEP.ANSWER_SENT)) {
            try {
                if (partie.getEtat().clueSender().equals(partie.getJ().login())){
                    //on récupe currentAnswer (list des réponses validées par le serv
                    //on update notre plateau en montrant ce qu'on a trouvé ou non
                    // ex : 1er mot CODE, 2eme mot : NEUTRAL, 3eme mot : non vérifié du coup
                    //
                } else {

                }
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }

        if (partie.getEtat().state().equals(STATE_STEP.GAME_WON)) {
            //On a gagné la partie, on ajoute +1 à joueur.setGameWin.
        }

        if (partie.getEtat().state().equals(STATE_STEP.GAME_LOST)) {
            //On a perdu la partie, on ajoute +1 à joueur.setGameLost.
        }


    }
}
