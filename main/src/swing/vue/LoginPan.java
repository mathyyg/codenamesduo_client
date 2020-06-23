package swing.vue;

import code.joueur;
import codenames.CodeNamesClient;
import swing.controleur.controleurLogin;
import swing.controleur.controleurRegister;

import javax.swing.*;
import java.awt.*;

public class LoginPan extends JFrame {

    private joueur joueur;
    private CodeNamesClient serv;

    private JTextField pseudoReg;
    private JTextField mdpLog;
    private JTextField pseudoLog;

    private JButton logBut;
    private JButton regBut;

    public LoginPan(String titre, CodeNamesClient leserv) {

        super(titre);
        joueur = null;
        serv = leserv;

        JPanel identif = new JPanel();
        identif.setBorder(BorderFactory
                .createTitledBorder("Identification"));
        JTabbedPane choix = new JTabbedPane();

        JPanel login = new JPanel();
        login.setLayout(new BoxLayout(login, BoxLayout.Y_AXIS));
        login.add(new JLabel("Pseudo : "));
        login.add(pseudoLog = new JTextField());
        login.add(new JLabel("mot de passe : "));
        login.add(mdpLog = new JTextField());
        login.add(logBut = new JButton("login"));

        JPanel register = new JPanel();
        register.setLayout(new BoxLayout(register, BoxLayout.Y_AXIS));

        register.add(new JLabel("Choisissez un pseudo : "));
        register.add(pseudoReg = new JTextField());
        register.add(regBut = new JButton("register"));

        choix.addTab("Login", login);
        choix.addTab("Register", register);
        identif.add(choix);
        this.add(identif);

        logBut.addActionListener(new controleurLogin(this, serv));
        regBut.addActionListener(new controleurRegister(this, serv));


        /* Taille du login pour placement au centre de l'écran
        fenetre.setPreferredSize(new Dimension(500, 300));


        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - fenetre.getWidth()) /2 -250);
        int y = (int) ((dimension.getHeight() - fenetre.getHeight()) / 2 -150);
        fenetre.setLocation(x, y);
        */

    }

    public void second() {
        FenetreRecherchePartie next = new FenetreRecherchePartie("Rejoindre une partie", getJoueur(), serv);
        next.setVisible(true);
        this.dispose();
    }
    public String getPseudoLog() { return pseudoLog.getText(); }
    public String getmdpLog() { return mdpLog.getText(); }
    public String getPseudoReg() { return pseudoReg.getText(); }

    public void setJoueur(joueur j) { joueur = j; }
    public joueur getJoueur() { return joueur; }

    public void ouvrirMessageErreur(String msg, String titre) {
        JOptionPane.showMessageDialog(this,
                msg,
                titre,
                JOptionPane.ERROR_MESSAGE);
    }
}
