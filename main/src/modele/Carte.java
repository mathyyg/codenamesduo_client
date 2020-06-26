package modele;

import codenames.cards.*;

/**
 * La classe Carte est un substitue de la classe Card proposé du serveur.
 *
 *  @author Paul Vernin, Thomas Peray, Matéo Esteves, Mathys Gagner
 *  @version 4.6
 */
public class Carte {

    private String mot;
    private TYPE_CARTE type;
    /**
     * Constructeur Carte
     * Cette méthode permet de vérifier et attribuer le type à sa carte (Assasin, innocent, code)
     * @param c qui est une carte
     */
    public Carte(Card c){
        mot = c.word();
        if (c.cardRole() == null)
            type = TYPE_CARTE.PAS_TROUVE;
        else if (c.cardRole().equals(CARD_ROLE.NEUTRAL))
            type = TYPE_CARTE.NEUTRAL;
        else if (c.cardRole().equals(CARD_ROLE.CODE))
            type = TYPE_CARTE.CODE;
        else if (c.cardRole().equals(CARD_ROLE.KILLER))
            type = TYPE_CARTE.ASSASSIN;
        else if (c.cardRole().equals(CARD_ROLE.NO_MORE_CODE))
            type = TYPE_CARTE.NO_MORE_CARD;
        else
            type = TYPE_CARTE.PAS_TROUVE;
    }


    /**
     * Constrcuteur de la classe Carte
     * Le constructeur permet de définir le mot de la carte ainsi que son type (Assasin, innocent, code)
     * @param lemot String qui définit le mot sur la carte
     * @param t Type qui permet de définir le type de la carte
     */
    public Carte(String lemot, TYPE_CARTE t) {
        mot = lemot;
        type = t;
    }

    /**
     * Méthode setMot
     * Cette méthode permet de définir le mot sur la carte 
     * @param s String qui le mot de la carte
     */
    public void setMot(String s) { mot = s; }

    /**
     * Méthode setType
     * Elle permet de définir le type d'une carte (Assasin, innocent, code)
     * @param t TYPE_CARTE qui est le type
     */
    public void setType(TYPE_CARTE t) { type = t; }

    /**
     * Méthode getMot
     * Elle permet de renvoyer le mot d'une carte
     * @return un string
     */
    public String getMot() { return mot; }

    /**
     * Méthode getType
     * Cette méthode permet de renvoyer le type d'une carte (Assasin, innocent, code)
     * @return un type de carte (Assasin, innocent, code)
     */
    public TYPE_CARTE getType() { return type; }

    @Override
    public String toString() { return mot + " " + type;}
}
