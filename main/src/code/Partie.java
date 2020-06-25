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
    private List<Card> plateau;


    public Partie(CodeNamesClient leserv, int lenbTour, Joueur lej, int lidPartie) {
        serveur = leserv;
        nbTour = lenbTour;
        j = lej;
        idPartie = lidPartie;
        try {
            etat = serveur.consultGame(idPartie);
            System.out.println(etat.state());
            words = etat.words();
            System.out.println(words);
            keyCard = serveur.keyCards(idPartie,j,j.getMdp());

            plateau = new ArrayList<>();
            for (String word : words) {
                plateau.add(new Card(word, null));
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
    public List<Card> getPlateau() { return plateau;}

    public void plateauMAJ(List<Card> CardList) {
        for (Card cPlat : plateau)
            for (Card cCardList : CardList)
                if (cPlat.word().equals(cCardList.word())) {
                    cPlat = new Card(cPlat.word(), cCardList.cardRole());
                }
    }
}
