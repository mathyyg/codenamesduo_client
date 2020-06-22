package code;

import codenames.iPlayer;

public class joueur implements iPlayer {

    private String nom;
    private String mdp;

    public joueur(String lenom) {
        nom = lenom;
        mdp = null;
    }

    @Override
    public String login() {
        return nom;
    }

    public void setMdp(String s) {
        mdp = s;
    }
    public String getMdp() {
        return mdp;
    }
}
