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

    private JCheckBox nbTour;
    private JTextField nbTourText;
    private JButton creer;

    public FenetreRecherchePartie(String titre) {
        super(titre);

        CodeNamesClient serv = new CodeNamesClient("http://51.178.49.138:3000/api/v0");

        JPanel main = new JPanel();
        this.setContentPane(main);

        JPanel recherchePan = new JPanel();
        recherchePan.setBorder(BorderFactory
                .createTitledBorder("Recherche une partie"));
        recherchePan.setLayout(new BoxLayout(recherchePan, BoxLayout.Y_AXIS));
        Vector parties= null;
        try {
            parties = new Vector<>(serv.waitingGames());
        } catch (CnNetworkException e) {
            System.out.println("erreur de chargement de la liste des parties en attente.");
            e.printStackTrace();
        }
        JList partiesAttente = new JList(parties);
        JScrollPane scroll = new JScrollPane(partiesAttente, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(0,250));
        recherchePan.add(scroll);
        recherchePan.add(new JButton("Rejoindre"));
        recherchePan.add(new JButton("Quitter"));

        //Partie droite
        JPanel gauche = new JPanel(new GridLayout(2,1));
        JPanel profil = new JPanel();
        profil.setLayout(new BoxLayout(profil, BoxLayout.Y_AXIS));
        profil.setBorder(BorderFactory
                .createTitledBorder("Profil"));
        profil.add(new JLabel("Pseudo"));
        profil.add(new JLabel("mdp"));
        profil.add(new JLabel("PartieWin"));
        profil.add(new JLabel("PartieLoos"));

        JPanel creerPan = new JPanel();
        creerPan.setLayout(new BoxLayout(creerPan, BoxLayout.Y_AXIS));
        creerPan.setBorder(BorderFactory
                .createTitledBorder("Créé une partie"));
        creerPan.add(new JLabel("Ajouter un nombre de tour personnalisé :"));
        JPanel tourPan = new JPanel();
        tourPan.add(nbTour = new JCheckBox());
        nbTourText = new JTextField();
        nbTourText.setColumns(10);
        tourPan.add(nbTourText);
        creerPan.add(tourPan);
        creerPan.add(creer = new JButton("Créer"));

        gauche.add(profil);
        gauche.add(creerPan);

        main.add(recherchePan);
        main.add(gauche);

        this.pack();

        this.setSize(new Dimension(700, 400));

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) /2 -350);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2 -200);
        this.setLocation(x, y);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
