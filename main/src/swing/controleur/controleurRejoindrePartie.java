package swing.controleur;

import code.*;
import codenames.CodeNamesClient;
import codenames.exceptions.CnBadIdException;
import codenames.exceptions.CnBadLoginException;
import codenames.exceptions.CnFullPlayersException;
import codenames.exceptions.CnNetworkException;
import swing.vue.FenetreRecherchePartie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class controleurRejoindrePartie implements ActionListener {
    private FenetreRecherchePartie fn;
    private CodeNamesClient serv;
    private Joueur joueur;

    public controleurRejoindrePartie(FenetreRecherchePartie lafn, CodeNamesClient leserv, Joueur lejoueur) {
        fn = lafn;
        serv = leserv;
        joueur = lejoueur;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Partie partie;
        try {
            serv.joinGame(fn.getIDPartieSelected(), joueur);
            System.out.println("Join la partie " + fn.getIDPartieSelected() + "du joueur "+joueur.login());
            partie = new Partie(serv, serv.consultGame(fn.getIDPartieSelected()).maxTurns(), joueur, fn.getIDPartieSelected(), false);
            fn.second(partie);
        } catch (CnNetworkException ex) {
            fn.ouvrirMessageErreur(ex.getMessage(), "Erreur serveur");
        } catch (CnBadIdException ex) {
            fn.ouvrirMessageErreur(ex.getMessage(), "Erreur ID partie");
        } catch (CnBadLoginException ex) {
            fn.ouvrirMessageErreur(ex.getMessage(), "Erreur Login joueur");
        } catch (CnFullPlayersException ex) {
            fn.ouvrirMessageErreur(ex.getMessage(), "Erreur partie pleinne");
        } catch (NullPointerException ex) {
            fn.ouvrirMessageErreur("NullPointerException: aucune partie n'est sélectionnée", "Erreur ID partie");
        }
    }
}
