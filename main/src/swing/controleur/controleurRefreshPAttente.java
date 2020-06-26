package swing.controleur;

import swing.vue.FenetreMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Contrôleur qui permet de rafraîchir la liste des parties en attente d'un joueur
 *
 * @author Paul Vernin, Thomas Peray, Matéo Esteves, Mathys Gagner
 * @version 4.6
 */
public class controleurRefreshPAttente implements ActionListener {
    private FenetreMenu fn;

    /**
     * Constructeur du contrôleur RefreshPAttente
     * @param lafn la fenêtre
     */
    public controleurRefreshPAttente(FenetreMenu lafn) {
        fn = lafn;
    }

    /**
     * Appelle refresh() de la classe FenetreRecherchePartie
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        fn.refresh();
    }
}
