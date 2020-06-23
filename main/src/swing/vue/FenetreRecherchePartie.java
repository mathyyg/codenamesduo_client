package swing.vue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class FenetreRecherchePartie extends JFrame {

    public FenetreRecherchePartie(String titre) {
        super(titre);

        JPanel main = new JPanel();
        this.setContentPane(main);

        main.setLayout(new GridLayout(1,2));

        JPanel rejoindrePan = new JPanel();
        rejoindrePan.setBorder(BorderFactory.createLineBorder(Color.black));
        rejoindrePan.setBackground(new Color((255),(255),(255)));

        JPanel creerPan = new JPanel();
        creerPan.setBorder(BorderFactory.createLineBorder(Color.black));
        creerPan.setBackground(new Color((255),(255),(255)));



        /* GAUCHE - REJOINDRE */
        JPanel gauche = new JPanel(new GridLayout(2,1));
        gauche.setBorder(BorderFactory.createRaisedBevelBorder());

        Vector parties= new Vector<String>();
        parties.add("Aucune partie trouvé");
        JList partiesAttente = new JList();
        partiesAttente.setListData(parties);
        JTextField pseudoInput = new JTextField("Pseudo");
        JButton rejoindreButton = new JButton("Rejoindre la partie");
        JButton retourButton = new JButton("Retour");

        JPanel g = new JPanel();
        g.setLayout(new BoxLayout(g, BoxLayout.Y_AXIS));
        g.setBorder(BorderFactory
                .createTitledBorder("Rejoindre une partie"));
        g.add(partiesAttente);
        g.add(pseudoInput);
        g.add(rejoindreButton);
        g.add(retourButton);

        gauche.add(g);

        rejoindrePan.add(gauche);
        /* --- */

        /* DROITE - CREER */
        JPanel droite = new JPanel();
        droite.setLayout(new BoxLayout(droite, BoxLayout.Y_AXIS));
        droite.setBorder(BorderFactory.createRaisedBevelBorder());

        JPanel profil = new JPanel();
        profil.setLayout(new BoxLayout(profil, BoxLayout.Y_AXIS));
        profil.setBorder(BorderFactory
                .createTitledBorder("Profil du joueur"));
        JLabel pseudo = new JLabel("pseudo");
        JLabel mdp = new JLabel("mdp");
        JLabel nbWin = new JLabel("Nombre de partie gagné :");
        JLabel nbloos = new JLabel("Nombre de partie perdue :");
        profil.add(pseudo);
        profil.add(mdp);
        profil.add(mdp);
        profil.add(nbWin);
        profil.add(nbloos);

        JLabel titreDroite = new JLabel("Créer une partie");
        JCheckBox personnalise = new JCheckBox("Nombre de tours personnalisé");
        JLabel textTours = new JLabel("Nombre de tours");
        JSlider toursSlider = new JSlider();
        JButton creerButton = new JButton("Créer la partie");

        JPanel d = new JPanel();
        d.setLayout(new BoxLayout(d, BoxLayout.Y_AXIS));

        d.add(titreDroite);
        d.add(personnalise);
        d.add(textTours);
        d.add(toursSlider);
        d.add(creerButton);

        droite.add(profil);
        droite.add(d);

        creerPan.add(droite);


        /* --- */

        main.add(rejoindrePan);
        main.add(creerPan);








    }
}
