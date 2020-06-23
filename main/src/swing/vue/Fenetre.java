package swing.vue;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame {



    public Fenetre(String titre) {
        super(titre);

//        JPanel panelPrincipal = new LoginPan();
  //      this.setContentPane(panelPrincipal);
        //JPanel log = new LoginPan();
        //JPanel search = new RecherchePan();
        //JPanel game = new PartiePan();
        /*
        JButton suivante = new JButton("Fenêtre suivante");

        panelPrincipal.add(suivante);

        suivante.addActionListener(actionEvent -> {
            RecherchePan deuxieme = new RecherchePan("suivante");
            deuxieme.setVisible(true);
            dispose();
        });
        */
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}
