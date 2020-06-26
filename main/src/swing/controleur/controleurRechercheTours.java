package swing.controleur;

import swing.vue.FenetreMenu;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Contrôleur qui permet de dire si oui ou non on souhaite ajouter un nombre de tour personalisé.
 *
 * @author Paul Vernin, Thomas Peray, Matéo Esteves, Mathys Gagner
 * @version 4.6
 */
public class controleurRechercheTours implements ItemListener {
    private FenetreMenu fn;

    /**
     * Constructeur du contrôleur RechercheTours
     * @param lafn la fenêtre de menu
     */
    public controleurRechercheTours(FenetreMenu lafn) {
        this.fn = lafn;
    }

    /**
     * rend le JTextField d'entrée du nombre de tours éditable si la checkbox est selectionnée; non éditable sinon
     * @param e actionEvent
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED){
            fn.toursEtat(true);
        }
        else if(e.getStateChange() == ItemEvent.DESELECTED){
            fn.toursEtat(false);
        }
    }
}
