package swing;

import java.awt.*;
import swing.vue.*;
import code.*;
public class Appli {

    public static void main(String[] args) {


        Fenetre fenetre = new Fenetre("Codenames");


        fenetre.setPreferredSize(new Dimension(500, 300));

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - fenetre.getWidth()) /2 -250);
        int y = (int) ((dimension.getHeight() - fenetre.getHeight()) / 2 -150);
        fenetre.setLocation(x, y);

        fenetre.pack();
        fenetre.setVisible(true);
    }
}
