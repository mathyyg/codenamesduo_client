package swing.vue;

import codenames.CodeNamesClient;

import javax.swing.*;
import java.awt.*;

import code.*;

public class FenetrePartie extends JFrame {

    private Joueur joueur;
    private CodeNamesClient serv;
    private Partie partie;

    JPanel BureauDesLegendes ;
    private JButton nc1 = new JButton("Button");
    private JButton nc2 = new JButton("Button");
    private JButton nc3 = new JButton("Button");
    private JButton nc4 = new JButton("Button");
    private JButton nc5 = new JButton("Button");
    private JButton nc6 = new JButton("Button");
    private JButton nc7 = new JButton("Button");
    private JButton nc8 = new JButton("Button");
    private JButton nc9 = new JButton("Button");
    private JButton nc10 = new JButton("Button");
    private JButton nc11 = new JButton("Button");
    private JButton nc12 = new JButton("Button");
    private JButton nc13 = new JButton("Button");
    private JButton nc14 = new JButton("Button");
    private JButton nc15 = new JButton("Button");
    private JButton nc16 = new JButton("Button");
    private JButton nc17 = new JButton("Button");
    private JButton nc18 = new JButton("Button");
    private JButton nc19 = new JButton("Button");
    private JButton nc20 = new JButton("Button");
    private JButton nc21 = new JButton("Button");
    private JButton nc22 = new JButton("Button");
    private JButton nc23 = new JButton("Button");
    private JButton nc24 = new JButton("Button");
    private JButton nc25 = new JButton("Button");
    private JButton send = new JButton("Send");
    private JButton keycard = new JButton("keycard");
    private JTextField indice = new JTextField("indice");
    private JComboBox indicechiffre = new JComboBox();
    protected List cardkey = new List();

    public FenetrePartie(String titre, Joueur lejoueur, CodeNamesClient leserv, Partie lapartie) {
        super(titre);
        partie = lapartie;
        joueur = lejoueur;
        serv = leserv;

        JPanel main = new JPanel();
        this.setContentPane(main);

        main.setLayout( new GridLayout(2,1));

        JPanel BureauDesLegendes = new JPanel();
        BureauDesLegendes.setAlignmentX(Component.CENTER_ALIGNMENT);
        BureauDesLegendes.setAlignmentY(Component.CENTER_ALIGNMENT);
        BureauDesLegendes.setBorder(BorderFactory.createLineBorder(Color.black));
        BureauDesLegendes.setBackground(new Color((255),(255),(255)));



        JPanel plateau = new JPanel();
        plateau.setBorder(BorderFactory
                .createTitledBorder("Plateau"));

        plateau.setLayout(new GridLayout(5,5));

        plateau.add(nc1);
        plateau.add(nc2);
        plateau.add(nc3);
        plateau.add(nc4);
        plateau.add(nc5);
        plateau.add(nc6);
        plateau.add(nc7);
        plateau.add(nc8);
        plateau.add(nc9);
        plateau.add(nc10);
        plateau.add(nc11);
        plateau.add(nc12);
        plateau.add(nc13);
        plateau.add(nc14);
        plateau.add(nc15);
        plateau.add(nc16);
        plateau.add(nc17);
        plateau.add(nc18);
        plateau.add(nc19);
        plateau.add(nc20);
        plateau.add(nc21);
        plateau.add(nc22);
        plateau.add(nc23);
        plateau.add(nc24);
        plateau.add(nc25);

        BureauDesLegendes.add(plateau);
        main.add(BureauDesLegendes);

        JPanel tips = new JPanel();
        tips.setBorder(BorderFactory
                .createTitledBorder("Indice"));

        JPanel hint = new JPanel();
        hint.setBorder(BorderFactory
                .createTitledBorder("Indice2"));
        hint.setLayout(new BoxLayout(hint, BoxLayout.Y_AXIS));

        hint.add(indice);
        hint.add(indicechiffre);
        hint.add(keycard);
        hint.add(send);
        tips.add(hint);
        main.add(tips);


// vue
        this.pack();

        this.setSize(new Dimension(800, 800));

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) /2 -400);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2 -400);
        this.setLocation(x, y);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }






}
