package swing.timerControleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.Partie;
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
                fn.resetConsole();
                fn.updateConsole("Un joueur a été trouvé.\nFin de l'attente.");
                fn.initGame();
                fn.setEnableSendAnswer(true);
                fn.setEnableSendClue(true);
                fn.modeDeJeuTab(0);
                fn.startState();
                fn.stopAttenteJ2();
            } else {
                if (!fn.getConsoleText().equals("En attente d'un autre joueur.\n")){
                    fn.updateConsole("En attente d'un autre joueur.");
                    fn.modeDeJeuTab(-1);
                }
            }
        } catch (CnNetworkException ex) {
            fn.ouvrirMessageErreur(ex.getMessage(),"Erreur serveur");
        } catch (CnBadIdException ex) {
            fn.ouvrirMessageErreur(ex.getMessage(), "Erreur ID partie");
        }

    }
}
