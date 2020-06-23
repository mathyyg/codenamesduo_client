package swing.vue;

import codenames.CodeNamesClient;
import codenames.exceptions.CnNetworkException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import code.*;
import swing.controleur.controleurCreerPartie;

public class FenetreRecherchePartie extends JFrame {

    private joueur joueur;
    private CodeNamesClient serv;

    private JCheckBox nbTour;
    private JTextField nbTourText;
    private JButton creer;

    JLabel pseudo;
    JLabel mdp;
    JLabel pWin;
    JLabel pLoos;
    public FenetreRecherchePartie(String titre, joueur j, CodeNamesClient leserv) {
        super(titre);
        joueur = j;
        serv = leserv;

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
        profil.add(pseudo = new JLabel(j.getPseudo()));
        profil.add(mdp =new JLabel(j.getMdp()));
        profil.add(pWin = new JLabel("Partie gagné : " +j.getPWin()));
        profil.add(pLoos = new JLabel("Partie perdue : " +j.getPLoos()));

        JPanel creerPan = new JPanel();
        creerPan.setLayout(new BoxLayout(creerPan, BoxLayout.Y_AXIS));
        creerPan.setBorder(BorderFactory
                .createTitledBorder("Créé une partie"));
        creerPan.add(new JLabel("Ajouter un nombre de tour personnalisé :"));
        JPanel tourPan = new JPanel();
        tourPan.add(nbTour = new JCheckBox("NB : "));
        nbTourText = new JTextField();
        nbTourText.setColumns(10);
        nbTourText.setEnabled(false);
        tourPan.add(nbTourText);
        creerPan.add(tourPan);
        creerPan.add(creer = new JButton("Créer"));

        gauche.add(profil);
        gauche.add(creerPan);

        main.add(recherchePan);
        main.add(gauche);

        // Listener
        creer.addActionListener(new controleurCreerPartie(this, serv, joueur));

        // vue
        this.pack();

        this.setSize(new Dimension(700, 400));

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) /2 -350);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2 -200);
        this.setLocation(x, y);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public boolean getNbTourSelect() { return nbTour.isSelected();}
    public int getNbTour() { return Integer.parseInt(nbTourText.getText()); }
    public joueur getJoueur() { return joueur;}
}
