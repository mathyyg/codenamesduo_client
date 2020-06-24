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
            ex.printStackTrace();
        } catch (CnBadIdException ex) {
            ex.printStackTrace();
        } catch (CnBadLoginException ex) {
            ex.printStackTrace();
        } catch (CnNotYourTurnException ex) {
            ex.printStackTrace();
        } catch (CnClosedGameException ex) {
            ex.printStackTrace();
        } catch (CnEmptyAnswerException ex) {
            ex.printStackTrace();
        } catch (CnUnknownAnswerException ex) {
            ex.printStackTrace();
        } catch (CnWaitClueException ex) {
            ex.printStackTrace();
        }
    }
}
