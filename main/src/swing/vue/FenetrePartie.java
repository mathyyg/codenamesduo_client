package swing.vue;

import codenames.CodeNamesClient;

import javax.swing.*;
import java.awt.*;

import codenames.cards.Card;
import codenames.exceptions.*;
import codenames.states.*;
import swing.panel.KeyCardPan;
import swing.panel.PlateauPan;
import swing.controleur.*;
import swing.timerControleur.*;
import modele.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Classe de la fenêtre de jeu
 *
 * @author Paul Vernin, Thomas Peray, Matéo Esteves, Mathys Gagner
 * @version 4.6
 */
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

    private JList<Indice> JListIndice;

    private JButton sendIndice;
    private JTextField indiceInput;
    private JComboBox<Integer> indicechiffre;
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

        JPanel profetquit = new JPanel();
        profetquit.setLayout(new BoxLayout(profetquit,BoxLayout.Y_AXIS));

        JPanel profil = new JPanel();
        profil.setLayout(new BoxLayout(profil, BoxLayout.Y_AXIS));
        profil.setBorder(BorderFactory
                .createTitledBorder("Profil"));
        profil.add(new JLabel("Pseudo : " + joueur.getPseudo()));
        profil.add(new JLabel("Mdp : " + joueur.getMdp()));
        profil.add(new JLabel("Parties gagnées : " + joueur.getPWin()));
        profil.add(new JLabel("Parties perdues : " + joueur.getPLoos()));
        JButton boutonQuitter = new JButton("Quitter");

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
        JPanel centerButPan = new JPanel(new FlowLayout(FlowLayout.CENTER));
        sendReponse = new JButton("Envoyer la réponse");
        centerButPan.add(sendReponse);
        reponsePan.add(centerButPan);
        indiceRepTabPan.addTab("Réponse", reponsePan);
        hautCenterPan.add(indiceRepTabPan);

        JPanel listIndPan = new JPanel();
        listIndPan.setBorder(BorderFactory
                .createTitledBorder("Liste des indices : "));
        JListIndice = new JList<>();
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


        // timer listener
        timerAttenteJ2 = new Timer(5000, new AttenteDeJ2Listener(this, partie, serv));
        timerState = new Timer(2000, new StateListener(this,partie,serv));
        if (partie.getEtat().state().equals(STATE_STEP.GAME_INIT)){
            sendIndice.setEnabled(false);
            sendReponse.setEnabled(false);
            timerAttenteJ2.start();
        }
        else {
            modeDeJeuTab(0);
            initGame();
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

    /**
     * Méthode qui ouvre une boîte de dialogue d'erreur
     * @param msg le message d'erreur
     * @param titre le titre de la boîte de dialogue
     */
    public void ouvrirMessageErreur(String msg, String titre) {
        JOptionPane.showMessageDialog(this,
                msg,
                titre,
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Méthode qui récupere la liste des réponses séléctionnées sur le plateau
     * @return une List de String (représentant les mots)
     */
    public List<String> getAnswer() { return plateau.getMotSelected(); }

    /**
     * Méthode qui met à jour la console
     * @param s le message à ajouter à la console
     */
    public void updateConsole(String s) { console.append(s+"\n");}

    /**
     * Méthode qui récupère le contenu de la console
     * @return message dans la console (String)
     */
    public String getConsoleText() { return console.getText(); }

    /**
     * Méthode qui reset la console
     */
    public void resetConsole() { console.setText("");}

    /**
     * Méthode qui récupère l'indice du JTextField
     * @return l'indice (string)
     */
    public String getClue() { return indiceInput.getText(); }

    /**
     * getter du nombre de mot correspondant à l'indice
     * @return un nombre (int)
     */
    public int getMotParClue() { return (int) indicechiffre.getSelectedItem(); }

    /**
     * Méthode qui reset le JTextField indiceInput
     */
    public void resetClue() { indiceInput.setText(""); }

    /**
     * Méthode qui lance le timerState
     */
    public void startState() {
        timerState.start();
    }

    /**
     * Méthode qui arrête le timerState
     */
    public void stopState() { timerState.stop(); }

    /**
     * Méthode qui arrête le timerAttenteJ2
     */
    public void stopAttenteJ2() { timerAttenteJ2.stop();}

    /**
     * Méthode qui rend actif ou inactif le bouton pour envoyer une réponse
     * @param b boolean pour choisir entre actif/inactif
     */
    public void setEnableSendAnswer(boolean b) { sendReponse.setEnabled(b);}

    /**
     * Méthode qui rend actif ou inactif le bouton pour envoyer un indice
     * @param b boolean pour choisir entre actif/inactif
     */
    public void setEnableSendClue(boolean b) { sendIndice.setEnabled(b);}

    /**
     * Méthode qui initialise le plateau et la KeyCard.
     */
    public void initGame() {
        plateau.init(partie.getWords());
        keyCardPan.init(partie.getKeyCard());
    }

    /**
     * Méthode qui update le plateau en changeant la couleur des cases découvertes
     * @param cList listes des cartes découvertes
     */
    public void updatePlateau(List<Carte> cList) {
        partie.updatePlateau(cList);
        plateau.updatePlateau(cList);
    }

    /**
     * Méthode qui met à jour le plateau par rapport à la liste renvoyé par
     * la méthode previousAnswer du serveur
     */
    public void majPreviousAnswer() {
        try {
            List<Card> cardList = partie.getEtat().previousAnswer();
            List<Carte> carteList = new ArrayList<>();
            for (Card c : cardList)
                carteList.add(new Carte(c));
            plateau.updatePlateau(carteList);
        } catch (IllegalAccessException e) {
            ouvrirMessageErreur(e.getMessage(),"Erreur : action interdite/impossible");
        }
    }

    /**
     * Méthode qui met à jour la liste des indices.
     */
    public void updateListIndice() {
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

    /**
     * Méthode qui change l'aspect et les droits d'accès aux différents onglets du JTabbedPan
     * @param i indice correspondant 0 : mode envoie indice
     *                               1 : mode envoie de réponse
     */
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

    /**
     * setter de NO_MORE_CARD
     * @param b boolean : <code>true</code> si il n'y a plus de carte, sinon <code>false</code>
     */
    public void setNoMoreCard(boolean b) { NO_MORE_CARD = b;}

    /**
     * Méthode qui retourne si oui ou non, on n'a plus de cartes CODE
     * @return <code>true</code> si il n'y a plus de carte CODE, sinon <code>false</code>
     */
    public boolean getNoMoreCard() { return NO_MORE_CARD;}

    /**
     * Méthode qui permet de quitter la partie et de revenir au menu.
     * @param i spécifie le type de retour()
     *          i = -1 : abandon de la partie (partie perdue)
     *          i = 0 : partie perdue
     *          i = 1 : partie gagnée
     */
    public void retour(int i) {
        if (timerAttenteJ2.isRunning())
            this.stopAttenteJ2();
        if (timerState.isRunning())
            this.stopState();
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
            joueur.partieLoosUp();
            joueur.updateWin(false);
            JOptionPane.showMessageDialog(this,
                    "Vous avez abandonné la partie, cela compte comme une défaite..",
                    "",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        if (i == 1){
            joueur.partieWinUp();
            joueur.updateWin(true);

            JOptionPane.showMessageDialog(this,
                    "Félicitation vous avez gagné la partie !",
                    "",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        if (i == 0){
            joueur.partieLoosUp();
            joueur.updateWin(false);
            JOptionPane.showMessageDialog(this,
                    "Pas de chance vous avez perdu...",
                    "",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        FenetreMenu nouveau = new FenetreMenu("Menu",joueur,serv);
        nouveau.setVisible(true);
        dispose();
    }
}
