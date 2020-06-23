package swing.vue;

import code.*;
import codenames.CodeNamesClient;
import swing.controleur.controleurLogin;
import swing.controleur.controleurRegister;

import javax.swing.*;

public class FenetreLogin extends JFrame {

    private Joueur joueur;
    private CodeNamesClient serv;

    private JTextField pseudoReg;
    private JTextField mdpLog;
    private JTextField pseudoLog;

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
        login.add(new JLabel("Pseudo : "));
        login.add(pseudoLog = new JTextField());
        login.add(new JLabel("mot de passe : "));
        login.add(mdpLog = new JPasswordField());
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

    }

    public void second() {
        FenetreRecherchePartie next = new FenetreRecherchePartie("Menu", getJoueur(), serv);
        next.setVisible(true);
        this.dispose();
    }
    public String getPseudoLog() { return pseudoLog.getText(); }
    public String getmdpLog() { return mdpLog.getText(); }
    public String getPseudoReg() { return pseudoReg.getText(); }

    public void setJoueur(Joueur j) { joueur = j; }
    public Joueur getJoueur() { return joueur; }

    public void ouvrirMessageErreur(String msg, String titre) {
        JOptionPane.showMessageDialog(this,
                msg,
                titre,
                JOptionPane.ERROR_MESSAGE);
    }
}
