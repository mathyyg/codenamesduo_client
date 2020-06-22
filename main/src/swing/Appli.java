package swing;

import java.awt.*;
import swing.vue.*;

public class Appli {

    public static void main(String[] args) {
        Fenetre fenetre = new Fenetre("Codenames");

        fenetre.setPreferredSize(new Dimension(800, 410));
        fenetre.pack();
        fenetre.setVisible(true);
    }
}
