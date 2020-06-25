package swing.panel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modele.*;

public class PlateauPan extends JPanel {
    private List<JToggleButton> listBut;


    private JToggleButton nc1 = new JToggleButton("Button");
    private JToggleButton nc2 = new JToggleButton("Button");
    private JToggleButton nc3 = new JToggleButton("Button");
    private JToggleButton nc4 = new JToggleButton("Button");
    private JToggleButton nc5 = new JToggleButton("Button");
    private JToggleButton nc6 = new JToggleButton("Button");
    private JToggleButton nc7 = new JToggleButton("Button");
    private JToggleButton nc8 = new JToggleButton("Button");
    private JToggleButton nc9 = new JToggleButton("Button");
    private JToggleButton nc10 = new JToggleButton("Button");
    private JToggleButton nc11 = new JToggleButton("Button");
    private JToggleButton nc12 = new JToggleButton("Button");
    private JToggleButton nc13 = new JToggleButton("Button");
    private JToggleButton nc14 = new JToggleButton("Button");
    private JToggleButton nc15 = new JToggleButton("Button");
    private JToggleButton nc16 = new JToggleButton("Button");
    private JToggleButton nc17 = new JToggleButton("Button");
    private JToggleButton nc18 = new JToggleButton("Button");
    private JToggleButton nc19 = new JToggleButton("Button");
    private JToggleButton nc20 = new JToggleButton("Button");
    private JToggleButton nc21 = new JToggleButton("Button");
    private JToggleButton nc22 = new JToggleButton("Button");
    private JToggleButton nc23 = new JToggleButton("Button");
    private JToggleButton nc24 = new JToggleButton("Button");
    private JToggleButton nc25 = new JToggleButton("Button");

    public PlateauPan() {
        this.setLayout(new GridLayout(5,5));

        listBut = new ArrayList<>();
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

    public void init(List<String> words) {
        Iterator<String> itWords = words.iterator();
        Iterator<JToggleButton> itButton = listBut.iterator();
        JToggleButton butCourant;
        String wordCourant;
        while (itWords.hasNext() && itButton.hasNext()){
            wordCourant = itWords.next();
            butCourant = itButton.next();
            butCourant.setText(wordCourant);
        }
    }
    public void updatePlateau(List<Carte> cList){
        for (JToggleButton b : listBut){
            for (Carte c : cList){
                if (b.getText().equals(c.getMot())){
                    if (c.getType().equals(TYPE_CARTE.CODE))
                        b.setBackground(Color.GREEN);
                    if (c.getType().equals(TYPE_CARTE.NEUTRAL))
                        b.setBackground(Color.GRAY);
                    b.setOpaque(true);
                    b.setSelected(false);
                    b.setEnabled(false);
                }
            }
        }
    }

    public List<String> getMotSelected() {
        List<String> mots = new ArrayList<>();
        for (JToggleButton b : listBut){
            if (b.isSelected() &&
                    !b.getBackground().equals(Color.GRAY) &&
                    !b.getBackground().equals(Color.GREEN)
            )
                mots.add(b.getText());
        }
        return mots;
    }

}
