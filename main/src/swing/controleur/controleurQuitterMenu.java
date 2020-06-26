package swing.controleur;

import swing.vue.FenetreMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Contrôleur qui permet de quitter l'application
 */
public class controleurQuitterMenu implements ActionListener {
    private FenetreMenu fn;

    /**
     * constructeur du contrôleur QuitterMenu
     * @param lafn la fenêtre du menu
     */
    public controleurQuitterMenu(FenetreMenu lafn) {
        fn = lafn;
    }

    /**
     * ferme la fenêtre associée
     * @param e actioevent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        fn.dispose();
    }
}