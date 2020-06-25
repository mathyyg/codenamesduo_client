package swing.vue;

import codenames.CodeNamesClient;
import codenames.exceptions.CnNetworkException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import modele.*;
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
    private JButton regle;
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

        JPanel main = new JPanel(new BorderLayout());
        this.setContentPane(main);

        JPanel recherchePan = new JPanel();
        recherchePan.setPreferredSize(new Dimension(130,250));
        recherchePan.setBorder(BorderFactory
                .createTitledBorder("Recherche une partie"));
        recherchePan.setLayout(new BoxLayout(recherchePan, BoxLayout.Y_AXIS));
        parties = null;
        try {
            parties = new Vector<>(serv.waitingGames());
        } catch (CnNetworkException e) {
            ouvrirMessageErreur("Erreur de connexion au réseau:\n" +
                    "Erreur de récupération des parties en attente","Erreur serveur");
        }
        DefaultListModel model = new DefaultListModel();
        model.addAll(parties);
        partiesAttente = new JList(model);
        JScrollPane scroll = new JScrollPane(partiesAttente, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        recherchePan.add(scroll);
        JPanel butPan = new JPanel(new GridLayout(2,1));
        butPan.add(rejoindre = new JButton("Rejoindre"));
        butPan.add(refresh = new JButton("Refresh"));
        recherchePan.add(butPan);

        main.add(recherchePan, BorderLayout.WEST);
        //Partie droite

        JPanel gauche = new JPanel(new GridLayout(2,1));
        JPanel profil = new JPanel();
        profil.setLayout(new BoxLayout(profil, BoxLayout.Y_AXIS));
        profil.setBorder(BorderFactory
                .createTitledBorder("Profil"));
        profil.add(pseudo = new JLabel("Pseudo : "+joueur.getPseudo()));
        profil.add(mdp =new JLabel("Mdp : "+joueur.getMdp()));
        profil.add(pWin = new JLabel("Parties gagnées : " +joueur.getPWin()));
        profil.add(pLoos = new JLabel("Parties perdues : " +joueur.getPLoos()));
        profil.add(new JLabel("Classement local : " + joueur.getClassement()));
        profil.add(new JLabel("Classement serveur : " + JoueurOnline.getClassementOnline(new JoueurOnline(joueur), serv)));

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
        JPanel creerbutPan = new JPanel(new FlowLayout(FlowLayout.CENTER));
        creer = new JButton("Créer");
        creer.setPreferredSize(new Dimension(150,30));
        creerbutPan.add(creer);
        creerPan.add(creerbutPan);
        regle = new JButton("règle");
        gauche.add(creerPan);
        JPanel pan3 = new JPanel(new GridLayout(1,2));
        pan3.add(profil);
        JPanel panRegleQuit = new JPanel(new GridLayout(2,1));
        panRegleQuit.add(regle);
        panRegleQuit.add(quitter = new JButton("Quitter"));
        pan3.add(panRegleQuit);
        gauche.add(pan3);

        //TODO ajouter un bouton pour visualiser le profil

        main.add(gauche, BorderLayout.CENTER);

        // Listener
        nbTour.addItemListener(new controleurRechercheTours(this));
        creer.addActionListener(new controleurCreerPartie(this, serv, joueur));
        rejoindre.addActionListener(new controleurRejoindrePartie(this, serv, joueur));
        quitter.addActionListener(new controleurQuitterRecherche(this));
        refresh.addActionListener(new controleurRefreshPAttente(this));
        regle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FenetreRegle fnregle = new FenetreRegle("Règle");
                fnregle.setVisible(true);
            }
        });
        //TODO ajouter un listener sur le bouton pour visualiser profil
        //(ajouter une fenetre profil)

        // vue
        this.pack();

        this.setSize(new Dimension(550, 270));

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) /2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public boolean getNbTourSelect() { return nbTour.isSelected();}
    public String getNbTour() { return nbTourText.getText(); }
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
        FenetrePartie next = new FenetrePartie("Bureau des légendes "+"(id: "+partie.getIdPartie()+")", getJoueur(), serv, partie);
        next.setVisible(true);
        this.dispose();
    }

    public void refresh() {
        try {
            parties = new Vector<>(serv.waitingGames());
            partiesAttente.setListData(parties);
        } catch (CnNetworkException e) {
            ouvrirMessageErreur("Erreur de connexion au réseau:\n" +
                    "Erreur de récupération des parties en attente","Erreur serveur");
        }
    }
}