package swing;

import java.awt.*;
import swing.vue.*;
import code.*;
public class Appli {

    public static void main(String[] args) {


        FenetreRecherchePartie fenetre = new FenetreRecherchePartie("");


        fenetre.setPreferredSize(new Dimension(800, 400));

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - fenetre.getWidth()) /2 -250);
        int y = (int) ((dimension.getHeight() - fenetre.getHeight()) / 2 -150);
        fenetre.setLocation(x, y);

        fenetre.pack();
        fenetre.setVisible(true);
    }
}
