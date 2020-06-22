package swing.vue;

import javax.swing.*;
import java.awt.*;

public class RecherchePan extends JFrame {
    public RecherchePan(String titre) {
        super(titre);
        JPanel panelPrincipal = new JPanel();
        this.setContentPane(panelPrincipal);

        JLabel suivante = new JLabel("Fini");

        panelPrincipal.add(suivante);

        panelPrincipal.setPreferredSize(new Dimension(800, 410));

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
}
