package swing.timerControleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Joueur;
import code.Partie;
import codenames.CodeNamesClient;
import codenames.exceptions.CnBadIdException;
import codenames.exceptions.CnNetworkException;
import codenames.states.STATE_STEP;
import codenames.states.State;
import swing.vue.*;

public class StateListener implements ActionListener {
    private FenetrePartie fn;
    private CodeNamesClient serv;
    private Partie partie;
    boolean majListIndice;
    private String j1; // Correspond toujours à nous
    private String j2; // Correspond toujours à notre adversaire
    private String jDoitRep;

    public StateListener(FenetrePartie lafn, Partie lapartie, CodeNamesClient leserv) {
        fn = lafn;
        partie = lapartie;
        serv = leserv;
        majListIndice = true;
        try {
            if (serv.consultGame(partie.getIdPartie()).creator().equals(partie.getJ().getPseudo())){
                j1 = serv.consultGame(partie.getIdPartie()).creator();
                j2 = serv.consultGame(partie.getIdPartie()).secondPlayer();
            } else {
                j2 = serv.consultGame(partie.getIdPartie()).creator();
                j1 = serv.consultGame(partie.getIdPartie()).secondPlayer();
            }
        } catch (CnNetworkException e) {
            e.printStackTrace();
        } catch (CnBadIdException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
                if (partie.getEtat().clueSender().equals(j1)){
                    System.out.println(partie.getEtat().clueSender() + "==" + j1);
                    System.out.println("vous avez envoyé un indice, ce n'est plus votre tour\nOn attend sa réponse");
                    //On vient d'envoyer l'indice, ce n'est plus notre tour.
                    jDoitRep = j2;
                } else {
                    System.out.println(partie.getEtat().clueSender() + "=/=" + j1);
                    System.out.println("Le joueur adverse a envoyé un indice, c'est à notre tour.\nIl faut choisir une réponse");
                    // récupère la currentClue et currentClueNumber
                    // on doit envoyer une réponse
                    if (majListIndice){
                        //
                        jDoitRep = j1;
                        //correspond aux réponses trouvés par l'autre user qu'il faut mettre sur notre plateau.
                        fn.majPreviousAnswer();
                        //ajoute le nouvel indice à la list des indices
                        fn.majListIndice();
                        majListIndice = false;
                    }
                }
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        if (partie.getEtat().state().equals(STATE_STEP.ANSWER_SENT)) {
            if (j1.equals(jDoitRep)){
                // on vient d'envoyer la réponse
                //on récupe currentAnswer (list des réponses validées par le serv
                //on update notre plateau en montrant ce qu'on a trouvé ou non
                // ex : 1er mot CODE, 2eme mot : NEUTRAL, 3eme mot : non vérifié du coup
                //
            } else {
                //on fait rien
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
