package code;

import codenames.CodeNamesClient;
import codenames.states.*;
import codenames.cards.*;
import codenames.exceptions.*;

import java.util.ArrayList;
import java.util.List;

public class Partie {

    private CodeNamesClient serveur;
    private int idPartie;
    private int nbTour;
    private State etat;
    private Joueur j;
    private List<String> words;
    private List<CARD_ROLE> keyCard;
    private List<Carte> plateau;


    public Partie(CodeNamesClient leserv, int lenbTour, Joueur lej, int lidPartie) {
        serveur = leserv;
        nbTour = lenbTour;
        j = lej;
        idPartie = lidPartie;
        try {
            etat = serveur.consultGame(idPartie);
            words = etat.words();
            keyCard = serveur.keyCards(idPartie,j,j.getMdp());

            plateau = new ArrayList<>();
            for (String word : words) {
                plateau.add(new Carte(word, TYPE_CARTE.PAS_TROUVE));
            }

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

    public List<String> getWords() {
        return words;
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
    public void setEtat(State e) { etat = e; }
    public Joueur getJ() {
        return j;
    }
    public List<Carte> getPlateau() { return plateau;}


    public void plateauMAJ(List<Carte> CardList) {
        for (Carte cPlat : plateau)
            for (Carte cCardList : CardList)
                if (cPlat.getMot().equals(cCardList.getMot()))
                    cPlat.setType(cCardList.getType());


    }
}
