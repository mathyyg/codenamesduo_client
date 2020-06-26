package swing.vue;

import codenames.CodeNamesClient;

import javax.swing.*;
import java.awt.*;

import codenames.cards.Card;
import codenames.exceptions.CnBadLoginException;
import codenames.exceptions.CnBadPwdException;
import codenames.states.*;
import codenames.exceptions.CnBadIdException;
import codenames.exceptions.CnNetworkException;
import swing.panel.KeyCardPan;
import swing.panel.PlateauPan;
import swing.controleur.*;
import swing.timerControleur.*;
import modele.*;
import codenames.cards.CARD_ROLE;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class FenetrePartie extends JFrame {

    private Joueur joueur;
    private CodeNamesClient serv;
    private Partie partie;
    private Timer timerAttenteJ2;
    private Timer timerState;
    private Vector<Indice> ListIndice;
    private boolean NO_MORE_CARD;

    JTabbedPane indiceRepTabPan;
    private JTextArea console;
    private JLabel pseudo;
    private JLabel mdp;
    private JLabel pWin;
    private JLabel pLoos;

    private JList<Indice> JListIndice;

    private JButton sendIndice;
    private JTextField indiceInput;
    private JComboBox<Integer> indicechiffre;
    private JTextField reponseInput;
    private JButton sendReponse;
    private JButton boutonQuitter;

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

        JPanel profetquit = new JPanel();
        profetquit.setLayout(new BoxLayout(profetquit,BoxLayout.Y_AXIS));

        JPanel profil = new JPanel();
        profil.setLayout(new BoxLayout(profil, BoxLayout.Y_AXIS));
        profil.setBorder(BorderFactory
                .createTitledBorder("Profil"));
        profil.add(pseudo = new JLabel("Pseudo : " + joueur.getPseudo()));
        profil.add(mdp = new JLabel("Mdp : " + joueur.getMdp()));
        profil.add(pWin = new JLabel("Parties gagnées : " + joueur.getPWin()));
        profil.add(pLoos = new JLabel("Parties perdues : " + joueur.getPLoos()));
        profil.add(new JLabel("Classement local : " + joueur.getClassement()));
        boutonQuitter = new JButton("Quitter");

        JPanel profilMain = new JPanel(new FlowLayout(FlowLayout.CENTER));
        profilMain.add(profil);
        JPanel quitter = new JPanel(new FlowLayout(FlowLayout.CENTER));
        quitter.add(boutonQuitter);

        profetquit.add(profilMain);
        profetquit.add(quitter);

        hautCenterPan.add(profetquit);

        indiceRepTabPan = new JTabbedPane();

        JPanel indicePan = new JPanel();
        JPanel pan1 = new JPanel();
        pan1.setLayout(new BoxLayout(pan1, BoxLayout.Y_AXIS));
        pan1.add(new JLabel("Entre l'indice ci-dessous : "));
        pan1.add(new JLabel("Nombre de mot pour l'indice : "));
        JPanel pan2 = new JPanel();
        pan2.setLayout(new BoxLayout(pan2, BoxLayout.Y_AXIS));
        pan2.add(indiceInput = new JTextField());
        indiceInput.setColumns(10);
        Integer[] chiffres = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        pan2.add(indicechiffre = new JComboBox<>(chiffres));
        indicePan.add(pan1);
        indicePan.add(pan2);
        indicePan.add(sendIndice = new JButton("Envoyer"));

        indiceRepTabPan.addTab("Indice", indicePan);

        JPanel reponsePan = new JPanel();
        reponsePan.setLayout(new BoxLayout(reponsePan, BoxLayout.Y_AXIS));
        JPanel gLab = new JPanel(new FlowLayout(FlowLayout.LEFT));
        gLab.add(new JLabel("Listez vos réponses dans le cadre en les séparant avec une ',' : "));
        reponsePan.add(gLab);
        reponsePan.add(reponseInput = new JTextField());
        reponseInput.setColumns(10);
        JPanel centerButPan = new JPanel(new FlowLayout(FlowLayout.CENTER));
        sendReponse = new JButton("Envoyer la réponse");
        centerButPan.add(sendReponse);
        reponsePan.add(centerButPan);
        indiceRepTabPan.addTab("Réponse", reponsePan);
        hautCenterPan.add(indiceRepTabPan);

        JPanel listIndPan = new JPanel();
        listIndPan.setBorder(BorderFactory
                .createTitledBorder("Liste des indices donnés : "));
        JListIndice = new JList();
        JListIndice.setPreferredSize(new Dimension(150,120));
        listIndPan.add(JListIndice);

        hautCenterPan.add(listIndPan);

        centerPan.add(hautCenterPan, BorderLayout.NORTH);

        // KeyCard
        keyCardPan = new KeyCardPan();
        keyCardPan.setBorder(BorderFactory
                .createTitledBorder("KeyCard"));

        centerPan.add(keyCardPan, BorderLayout.CENTER);

        bas.add(centerPan, BorderLayout.CENTER);

        console = new JTextArea();
        console.setLineWrap(true);
        console.setWrapStyleWord(true);
        Font f = console.getFont();
        console.setFont(new Font(f.getName(), f.getStyle(), f.getSize()-1));
        console.setEditable(false);
        JScrollPane scroll = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        Dimension d = new Dimension();
        d.width = 300;
        scroll.setPreferredSize(d);
        scroll.setBorder(BorderFactory
                .createTitledBorder("Console : "));
        bas.add(scroll, BorderLayout.EAST);

        main.add(bas);


        // timer
        timerAttenteJ2 = new Timer(5000, new AttenteDeJ2Listener(this, partie, serv));
        timerState = new Timer(2000, new StateListener(this,partie,serv));
        if (partie.getEtat().state().equals(STATE_STEP.GAME_INIT)){
            sendIndice.setEnabled(false);
            sendReponse.setEnabled(false);
            timerAttenteJ2.start();
        }
        else {
            initGame();
            System.out.println("Le 2eme joueur est arrivé la partie va commencer.");
            timerState.start();

        }


        // Listener
        sendIndice.addActionListener(new controleurSendClue(this, serv, partie));
        sendReponse.addActionListener(new controleurSendAnswer(this, serv, partie));
        boutonQuitter.addActionListener(e -> retour(-1));


        // vue
        this.pack();

        this.setSize(new Dimension(1150, 800));

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) /2 );
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2 );
        this.setLocation(x, y);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void ouvrirMessageErreur(String msg, String titre) {
        JOptionPane.showMessageDialog(this,
                msg,
                titre,
                JOptionPane.ERROR_MESSAGE);
    }
    public List<String> getAnswer() { return plateau.getMotSelected(); }
    public void updateConsole(String s) { console.append(s+"\n");}
    public String getConsoleText() { return console.getText(); }
    public void resetConsole() { console.setText("");}
    public String getClue() { return indiceInput.getText(); }
    public void resetAnswer() { reponseInput.setText(""); }
    public void resetClue() { indiceInput.setText(""); }
    public int getMotParClue() { return (int) indicechiffre.getSelectedItem(); }
    public void startState() {
        timerState.start();
    }
    public void stopState() { timerState.stop(); }
    public void stopAttenteJ2() { timerAttenteJ2.stop();}
    public void setEnableSendAnswer(boolean b) { sendReponse.setEnabled(b);}
    public void setEnableSendClue(boolean b) { sendIndice.setEnabled(b);}


    public void initGame() {
        plateau.init(partie.getWords());
        keyCardPan.init(partie.getKeyCard());
    }


    public void updatePlateau(List<Carte> cList) {
        partie.plateauMAJ(cList);
        plateau.updatePlateau(cList);
    }

    public void majPreviousAnswer() {
        try {
            System.out.println(partie.getEtat().previousAnswer());
            List<Card> cardList = partie.getEtat().previousAnswer();
            List<Carte> carteList = new ArrayList<>();
            for (Card c : cardList)
                carteList.add(new Carte(c));
            plateau.updatePlateau(carteList);
        } catch (IllegalAccessException e) {
            ouvrirMessageErreur(e.getMessage(),"Erreur : action interdite/impossible");
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
            ouvrirMessageErreur(e.getMessage(),"Erreur : action interdite/impossible");
        } catch (CnNetworkException e) {
            ouvrirMessageErreur(e.getMessage(),"Erreur serveur");
        } catch (CnBadIdException e) {
            ouvrirMessageErreur(e.getMessage(), "Erreur ID partie");
        }
    }
    public void modeDeJeuTab(int i ) {
        if (i == 0){
            indiceRepTabPan.setEnabledAt(1, false);
            indiceRepTabPan.setEnabledAt(0, true);
            indiceRepTabPan.setSelectedIndex(0);
        } else if (i == 1) {
            indiceRepTabPan.setEnabledAt(0, false);
            indiceRepTabPan.setEnabledAt(1, true);
            indiceRepTabPan.setSelectedIndex(1);
        } else if (i == -1){
            indiceRepTabPan.setEnabledAt(0, false);
            indiceRepTabPan.setEnabledAt(1, false);
        }
    }
    public void setNoMoreCard(boolean b) { NO_MORE_CARD = b;}
    public boolean getNoMoreCard() { return NO_MORE_CARD;}
    public void retour(int i) {
        JOptionPane d = new JOptionPane();
        if (i == -1){
            try {
                if (serv.abortGame(partie.getIdPartie(), joueur, joueur.getMdp()))
                    System.out.println("la partie a bien été abandonnée");
            } catch (CnNetworkException e) {
                this.ouvrirMessageErreur("Le serveur semble être inaccessible","Erreur abortGame");
            } catch (CnBadIdException e) {
                this.ouvrirMessageErreur("L'ID de la partie ne correspond pas","Erreur abortGame");
            } catch (CnBadLoginException e) {
                this.ouvrirMessageErreur("Le login ne correspond pas","Erreur abortGame");
            } catch (CnBadPwdException e) {
                this.ouvrirMessageErreur("Le mot de passe ne correspond pas","Erreur abortGame");
            }
        }
        if (i == 1){
            joueur.partieWinUp();
            joueur.updateWin(true);

            d.showMessageDialog(this,
                    "Félicitation vous avez gagné la partie !",
                    "",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        if (i == 0){
            joueur.partieLoosUp();
            joueur.updateWin(false);
            d.showMessageDialog(this,
                    "Pas de chance vous avez perdu...",
                    "",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        FenetreRecherchePartie nouveau = new FenetreRecherchePartie("Menu",joueur,serv);
        nouveau.setVisible(true);
        if (timerAttenteJ2.isRunning())
            this.stopAttenteJ2();
        if (timerState.isRunning())
            this.stopState();
        dispose();
    }
}
