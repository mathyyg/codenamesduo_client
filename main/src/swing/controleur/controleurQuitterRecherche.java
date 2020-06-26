/**
 * @author Les Infopotes
 * @version 4
 */
package swing.controleur;

import swing.vue.FenetreMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class controleurQuitterRecherche implements ActionListener {
    private FenetreMenu fn;

    /**
     * constructeur du contrôleur pour quitter la partie
     * @param lafn
     */
    public controleurQuitterRecherche(FenetreMenu lafn) {
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