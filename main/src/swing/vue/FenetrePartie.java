package swing.vue;

import codenames.CodeNamesClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import java.util.Vector;

import code.*;
import codenames.cards.CARD_ROLE;
import codenames.states.STATE_STEP;
import swing.timerControleur.AttenteDeJ2Listener;

public class FenetrePartie extends JFrame {

    private Joueur joueur;
    private CodeNamesClient serv;
    private Partie partie;
    private Timer timerAttenteJ2;
    private List ListIndice;


    private JLabel pseudo;
    private JLabel mdp;
    private JLabel pWin;
    private JLabel pLoos;
    private JPanel BureauDesLegendes ;
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

    private JButton keycardBut;
    private JList<CARD_ROLE> keycardsList;
    private JList<Indice> JListIndice;

    private JButton sendIndice;
    private JTextField indiceInput;
    private JComboBox<Integer> indicechiffre;
    private JTextField reponseInput;
    private JButton sendReponse;


    public FenetrePartie(String titre, Joueur lejoueur, CodeNamesClient leserv, Partie lapartie) {
        super(titre);
        partie = lapartie;
        joueur = lejoueur;
        serv = leserv;

        JPanel main = new JPanel();
        this.setContentPane(main);

        main.setLayout( new GridLayout(2,1));

        JPanel BureauDesLegendes = new JPanel();
        BureauDesLegendes.setLayout(new GridLayout(1,1));
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

        // Partie bas
        JPanel bas = new JPanel(new BorderLayout());

        JPanel centerPan = new JPanel();
        centerPan.setLayout(new GridLayout(2,1));

        JPanel hautCenterPan = new JPanel();

        JPanel profil = new JPanel();
        profil.setLayout(new BoxLayout(profil, BoxLayout.Y_AXIS));
        profil.setBorder(BorderFactory
                .createTitledBorder("Profil"));
        profil.add(pseudo = new JLabel("Pseudo : " + joueur.getPseudo()));
        profil.add(mdp = new JLabel("Mdp : " + joueur.getMdp()));
        profil.add(pWin = new JLabel("Partie gagné : " +joueur.getPWin()));
        profil.add(pLoos = new JLabel("Partie perdue : " +joueur.getPLoos()));

        hautCenterPan.add(profil);

        JTabbedPane indiceRepTabPan = new JTabbedPane();

        JPanel indicePan = new JPanel();
        JPanel pan1 = new JPanel();
        pan1.setLayout(new BoxLayout(pan1, BoxLayout.Y_AXIS));
        pan1.add(new JLabel("Entre l'indice ci-dessous : "));
        pan1.add(new JLabel("le nombre de mot correspondant à l'indice : "));
        JPanel pan2 = new JPanel();
        pan2.setLayout(new BoxLayout(pan2, BoxLayout.Y_AXIS));
        pan2.add(indiceInput = new JTextField());
        indiceInput.setColumns(10);
        Integer[] chiffres = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        pan2.add(indicechiffre = new JComboBox<>(chiffres));
        indicePan.add(pan1);
        indicePan.add(pan2);
        indicePan.add(sendIndice = new JButton("Envoyer l'indice"));

        indiceRepTabPan.addTab("Indice", indicePan);

        JPanel reponsePan = new JPanel();
        reponsePan.setLayout(new BoxLayout(reponsePan, BoxLayout.Y_AXIS));

        reponsePan.add(new JLabel("Listez vos réponses dans le cadre en les séparant avec une ',' : "));
        reponsePan.add(reponseInput = new JTextField());
        reponseInput.setColumns(10);
        reponsePan.add(sendReponse = new JButton("Envoyer la réponse"));


        indiceRepTabPan.addTab("Réponse", reponsePan);

        hautCenterPan.add(indiceRepTabPan);

        centerPan.add(hautCenterPan);

        JPanel keyPan = new JPanel(new FlowLayout(FlowLayout.CENTER));
        keyPan.add(keycardBut = new JButton("KeyCards"));

        centerPan.add(keyPan);

        bas.add(centerPan, BorderLayout.CENTER);
        JPanel listIndPan = new JPanel();
        listIndPan.setBorder(BorderFactory
                .createTitledBorder("Liste des indices donnés : "));
        JListIndice = new JList();
        JListIndice.setPreferredSize(new Dimension(300,300));
        listIndPan.add(JListIndice);
        bas.add(listIndPan, BorderLayout.EAST);

        main.add(bas);

        // timer
        /*
        timerAttenteJ2 = new Timer(5000, new AttenteDeJ2Listener(this, partie, serv));
        if (partie.getEtat().state().equals(STATE_STEP.GAME_INIT))
            timerAttenteJ2.start();
        else
            initGame();
        */
        // vue
        this.pack();

        this.setSize(new Dimension(800, 800));

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) /2 -400);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2 -400);
        this.setLocation(x, y);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void ouvrirMessageErreur(String msg, String titre) {
        JOptionPane.showMessageDialog(this,
                msg,
                titre,
                JOptionPane.ERROR_MESSAGE);
    }

    public void stopAttenteJ2() { timerAttenteJ2.stop();}


    public void initGame() {
        partie.getPlateau();

    }


}
