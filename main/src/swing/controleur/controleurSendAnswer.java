package swing.controleur;

import codenames.CodeNamesClient;
import codenames.exceptions.*;
import swing.vue.FenetrePartie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import codenames.cards.*;

import code.*;

public class controleurSendAnswer implements ActionListener {
    private Partie partie;
    private FenetrePartie fn;
    private CodeNamesClient serv;

    public controleurSendAnswer(FenetrePartie lafn, CodeNamesClient leserv, Partie lapartie) {
        fn = lafn;
        partie = lapartie;
        serv = leserv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<String> listReponses = Arrays.asList(fn.getAnswer().split(","));
        List<Card> cards = null;

        try {
            cards = serv.sendAnswer(partie.getIdPartie(), partie.getJ(), listReponses);
            fn.updatePlateau(cards);
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
