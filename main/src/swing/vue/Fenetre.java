package swing.vue;

import org.apache.commons.logging.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame {
    public Fenetre(String titre) {
        super(titre);

        JPanel panelPrincipal = new JPanel();
        this.setContentPane(panelPrincipal);

        JButton suivante = new JButton("Fenêtre suivante");

        panelPrincipal.add(suivante);

        suivante.addActionListener(actionEvent -> {
            RecherchePan deuxieme = new RecherchePan("suivante");
            deuxieme.setVisible(true);
            dispose();
        });

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
