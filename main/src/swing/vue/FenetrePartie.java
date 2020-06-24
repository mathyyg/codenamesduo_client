package swing.vue;

import codenames.CodeNamesClient;

import javax.swing.*;
import java.awt.*;

import codenames.cards.Card;
import codenames.exceptions.CnBadIdException;
import codenames.exceptions.CnNetworkException;
import swing.controleur.*;
import swing.timerControleur.*;
import codenames.states.*;
import code.*;
import codenames.cards.CARD_ROLE;
import java.util.List;
import java.util.Vector;
import java.util.zip.ZipEntry;

public class FenetrePartie extends JFrame {

    private Joueur joueur;
    private CodeNamesClient serv;
    private Partie partie;
    private Timer timerAttenteJ2;
    private Timer timerState;
    private Vector<Indice> ListIndice;

    private JTextArea console;
    private JLabel pseudo;
    private JLabel mdp;
    private JLabel pWin;
    private JLabel pLoos;
    private JPanel BureauDesLegendes ;

    private JButton keycardBut;
    private JList<CARD_ROLE> keycardsList;
    private JList<Indice> JListIndice;

    private JButton sendIndice;
    private JTextField indiceInput;
    private JComboBox<Integer> indicechiffre;
    private JTextField reponseInput;
    private JButton sendReponse;

    private PlateauPan plateau;
    private KeyCardPan keyCardPan;

    public FenetrePartie(String titre, Joueur lejoueur, CodeNamesClient leserv, Partie lapartie) {
        super(titre);
        partie = lapartie;
        joueur = lejoueur;
        serv = leserv;

        ListIndice = new Vector<>();

        JPanel main = new JPanel();
        this.setContentPane(main);

        main.setLayout( new GridLayout(2,1));

        JPanel BureauDesLegendes = new JPanel();
        BureauDesLegendes.setLayout(new GridLayout(1,1));
        BureauDesLegendes.setBorder(BorderFactory.createLineBorder(Color.black));
        BureauDesLegendes.setBackground(new Color((255),(255),(255)));



        plateau = new PlateauPan();
        plateau.setBorder(BorderFactory
                .createTitledBorder("Plateau"));

        BureauDesLegendes.add(plateau);
        main.add(BureauDesLegendes);

        // Partie bas
        JPanel bas = new JPanel(new BorderLayout());

        JPanel centerPan = new JPanel();
        centerPan.setLayout(new BorderLayout());

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
        sendReponse = new JButton("Envoyer la réponse");
        reponsePan.add(sendReponse);
        indiceRepTabPan.addTab("Réponse", reponsePan);
        hautCenterPan.add(indiceRepTabPan);

        console = new JTextArea();
        console.setEditable(false);
        console.setPreferredSize(new Dimension(150,100));
        JScrollPane scroll = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        hautCenterPan.add(scroll);

        centerPan.add(hautCenterPan, BorderLayout.NORTH);

        // KeyCard
        keyCardPan = new KeyCardPan();
        keyCardPan.setBorder(BorderFactory
                .createTitledBorder("KeyCard"));

        centerPan.add(keyCardPan, BorderLayout.CENTER);

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
        timerAttenteJ2 = new Timer(5000, new AttenteDeJ2Listener(this, partie, serv));
        if (partie.getEtat().state().equals(STATE_STEP.GAME_INIT))
            timerAttenteJ2.start();
        else {
            initGame();
            timerState = new Timer(2000, new StateListener(this,partie,serv));
            System.out.println("Le 2eme joueur est arrivé la partie va commencer.");
            timerState.start();

        }


        // Listener
        sendIndice.addActionListener(new controleurSendClue(this, serv, partie));
        sendReponse.addActionListener(new controleurSendAnswer(this, serv, partie));

        // vue
        this.pack();

        this.setSize(new Dimension(1200, 800));

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
    public String getAnswer() { return reponseInput.getText();}
    public String getClue() { return indiceInput.getText(); }
    public int getMotParClue() { return (int) indicechiffre.getSelectedItem(); }
    public void startState() {
        timerState = new Timer(2000, new StateListener(this,partie,serv));
        timerState.start();
    }
    public void stopState() { timerState.stop(); }
    public void stopAttenteJ2() { timerAttenteJ2.stop();}


    public void initGame() {
        plateau.init(partie.getPlateau());
        keyCardPan.init(partie.getKeyCard());
    }





    public void updatePlateau(List<Card> cList) {
        plateau.updatePlateau(cList);
    }

    public void majPreviousAnswer() {
        try {
            System.out.println(partie.getEtat().previousAnswer());
            plateau.updatePlateau(partie.getEtat().previousAnswer());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public void majListIndice() {
        try {
            Indice i = new Indice(
                    serv.consultGame(partie.getIdPartie()).currentClue(),
                    serv.consultGame(partie.getIdPartie()).currentClueNumber());
            ListIndice.add(i);
            JListIndice.setListData(ListIndice);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (CnNetworkException e) {
            e.printStackTrace();
        } catch (CnBadIdException e) {
            e.printStackTrace();
        }
    }

}
