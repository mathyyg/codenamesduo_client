/**
 * @author Les Infopotes
 * @version 4
 */
package swing.controleur;

import swing.vue.FenetreRecherchePartie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class controleurRefreshPAttente implements ActionListener {
    private FenetreRecherchePartie fn;

    /**
     * Constructeur du contrôleur permettant le rafraîchissement des parties en attente
     * @param lafn
     */
    public controleurRefreshPAttente(FenetreRecherchePartie lafn) {
        fn = lafn;
    }

    /**
     * Appelle refresh() de la classe FenetreRecherchePartie
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        fn.refresh();
    }
}
