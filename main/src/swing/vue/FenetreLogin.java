package swing.vue;

import codenames.CodeNamesClient;
import modele.Joueur;
import swing.controleur.controleurLogin;
import swing.controleur.controleurRegister;

import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Classe de la fenêtre de login
 *
 * @author Paul Vernin, Thomas Peray, Matéo Esteves, Mathys Gagner
 * @version 4.6
 */
public class FenetreLogin extends JFrame {

    private Joueur joueur;
    private CodeNamesClient serv;

    private JTextField pseudoReg;
    private JTextField mdpLog;
    private JTextField pseudoLog;
    JTabbedPane choix;
    private JLabel mdp;

    private JLabel labelMadeby;
    private Timer labelColor;

    public FenetreLogin(String titre, CodeNamesClient leserv) {

        super(titre);
        joueur = null;
        serv = leserv;

        JPanel identif = new JPanel();
        identif.setBorder(BorderFactory
                .createTitledBorder("Identification"));
        choix = new JTabbedPane();

        JPanel login = new JPanel();
        login.setLayout(new BoxLayout(login, BoxLayout.Y_AXIS));
        JPanel log1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        log1.add(new JLabel("Pseudo : "));
        login.add(log1);
        login.add(pseudoLog = new JTextField());
        JPanel log2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        log2.add(new JLabel("mot de passe : "));
        login.add(log2);
        login.add(mdpLog = new JPasswordField());
        JPanel log3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton logBut;
        log3.add(logBut = new JButton("login"));
        login.add(log3);

        JPanel register = new JPanel();
        register.setLayout(new BoxLayout(register, BoxLayout.Y_AXIS));

        JPanel reg0 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        reg0.add(new JLabel("Choisissez un pseudo : "));
        register.add(reg0);
        register.add(pseudoReg = new JTextField());
        JPanel reg1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton regBut;
        reg1.add(regBut = new JButton("register"));
        register.add(reg1);
        JPanel reg2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        reg2.add(mdp = new JLabel("Votre mdp : "));
        register.add(reg2);

        choix.addTab("Login", login);
        choix.addTab("Register", register);

        Image img;
        Image imgSized = null;
        try {
            img = ImageIO.read(new File("ressource"+File.separator+"logo_dut.png"));
            imgSized = img.getScaledInstance(150, 150, Image.SCALE_DEFAULT);

        } catch (IOException e) { ouvrirMessageErreur(e.getMessage()+"\nmauvais fichier?","Erreur entrée/sortie"); }
        assert imgSized != null;
        JLabel logoLab1 = new JLabel(new ImageIcon(imgSized));

        identif.add(logoLab1);
        identif.add(choix);

        try {
            img = ImageIO.read(new File("ressource"+File.separator+"infopotes.png"));
            imgSized = img.getScaledInstance(150, 150, Image.SCALE_DEFAULT);

        } catch (IOException e) { ouvrirMessageErreur(e.getMessage()+"\nMauvais fichier?","Erreur entrée/sortie"); }
        JLabel logoLab2 = new JLabel(new ImageIcon(imgSized));
        identif.add(logoLab2);

        JPanel credits = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelMadeby = new JLabel("Made by Infopotes");
        credits.add(labelMadeby);
        identif.add(credits);

        Color[] colorTable = new Color[]{Color.red, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.blue, Color.MAGENTA, Color.black,
        Color.gray,Color.darkGray,Color.pink,Color.cyan};
        labelColor = new Timer(200,e -> setMadeBy(colorTable[ThreadLocalRandom.current().nextInt(0,10)]));
        labelColor.start();
        this.add(identif);


        // Listener
        logBut.addActionListener(new controleurLogin(this, serv));
        regBut.addActionListener(new controleurRegister(this, serv));


        // vue
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) /2 -250);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2 -150);
        this.setLocation(x, y);
        setPreferredSize(new Dimension(520,260));
    }

    /**
     * Méthode second qui permet de passer à la fenêtre suivante (Fenetre Menu) et de fermer cette fenêtre
     */
    public void second() {
        FenetreMenu next = new FenetreMenu("Menu", joueur, serv);
        next.setVisible(true);
        this.dispose();
    }

    /**
     * Retourne le pseudo entré dans le JTextField du Login
     * @return pseudo (String)
     */
    public String getPseudoLog() { return pseudoLog.getText(); }

    /**
     * Retourne le mot de passe entré dans le JTextField du Login
     * @return mot de passe (String)
     */
    public String getmdpLog() { return mdpLog.getText(); }


    /**
     * Retourne le pseudo entré dans le JTextField du Register
     * @return pseudo (String)
     */
    public String getPseudoReg() { return pseudoReg.getText(); }

    /**
     * Setter de l'instance joueur
     * @param j le joueur
     */
    public void setJoueur(Joueur j) { joueur = j; }

    /**
     * Méthode qui ouvre une boîte de dialogue pour signaler une erreur
     * @param msg message d'erreur
     * @param titre titre de la boîte de dialogue
     */
    public void ouvrirMessageErreur(String msg, String titre) {
        JOptionPane.showMessageDialog(this,
                msg,
                titre,
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Méthode qui affiche  le mot de passe du compte registered (enregistré)
     * @param s le mot de passe
     */
    public void setLabelMdp(String s) {
        mdp.setText("Votre mdp : " + s);
    }

    /**
     * Méthode qui affiche en multicolor
     * @param color la couleur
     */
    public void setMadeBy(Color color) { labelMadeby.setForeground(color); labelColor.start();}

    public void preremplir(String login, String mdp) {
        choix.setSelectedIndex(0);
        pseudoLog.setText(login);
        mdpLog.setText(mdp);
    }
}
