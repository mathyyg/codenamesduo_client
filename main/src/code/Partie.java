package code;

import codenames.CodeNamesClient;
import codenames.states.*;
import codenames.cards.*;
import codenames.exceptions.*;

import java.util.List;

public class Partie {

    private CodeNamesClient serveur = new CodeNamesClient("http://51.178.49.138:3000/api/v0");
    private int idPartie;
    private int nbTour;
    private State etat;
    private Joueur joueur;
    private int id;
    private List<String> plateau;
    private List<CARD_ROLE> keyCard;


    public Partie(int lenbTour, Joueur lej1, int lidPartie) {
        nbTour = lenbTour;
        joueur = lej1;
        idPartie = lidPartie;
    }

    public Partie(int lid, int leNbTour, State letat, Joueur j1) throws CnBadIdException, CnNetworkException, CnBadLoginException, CnBadPwdException {
        nbTour = leNbTour;
        etat = letat;
        joueur = j1;
        id = lid;
        plateau = serveur.consultGame(id).words();
        keyCard = serveur.keyCards(id,joueur,joueur.getMdp());
    }
}
