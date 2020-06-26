package swing.controleur;

import codenames.CodeNamesClient;
import codenames.exceptions.*;
import swing.vue.FenetrePartie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modele.*;

/**
 * Contrôleur qui permet d'envoyer au serveur un indice et un nombre de mot correspondant
 *
 * @author Paul Vernin, Thomas Peray, Matéo Esteves, Mathys Gagner
 * @version 4.6
 */
public class controleurSendClue implements ActionListener {
    private Partie partie;
    private FenetrePartie fn;
    private CodeNamesClient serv;

    /**
     * Constructeur du contrôleur SendClue
     * @param lafn la fenêtre partie
     * @param leserv le serveur
     * @param lapartie la partie en cours
     */
    public controleurSendClue(FenetrePartie lafn, CodeNamesClient leserv, Partie lapartie) {
        fn = lafn;
        partie = lapartie;
        serv = leserv;
    }

    /**
     * Envoie un indice s'il est valide
     * affiche des erreurs suivant l'exception sinon
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        Indice indice = new Indice(fn.getClue(), fn.getMotParClue());
        if (indice.estValide(partie)) {
            try {
                serv.sendClue(partie.getIdPartie(), partie.getJ(), indice.getIndice(), indice.getNbMotPourIndice());
            } catch (CnNetworkException ex) {
                fn.ouvrirMessageErreur("Le serveur semble être inaccessible","Erreur d'envoie d'indice");
            } catch (CnBadIdException ex) {
                fn.ouvrirMessageErreur("L'ID de la partie est inaccessible","Erreur d'envoie d'indice");
            } catch (CnBadLoginException ex) {
                fn.ouvrirMessageErreur("Votre login semble être inaccessible","Erreur d'envoie d'indice");
            } catch (CnWaitPlayerException ex) {
                fn.ouvrirMessageErreur("Aucun autre joueur n'a rejoint votre partie", "Error WaitPlayerException");
            } catch (CnNotYourTurnException ex) {
                fn.ouvrirMessageErreur("Ce n'est pas votre tour", "Error NotYourTurn");
            } catch (CnWaitAnswerException ex) {
                fn.ouvrirMessageErreur("Une réponse est attendu et pas un indice", "Error WaitAnswerException");
            } catch (CnClosedGameException ex) {
                fn.ouvrirMessageErreur("La partie est terminé", "Error partie gagné/perdu");
            }
            fn.resetClue();
        }
    }
}
