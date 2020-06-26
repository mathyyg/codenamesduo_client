package swing;

import codenames.CodeNamesClient;
import swing.vue.FenetreLogin;

/**
 * Classe Application qui lance le jeu
 *
 * @author Paul Vernin, Thomas Peray, Matéo Esteves, Mathys Gagner
 * @version 4.6
 */
public class Appli {

    public static void main(String[] args) {

        CodeNamesClient serv = new CodeNamesClient("http://51.178.49.138:3000/api/v0");
        FenetreLogin fenetre = new FenetreLogin("Accueil", serv);
        fenetre.pack();
        fenetre.setVisible(true);


    }
}