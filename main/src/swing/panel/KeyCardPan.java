package swing.panel;

import codenames.cards.CARD_ROLE;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe KeyCard qui extend JPanel, elle représente la KeyCard dans la fenêtre partie
 *
 * @author Paul Vernin, Thomas Peray, Matéo Esteves, Mathys Gagner
 * @version 4.6
 */
public class KeyCardPan extends JPanel {
    private List<JButton> listBut;

    // boutons de la key card
    private JButton nc1 = new JButton();
    private JButton nc2 = new JButton();
    private JButton nc3 = new JButton();
    private JButton nc4 = new JButton();
    private JButton nc5 = new JButton();
    private JButton nc6 = new JButton();
    private JButton nc7 = new JButton();
    private JButton nc8 = new JButton();
    private JButton nc9 = new JButton();
    private JButton nc10 = new JButton();
    private JButton nc11 = new JButton();
    private JButton nc12 = new JButton();
    private JButton nc13 = new JButton();
    private JButton nc14 = new JButton();
    private JButton nc15 = new JButton();
    private JButton nc16 = new JButton();
    private JButton nc17 = new JButton();
    private JButton nc18 = new JButton();
    private JButton nc19 = new JButton();
    private JButton nc20 = new JButton();
    private JButton nc21 = new JButton();
    private JButton nc22 = new JButton();
    private JButton nc23 = new JButton();
    private JButton nc24 = new JButton();
    private JButton nc25 = new JButton();

    /**
     * Constructeur de KeyCardPan
     */
    // on ajoute les 25 boutons à la fenêtre et dans une liste
    public KeyCardPan(){
        listBut = new ArrayList<>();
        this.setLayout(new GridLayout(5,5));
        this.add(nc1); listBut.add(nc1);
        this.add(nc2); listBut.add(nc2);
        this.add(nc3); listBut.add(nc3);
        this.add(nc4); listBut.add(nc4);
        this.add(nc5); listBut.add(nc5);
        this.add(nc6); listBut.add(nc6);
        this.add(nc7); listBut.add(nc7);
        this.add(nc8); listBut.add(nc8);
        this.add(nc9); listBut.add(nc9);
        this.add(nc10); listBut.add(nc10);
        this.add(nc11); listBut.add(nc11);
        this.add(nc12); listBut.add(nc12);
        this.add(nc13); listBut.add(nc13);
        this.add(nc14); listBut.add(nc14);
        this.add(nc15); listBut.add(nc15);
        this.add(nc16); listBut.add(nc16);
        this.add(nc17); listBut.add(nc17);
        this.add(nc18); listBut.add(nc18);
        this.add(nc19); listBut.add(nc19);
        this.add(nc20); listBut.add(nc20);
        this.add(nc21); listBut.add(nc21);
        this.add(nc22); listBut.add(nc22);
        this.add(nc23); listBut.add(nc23);
        this.add(nc24); listBut.add(nc24);
        this.add(nc25); listBut.add(nc25);
    }

    /**
     * méthode qui attribue les couleurs aux boutons selon leur rôle
     * @param keyCards liste de cartes générée par le serveur
     */
    public void init(List<CARD_ROLE> keyCards) {
        Iterator<CARD_ROLE> itKeyCard = keyCards.iterator();
        Iterator<JButton> itButton = listBut.iterator();
        JButton butCourant;
        CARD_ROLE cardCourant;
        while (itKeyCard.hasNext() && itButton.hasNext()){
            cardCourant = itKeyCard.next();
            butCourant = itButton.next();

            if (cardCourant.equals(CARD_ROLE.CODE))
                butCourant.setBackground(Color.GREEN);
            if (cardCourant.equals(CARD_ROLE.KILLER))
                butCourant.setBackground(Color.BLACK);
            if (cardCourant.equals(CARD_ROLE.NEUTRAL))
                butCourant.setBackground(Color.GRAY);
            butCourant.setOpaque(true);
            butCourant.setEnabled(false);
        }
    }
}
