package swing.vue;

import codenames.CodeNamesClient;
import codenames.exceptions.CnNetworkException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class FenetreRecherchePartie extends JFrame {

    public FenetreRecherchePartie(String titre) {
        super(titre);

        CodeNamesClient serv = new CodeNamesClient("http://51.178.49.138:3000/api/v0");

        JPanel main = new JPanel();
        this.setContentPane(main);

        main.setLayout(new GridLayout(1,2));



        /* GAUCHE - REJOINDRE */
        JPanel gauche = new JPanel(new GridLayout(2,1));
        gauche.setBorder(BorderFactory.createRaisedBevelBorder());

        Vector parties= null;
        try {
            parties = new Vector<>(serv.waitingGames());
        } catch (CnNetworkException e) {
            e.printStackTrace();
        }
        JList partiesAttente = new JList(parties);
        JScrollPane scroll = new JScrollPane(partiesAttente, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JTextField pseudoInput = new JTextField("Pseudo");
        pseudoInput.setColumns(10);
        JButton rejoindreButton = new JButton("Rejoindre la partie");
        JButton retourButton = new JButton("Retour");

        JPanel g = new JPanel(new GridLayout(1,2));
        g.setBorder(BorderFactory
                .createTitledBorder("Rejoindre une partie"));
        JPanel gBas = new JPanel(new GridLayout(3,1));
        gBas.add(pseudoInput);
        gBas.add(rejoindreButton);
        gBas.add(retourButton);

        g.add(scroll);
        g.add(gBas);
        gauche.add(g);

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

        JCheckBox personnalise = new JCheckBox("Nombre de tours personnalisé");
        JLabel textTours = new JLabel("Nombre de tours");
        JSlider toursSlider = new JSlider();
        JButton creerButton = new JButton("Créer la partie");

        JPanel d = new JPanel();
        d.setLayout(new BoxLayout(d, BoxLayout.Y_AXIS));
        d.setBorder(BorderFactory
                .createTitledBorder("Créer une partie"));
        d.add(personnalise);
        d.add(textTours);
        d.add(toursSlider);
        d.add(creerButton);

        droite.add(profil);
        droite.add(d);



        /* --- */

        main.add(gauche);
        main.add(droite);








    }
}
