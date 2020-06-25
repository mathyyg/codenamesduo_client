package code;

import codenames.cards.*;
public class Carte {

    private String mot;
    private TYPE_CARTE type;

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
        else
            type = TYPE_CARTE.PAS_TROUVE;
    }

    public Carte(String lemot, TYPE_CARTE t) {
        mot = lemot;
        type = t;
    }

    public void setMot(String s) { mot = s; }
    public void setType(TYPE_CARTE t) { type = t; }
    public String getMot() { return mot; }
    public TYPE_CARTE getType() { return type; }

}
