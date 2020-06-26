package swing.controleur;

import modele.*;
import codenames.CodeNamesClient;
import codenames.exceptions.CnBadIdException;
import codenames.exceptions.CnBadLoginException;
import codenames.exceptions.CnFullPlayersException;
import codenames.exceptions.CnNetworkException;
import swing.vue.FenetreMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Contrôleur qui permet de rejoindre une partie sélectionné dans la liste des parties en attente
 *
 * @author Paul Vernin, Thomas Peray, Matéo Esteves, Mathys Gagner
 * @version 4.6
 */
public class controleurRejoindrePartie implements ActionListener {
    private FenetreMenu fn;
    private CodeNamesClient serv;
    private Joueur joueur;

    /**
     * Constructeur du contrôleur RejoindrePartie
     * @param lafn la fenêtre Menu
     * @param leserv le serveur
     * @param lejoueur le joueur connecté
     */
    public controleurRejoindrePartie(FenetreMenu lafn, CodeNamesClient leserv, Joueur lejoueur) {
        fn = lafn;
        serv = leserv;
        joueur = lejoueur;
    }

    /**
     * Essaye de rejoindre la partie selectionée
     * affiche des erreurs suivant l'exception sinon
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Partie partie;
        try {
            serv.joinGame(fn.getIDPartieSelected(), joueur);
            partie = new Partie(serv, serv.consultGame(fn.getIDPartieSelected()).maxTurns(), joueur, fn.getIDPartieSelected());
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
