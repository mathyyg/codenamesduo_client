package swing.controleur;

import codenames.CodeNamesClient;
import codenames.exceptions.*;
import swing.vue.FenetrePartie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import code.*;

public class controleurSendClue implements ActionListener {
    private Partie partie;
    private FenetrePartie fn;
    private CodeNamesClient serv;

    public controleurSendClue(FenetrePartie lafn, CodeNamesClient leserv, Partie lapartie) {
        fn = lafn;
        partie = lapartie;
        serv = leserv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Indice indice = new Indice(fn.getClue(), fn.getMotParClue());
        if (indice.estValide(partie)) {
            try {
                serv.sendClue(partie.getIdPartie(), partie.getJ(), indice.getIndice(), indice.getNbMotPourIndice());
                System.out.println("Envoie clue réussi, state : " + serv.consultGame(partie.getIdPartie()).state());
                } catch (CnNetworkException ex) {
                    ex.printStackTrace();
                } catch (CnBadIdException ex) {
                    ex.printStackTrace();
                } catch (CnBadLoginException ex) {
                    ex.printStackTrace();
                } catch (CnWaitPlayerException ex) {
                    fn.ouvrirMessageErreur(ex.getMessage(), "Error WaitPlayerException");
                } catch (CnNotYourTurnException ex) {
                    fn.ouvrirMessageErreur(ex.getMessage(), "Error NotYourTurn");
                } catch (CnWaitAnswerException ex) {
                    fn.ouvrirMessageErreur(ex.getMessage(), "Error WaitAnswerException");
                } catch (CnClosedGameException ex) {
                    fn.ouvrirMessageErreur(ex.getMessage(), "Error partie gagné/perdu");
                }

        }
    }
}
