package swing;

import java.awt.*;

import codenames.CodeNamesClient;
import swing.vue.*;
import code.*;
public class Appli {

    public static void main(String[] args) {

        CodeNamesClient serv = new CodeNamesClient("http://51.178.49.138:3000/api/v0");
        LoginPan fenetre = new LoginPan("Login/Register", serv);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - fenetre.getWidth()) /2 -250);
        int y = (int) ((dimension.getHeight() - fenetre.getHeight()) / 2 -150);
        fenetre.setLocation(x, y);

        fenetre.pack();
        fenetre.setVisible(true);
    }
}
