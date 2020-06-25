package swing.controleur;

import modele.*;
import codenames.CodeNamesClient;
import codenames.exceptions.CnBadLoginException;
import codenames.exceptions.CnNetworkException;
import swing.vue.FenetreRecherchePartie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class controleurCreerPartie implements ActionListener{
    private FenetreRecherchePartie fn;
    private CodeNamesClient serv;
    private Joueur joueur;

    public controleurCreerPartie(FenetreRecherchePartie lafn, CodeNamesClient leserv, Joueur lejoueur) {
        fn = lafn;
        serv = leserv;
        joueur = lejoueur;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int idPartie;
        Partie partie;
        if (fn.getNbTourSelect()) {

            try {
                idPartie = serv.createGame(fn.getJoueur(), Integer.parseInt(fn.getNbTour()));
                System.out.println("Créé une partie de X tours du joueur " +joueur +" avec comme ID : " + idPartie);
                partie = new Partie(serv, Integer.parseInt(fn.getNbTour()), joueur, idPartie);
                fn.second(partie);
            } catch (CnNetworkException ex) {
                fn.ouvrirMessageErreur(ex.getMessage(),"Erreur serveur");
            } catch (CnBadLoginException ex) {
                fn.ouvrirMessageErreur(ex.getMessage(),"Erreur de login");
            } catch (NumberFormatException ex){
                fn.ouvrirMessageErreur("la valeur spécifié n'est pas un nombre.", "Erreur création partie");
            }
        } else {
            try {
                idPartie = serv.createGame(fn.getJoueur());
                System.out.println("Créé une partie de 9 tours du joueur " +joueur +" avec comme ID : " + idPartie);
                partie = new Partie(serv, 9, joueur, idPartie);
                fn.second(partie);
            } catch (CnNetworkException ex) {
                fn.ouvrirMessageErreur(ex.getMessage(),"Erreur serveur");
            } catch (CnBadLoginException ex) {
                fn.ouvrirMessageErreur(ex.getMessage(),"Erreur de login");
            }
        }
    }
}
