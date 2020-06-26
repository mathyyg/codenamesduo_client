/**
 * @author Les Infopotes
 * @version 4
 */
package swing.controleur;

import swing.vue.FenetreRecherchePartie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class controleurQuitterRecherche implements ActionListener {
    private FenetreRecherchePartie fn;

    /**
     * constructeur du contrôleur pour quitter la partie
     * @param lafn
     */
    public controleurQuitterRecherche(FenetreRecherchePartie lafn) {
        fn = lafn;
    }

    /**
     * ferme la fenêtre associée
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        fn.dispose();
    }
}