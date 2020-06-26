package modele;

import codenames.CodeNamesClient;
import codenames.states.*;
import codenames.cards.*;
import codenames.exceptions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Partie qui permet d'enregistrer les informations lors de l'initialisation d'une partie.
 *
 * @author Paul Vernin, Thomas Peray, Matéo Esteves, Mathys Gagner
 * @version 4.6
 */
public class Partie {

    private int idPartie;
    private int nbTour;
    private State etat;
    private Joueur j;
    private List<String> words;
    private List<CARD_ROLE> keyCard;
    private List<Carte> plateau;


    /**
     * Constructeur de classe Partie
     * Prend 4 paramètres
     * @param leserv correspond au serveur
     * @param lenbTour int correspond nombre de tour 
     * @param lej correspond au joueur qui à lancé la partiz
     * @param lidPartie int correspond au numéro de la partie
     */
    public Partie(CodeNamesClient leserv, int lenbTour, Joueur lej, int lidPartie) {
        nbTour = lenbTour;
        j = lej;
        idPartie = lidPartie;
        try {
            etat = leserv.consultGame(idPartie);
            words = etat.words();
            keyCard = leserv.keyCards(idPartie,j,j.getMdp());

            plateau = new ArrayList<>();
            for (String word : words) {
                plateau.add(new Carte(word, TYPE_CARTE.PAS_TROUVE));
            }

        } catch (CnNetworkException | CnBadPwdException | CnBadLoginException | CnBadIdException e) {
            System.err.println("Erreur de création de l'intance de classe partie");
        }
    }


    /**
     * Méthode getWords
     * Elle permet de recupérer les mots du serveur et de les afficher sur l'interface
     * @return une liste de string
     */
    public List<String> getWords() {
        return words;
    }

    /**
     * Méthode getKeyCard
     * Elle permet de récupérer du serveur la keyCard qui nous indique les réponses et de l'afficher sur l'interface
     * @return une liste de type de cartes
     */
    public List<CARD_ROLE> getKeyCard() {
        return keyCard;
    }

    /**
     * Méthode getIdPartie
     * retourne l'identifiant de la partie
     * @return un int
     */
    public int getIdPartie() {
        return idPartie;
    }

    /**
     * Méthode getNbTour
     * Elle permet de récupérer le nombre de tour définit par le créateur de la partie
     * @return int
     */
    public int getNbTour() {
        return nbTour;
    }

    /**
     * Méthode getEtat
     * Elle permet de récupérer l'état de la partie via le serveur
     * @return state (état)
     */
    public State getEtat() {
        return etat;
    }

    /**
     * Méthode setEtat
     * Modifie l'état de la classe partie.
     * @param e état à transférer à l'état de partie.
     */
    public void setEtat(State e) { etat = e; }

    /**
     * Méthode getJ
     * Elle permet de récupérer les joueurs de la partie
     * @return un joueur
     */
    public Joueur getJ() {
        return j;
    }

    /**
     * Méthode getPlateau 
     * Elle permet de récupérer le plateau de jeu (les 25 mots)
     * @return une liste de Carte
     */
    public List<Carte> getPlateau() { return plateau;}


    /**
     * Méthode updatePlateau, met à jour le type des cartes qui ont été trouvé par les joueurs
     * @param CardList , liste des cartes dont on a découvert le TYPE.
     */
    public void updatePlateau(List<Carte> CardList) {
        for (Carte cPlat : plateau)
            for (Carte cCardList : CardList)
                if (cPlat.getMot().equals(cCardList.getMot()))
                    cPlat.setType(cCardList.getType());
    }
}
