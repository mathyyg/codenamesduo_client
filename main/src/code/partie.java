package code;

import codenames.CodeNamesClient;
import codenames.states.*;
import codenames.cards.*;
import codenames.exceptions.*;

import java.util.List;

public class partie {

    private CodeNamesClient serveur;
    private int nbTour;
    private State etat;
    private joueur joueur;
    private int id;
    private List<String> plateau;
    private List<CARD_ROLE> keyCard;


    public partie(CodeNamesClient c, int lid, int leNbTour, State letat, joueur j1) throws CnBadIdException, CnNetworkException, CnBadLoginException, CnBadPwdException {
        nbTour = leNbTour;
        etat = letat;
        joueur = j1;
        serveur = c;
        id = lid;
        plateau = c.consultGame(id).words();
        keyCard = c.keyCards(id,joueur,joueur.getMdp());
    }
}
