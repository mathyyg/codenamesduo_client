package swing.vue;


import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame {



    public Fenetre(String titre) {
        super(titre);

        JPanel panelPrincipal = new JPanel();
        this.setContentPane(panelPrincipal);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
