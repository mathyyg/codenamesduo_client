/**
 * @author Les Infopotes
 * @version 4
 */
package swing.controleur;

import swing.vue.FenetreRecherchePartie;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class controleurRechercheTours implements ItemListener {
    private FenetreRecherchePartie fn;

    /**
     * Constructeur du contrôleur permettant l'entrée d'un nombre de tours ou non
     * @param lafn
     */
    public controleurRechercheTours(FenetreRecherchePartie lafn) {
        this.fn = lafn;
    }

    /**
     * rend le JTextField d'entrée du nombre de tours éditable si la checkbox est selectionnée; non éditable sinon
     * @param e
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
