package swing.vue;


import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame {



    public Fenetre(String titre) {
        super(titre);

        JPanel panelPrincipal = new JPanel();
        this.setContentPane(panelPrincipal);
        JPanel log = new LoginPan();
        JPanel search = new RecherchePan();
        JPanel game = new PartiePan();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
