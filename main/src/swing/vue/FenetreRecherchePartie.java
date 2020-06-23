package swing.vue;

import codenames.CodeNamesClient;
import codenames.exceptions.CnNetworkException;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;
import code.*;
import swing.controleur.*;

public class FenetreRecherchePartie extends JFrame {

    private Joueur joueur;
    private CodeNamesClient serv;

    private JCheckBox nbTour;
    private JTextField nbTourText;
    private JButton creer;
    private JButton rejoindre;
    private JButton refresh;
    private JButton quitter;
    private JList partiesAttente;
    private Vector parties;

    JLabel pseudo;
    JLabel mdp;
    JLabel pWin;
    JLabel pLoos;

    public FenetreRecherchePartie(String titre, Joueur j, CodeNamesClient leserv) {
        super(titre);
        joueur = j;
        serv = leserv;

        JPanel main = new JPanel();
        this.setContentPane(main);

        JPanel recherchePan = new JPanel();
        recherchePan.setPreferredSize(new Dimension(170,350));
        recherchePan.setBorder(BorderFactory
                .createTitledBorder("Recherche une partie"));
        recherchePan.setLayout(new BoxLayout(recherchePan, BoxLayout.Y_AXIS));
        parties = null;
        try {
            parties = new Vector<>(serv.waitingGames());
        } catch (CnNetworkException e) {
            System.out.println("erreur de chargement de la liste des parties en attente.");
            e.printStackTrace();
        }
        DefaultListModel model = new DefaultListModel();
        model.addAll(parties);
        partiesAttente = new JList(model);
        JScrollPane scroll = new JScrollPane(partiesAttente, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        recherchePan.add(scroll);
        recherchePan.add(rejoindre = new JButton("Rejoindre"));
        recherchePan.add(quitter = new JButton("Quitter"));
        recherchePan.add(refresh = new JButton("\uD83D\uDDD8"));


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
        nbTourText.setEditable(false);
        tourPan.add(nbTourText);
        creerPan.add(tourPan);
        creerPan.add(creer = new JButton("Créer"));

        gauche.add(profil);
        gauche.add(creerPan);

        main.add(recherchePan);
        main.add(gauche);

        // Listener
        nbTour.addItemListener(new controleurRechercheTours(this));
        creer.addActionListener(new controleurCreerPartie(this, serv, joueur));
        rejoindre.addActionListener(new controleurRejoindrePartie(this, serv, joueur));
        quitter.addActionListener(new controleurQuitter(this));
        refresh.addActionListener(new controleurRefreshPAttente(this));


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
    public Joueur getJoueur() { return joueur;}
    public void toursEtat(boolean b) { nbTourText.setEditable(b); }
    public int getIDPartieSelected() { return (int) partiesAttente.getSelectedValue();}


    public void ouvrirMessageErreur(String msg, String titre) {
        JOptionPane.showMessageDialog(this,
                msg,
                titre,
                JOptionPane.ERROR_MESSAGE);
    }

    public void second(Partie partie) {
        FenetrePartie next = new FenetrePartie("Bureau des légendes", getJoueur(), serv, partie);
        next.setVisible(true);
        this.dispose();
    }

    public void refresh() {
        try {
            parties = new Vector<>(serv.waitingGames());
            partiesAttente.setListData(parties);
        } catch (CnNetworkException e) {
            System.out.println("erreur de chargement de la liste des parties en attente.");
            e.printStackTrace();
        }
    }

}
