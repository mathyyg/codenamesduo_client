package swing.controleur;

import swing.panel.PlateauPan;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class controleurAjoutLAnswer implements ItemListener {
    PlateauPan p;
    JToggleButton b;

    public controleurAjoutLAnswer(PlateauPan lapan, JToggleButton leb){
        p = lapan;
        b = leb;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange()==ItemEvent.SELECTED){
            p.addListMot(b.getText());
        } else {
            p.removeListMot(b.getText());
        }
    }
}
