package swing.vue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class PartiePan extends JFrame {

    JPanel BureauDesLegendes ;
    private JButton nc1 ;
    private JButton nc2 ;
    private JButton nc3 ;
    private JButton nc4 ;
    private JButton nc5 ;
    private JButton nc6 ;
    private JButton nc7 ;
    private JButton nc8 ;
    private JButton nc9 ;
    private JButton nc10 ;
    private JButton nc11 ; 
    private JButton nc12 ;
    private JButton nc13 ; 
    private JButton nc14 ;
    private JButton nc15 ;
    private JButton nc16 ;
    private JButton nc17 ; 
    private JButton nc18 ; 
    private JButton nc19 ; 
    private JButton nc20 ;
    private JButton nc21 ;
    private JButton nc22 ;
    private JButton nc23 ;
    private JButton nc24 ;
    private JButton nc25 ;
    private JButton send ;
    private JButton keycard ;
    private JTextField indice ;
    private JComboBox indicechiffre ;
    protected List cardkey ;

    public PartiePan(String titre) {
        super(titre);
        JPanel main = new JPanel();
        this.setContentPane(main);

        main.setLayout( new GridLayout(2,1));

        JPanel BureauDesLegendes = new JPanel();
        BureauDesLegendes.setAlignmentX(Component.CENTER_ALIGNMENT);
        BureauDesLegendes.setAlignmentY(Component.CENTER_ALIGNMENT);
        BureauDesLegendes.setBorder(BorderFactory.createLineBorder(Color.black));
        BureauDesLegendes.setBackground(new Color((255),(255),(255)));



        JPanel plateau = new JPanel();
        plateau.setBorder(BorderFactory.createRaisedBevelBorder());

        plateau.setLayout(new BoxLayout(plateau, BoxLayout.Y_AXIS));
        plateau.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel titreplateau = new JLabel("Plateau");

        plateau.add(titreplateau);
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
        tips.setBorder(BorderFactory.createRaisedBevelBorder());
        JLabel titreIndice = new JLabel("Indice");

        JPanel hint = new JPanel();
        hint.setLayout(new BoxLayout(hint, BoxLayout.Y_AXIS));
        hint.setBorder(new EmptyBorder(10, 10, 10, 10));

        hint.add(titreIndice);
        hint.add(send);
        hint.add(indicechiffre);
        hint.add(indice);
        hint.add(keycard);

        tips.add(hint);
        main.add(tips);



        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }






}
