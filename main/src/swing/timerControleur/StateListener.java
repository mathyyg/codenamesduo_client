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

import javax.swing.*;

public class StateListener implements ActionListener {
    private FenetrePartie fn;
    private CodeNamesClient serv;
    private Partie partie;
    private State EtatActuel;
    int i;

    public StateListener(FenetrePartie lafn, Partie lapartie, CodeNamesClient leserv) {
        fn = lafn;
        partie = lapartie;
        serv = leserv;
        try {
            EtatActuel = serv.consultGame(partie.getIdPartie());
        } catch (CnNetworkException e) {
            fn.ouvrirMessageErreur(e.getMessage(),"Erreur serveur");
        } catch (CnBadIdException e) {
            fn.ouvrirMessageErreur(e.getMessage(), "Erreur ID partie");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            partie.setEtat(serv.consultGame(partie.getIdPartie()));
            System.out.println("Etat actuel de la partie : "+partie.getEtat().state());
        } catch (CnNetworkException ex) {
            fn.ouvrirMessageErreur(ex.getMessage(),"Erreur serveur");
        } catch (CnBadIdException ex) {
            fn.ouvrirMessageErreur(ex.getMessage(), "Erreur ID partie");
        }

        // On vérifie si l'état à changer, cela permet d'éviter d'appeler les modifications sur la fenêtre en boucle
        if (!EtatActuel.state().equals(partie.getEtat().state())) {
            EtatActuel = partie.getEtat();

            if (partie.getEtat().state().equals(STATE_STEP.CLUE_SENT)) {
                int tour = partie.getNbTour() - partie.getEtat().finishedTurns();
                fn.updateConsole("Tours restant : " + String.valueOf(tour));
                try {
                    if (partie.getEtat().clueSender().equals(partie.getJ().getPseudo())) {
                        i = 0;
                        fn.modeDeJeuTab(i);
                        fn.updateConsole("Indice envoyé, veuillez attendre la réponse de \nvotre coéquipier.");
                        //On vient d'envoyer l'indice, ce n'est plus notre tour.
                    } else {
                        i = 1;
                        fn.modeDeJeuTab(i);
                        fn.updateConsole("L'indice est " + partie.getEtat().currentClue() + " pour "+
                                partie.getEtat().currentClueNumber() + " mots.\nChoisissez une ou des réponses");
                        //met à jour le plateau avec les previousAnswer.
                        fn.majPreviousAnswer();
                        //ajoute le nouvel indice à la list des indices
                        fn.majListIndice();
                    }
                } catch (IllegalAccessException ex) {
                    fn.ouvrirMessageErreur(ex.getMessage(),"Erreur : action interdite/impossible");
                }
            }
            if (partie.getEtat().state().equals(STATE_STEP.ANSWER_SENT)) {
                //Tout se fait dans le controleurSendAnswer pour cet état.
                if (i == 1){
                    fn.modeDeJeuTab(0);
                }
            }
            if (partie.getEtat().state().equals(STATE_STEP.GAME_WON)) {
                fn.updateConsole("Vous avez gagné la partie");
                fn.stopState();
                fn.retour(1);
            }
            if (partie.getEtat().state().equals(STATE_STEP.GAME_LOST)) {
                fn.updateConsole("Vous avez perdu la partie");
                fn.stopState();
                fn.retour(0);
            }

        }
    }
}
