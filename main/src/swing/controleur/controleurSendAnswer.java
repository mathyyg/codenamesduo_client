package swing.controleur;

import codenames.CodeNamesClient;
import codenames.exceptions.*;
import swing.vue.FenetrePartie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import codenames.cards.*;
import modele.*;

/**
 * Contrôleur qui permet d'envoyer au serveur une réponse à un indice
 *
 * @author Paul Vernin, Thomas Peray, Matéo Esteves, Mathys Gagner
 * @version 4.6
 */
public class controleurSendAnswer implements ActionListener {
    private Partie partie;
    private FenetrePartie fn;
    private CodeNamesClient serv;

    /**
     * Constructeur du contrôleur SendAnswer
     * @param lafn la fenêtre partie
     * @param leserv le serveur
     * @param lapartie la partie en cours
     */
    public controleurSendAnswer(FenetrePartie lafn, CodeNamesClient leserv, Partie lapartie) {
        fn = lafn;
        partie = lapartie;
        serv = leserv;
    }

    /**
     * Envoie une réponse de la liste des cartes sélectionée; impossible s'il n'y a plus de carte.
     * affiche des erreurs suivant l'exception sinon
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        List<String> listReponses = fn.getAnswer();
        List<Card> cards;

        try {
            cards = serv.sendAnswer(partie.getIdPartie(), partie.getJ(), listReponses);
            if (cards.get(cards.size()-1).cardRole().equals(CARD_ROLE.NO_MORE_CODE)){
                fn.updateConsole("Vous avez fini de trouver vos CODE. Vous ne pourrez que soumettre des indices à votre coéquipier à" +
                        "partir de maintenant.");
                fn.setNoMoreCard(true);
                cards.remove(cards.size()-1);
            }

            List<Carte> cList = new ArrayList<>();
            for (Card c : cards)
                cList.add(new Carte(c));
            fn.updatePlateau(cList);
        } catch (CnNetworkException ex) {
            fn.ouvrirMessageErreur("Le serveur semble être inaccessible","Erreur d'envoie de réponse");
        } catch (CnBadIdException ex) {
            fn.ouvrirMessageErreur("L'ID de la partie est inaccessible","Erreur d'envoie de réponse");
        } catch (CnBadLoginException ex) {
            fn.ouvrirMessageErreur("Votre login semble être inaccessible","Erreur d'envoie de réponse");
        } catch (CnNotYourTurnException ex) {
            fn.ouvrirMessageErreur("Ce n'est pas votre tour","Erreur d'envoie de réponse");
        } catch (CnClosedGameException ex) {
            fn.ouvrirMessageErreur("La partie est déjà terminé","Erreur d'envoie de réponse");
        } catch (CnEmptyAnswerException ex) {
            fn.ouvrirMessageErreur("Votre réponse est vide","Erreur d'envoie de réponse");
        } catch (CnUnknownAnswerException ex) {
            fn.ouvrirMessageErreur("Un des mots de votre réponse n'appartient " +
                    "pas aux 25 mots de la partie","Erreur d'envoie de réponse");
        } catch (CnWaitClueException ex) {
            fn.ouvrirMessageErreur("Un indice est attendu à la place d'une réponse","Erreur d'envoie de réponse");
        }

    }
}
