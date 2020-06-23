package code;

import codenames.CodeNamesClient;
import codenames.states.*;
import codenames.cards.*;
import codenames.exceptions.*;

import java.util.List;

public class Partie {

    private CodeNamesClient serveur;
    private int idPartie;
    private int nbTour;
    private State etat;
    private Joueur j;
    private List<String> plateau;
    private List<CARD_ROLE> keyCard;


    public Partie(CodeNamesClient leserv, int lenbTour, Joueur lej, int lidPartie) {
        serveur = leserv;
        nbTour = lenbTour;
        j = lej;
        idPartie = lidPartie;
        try {
            etat = serveur.consultGame(idPartie);
            plateau = etat.words();
            keyCard = serveur.keyCards(idPartie,j,j.getMdp());
        } catch (CnNetworkException e) {
            e.printStackTrace();
        } catch (CnBadIdException e) {
            e.printStackTrace();
        } catch (CnBadLoginException e) {
            e.printStackTrace();
        } catch (CnBadPwdException e) {
            e.printStackTrace();
        }
    }


    public List<String> getPlateau() {
        return plateau;
    }

    public List<CARD_ROLE> getKeyCard() {
        return keyCard;
    }

    public int getIdPartie() {
        return idPartie;
    }

    public int getNbTour() {
        return nbTour;
    }

    public State getEtat() {
        return etat;
    }

    public Joueur getJ() {
        return j;
    }
}
