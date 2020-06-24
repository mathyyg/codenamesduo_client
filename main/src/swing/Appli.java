package swing;

import java.awt.*;

import code.Joueur;
import code.Partie;
import codenames.CodeNamesClient;
import swing.vue.*;

public class Appli {

    public static void main(String[] args) {

        CodeNamesClient serv = new CodeNamesClient("http://51.178.49.138:3000/api/v0");
        FenetreLogin fenetre = new FenetreLogin("Login/Register", serv);
        //FenetrePartie fenetre = new FenetrePartie("oui",  new Joueur("a"),  serv, null);
        fenetre.pack();
        fenetre.setVisible(true);
    }
}