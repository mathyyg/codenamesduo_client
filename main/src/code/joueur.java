package code;

import codenames.iPlayer;

public class joueur implements iPlayer {

    private String nom;

    public joueur(String lenom) {
        nom = lenom;
    }

    @Override
    public String login() {
        return nom;
    }


}
