/**
 * @author Les Infopotes
 * @version 4
 */
package swing;

import codenames.CodeNamesClient;
import swing.vue.FenetreLogin;

public class Appli {

    public static void main(String[] args) {

        CodeNamesClient serv = new CodeNamesClient("http://51.178.49.138:3000/api/v0");
        FenetreLogin fenetre = new FenetreLogin("Accueil", serv);
        fenetre.pack();
        fenetre.setVisible(true);


    }
}