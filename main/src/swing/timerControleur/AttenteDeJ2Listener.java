package swing.timerControleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.Partie;
import codenames.CodeNamesClient;
import codenames.exceptions.CnBadIdException;
import codenames.exceptions.CnNetworkException;
import codenames.states.STATE_STEP;
import swing.vue.*;

/**
 * La classe AttenteDeJ2Listener est un listener appelé par l'instance timerAttenteJ2 de la fenêtre partie
 * Elle se lance quand on est seul dans une partie et elle sert à savoir quand un 2ème joueur nous rejoint
 * afin de lancer la partie.
 *
 * @author Paul Vernin, Thomas Peray, Matéo Esteves, Mathys Gagner
 * @version 4.6
 */
public class AttenteDeJ2Listener implements ActionListener {
    FenetrePartie fn;
    CodeNamesClient serv;
    Partie partie;

    /**
     * Constructeur de AttenteDeJ2Listener
     * @param lafn la fenêtre de partie
     * @param lapartie la partie contenant toutes les infos utiles (id, keycard, login, etc...)
     * @param leserv le serveur qui nous sert à récupérer l'état de la partie
     */
    public AttenteDeJ2Listener(FenetrePartie lafn, Partie lapartie, CodeNamesClient leserv) {
        fn = lafn;
        partie = lapartie;
        serv = leserv;
    }

    /**
     * Action effectué par le timerAttenteJ2 toutes les 5s. Elle regarde si quelqu'un a rejoint grâce au
     * state renvoyé par le serveur
     * @param e actionEvent (non utilisé)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
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
