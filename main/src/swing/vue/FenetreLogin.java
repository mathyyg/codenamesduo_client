package swing.vue;

import code.Joueur;
import codenames.CodeNamesClient;
import swing.controleur.controleurLogin;
import swing.controleur.controleurRegister;

import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class FenetreLogin extends JFrame {

    private Joueur joueur;
    private CodeNamesClient serv;

    private JTextField pseudoReg;
    private JTextField mdpLog;
    private JTextField pseudoLog;
    private JLabel mdp;

    private JButton logBut;
    private JButton regBut;

    public FenetreLogin(String titre, CodeNamesClient leserv) {

        super(titre);
        joueur = null;
        serv = leserv;

        JPanel identif = new JPanel();
        identif.setBorder(BorderFactory
                .createTitledBorder("Identification"));
        JTabbedPane choix = new JTabbedPane();

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
        log3.add(logBut = new JButton("login"));
        login.add(log3);

        JPanel register = new JPanel();
        register.setLayout(new BoxLayout(register, BoxLayout.Y_AXIS));

        JPanel reg0 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        reg0.add(new JLabel("Choisissez un pseudo : "));
        register.add(reg0);
        register.add(pseudoReg = new JTextField());
        JPanel reg1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
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
        JLabel logoLab1 = new JLabel(new ImageIcon(imgSized));

        identif.add(logoLab1);
        identif.add(choix);

        try {
            img = ImageIO.read(new File("ressource"+File.separator+"infopotes.png"));
            imgSized = img.getScaledInstance(150, 150, Image.SCALE_DEFAULT);

        } catch (IOException e) { ouvrirMessageErreur(e.getMessage()+"\nmauvais fichier?","Erreur entrée/sortie"); }
        JLabel logoLab2 = new JLabel(new ImageIcon(imgSized));
        identif.add(logoLab2);
        this.add(identif);


        // Listener
        logBut.addActionListener(new controleurLogin(this, serv));
        regBut.addActionListener(new controleurRegister(this, serv));


        // vue
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) /2 -250);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2 -150);
        this.setLocation(x, y);
        setPreferredSize(new Dimension(520,250));
    }

    /**
     * Méthode second qui permet de passer à la fenêtre suivante (Fenetre Menu) et de fermer cette fenêtre
     */
    public void second() {
        FenetreRecherchePartie next = new FenetreRecherchePartie("Menu", getJoueur(), serv);
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

    public void setJoueur(Joueur j) { joueur = j; }
    public Joueur getJoueur() { return joueur; }


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

    public void setLabelMdp(String s) {
        mdp.setText("Votre mdp : " + s);
    }
}
