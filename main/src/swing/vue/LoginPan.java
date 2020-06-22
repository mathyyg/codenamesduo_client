package swing.vue;

import code.joueur;
import swing.controleur.controleurLogin;
import swing.controleur.controleurRegister;

import javax.swing.*;
import java.awt.*;

public class LoginPan extends JPanel {

    private joueur j;

    private JTextField pseudoReg;
    private JTextField mdpLog;
    private JTextField pseudoLog;

    private JButton logBut;
    private JButton regBut;

    public LoginPan() {


        JTabbedPane choix = new JTabbedPane();

        JPanel login = new JPanel();
        login.add(new JLabel("Pseudo : "));
        login.add(pseudoLog = new JTextField());
        login.add(new JLabel("mot de passe : "));
        login.add(mdpLog = new JTextField());
        login.add(logBut = new JButton("login"));

        JPanel register = new JPanel();
        register.add(new JLabel("Choisissez un pseudo : "));
        register.add(pseudoReg = new JTextField());
        register.add(regBut = new JButton("register"));

        logBut.addActionListener(new controleurLogin(this));
        regBut.addActionListener(new controleurRegister(this));
    }

    public String getPseudoLog() { return pseudoLog.getText(); }
    public String getmdpLog() { return mdpLog.getText(); }
    public String getPseudoReg() { return pseudoReg.getText(); }
    public void setJoueur(joueur j) { this.j = j; }
}
