package swing.controleur;

import code.joueur;
import codenames.CodeNamesClient;
import codenames.exceptions.CnNetworkException;
import swing.vue.Fenetre;
import swing.vue.LoginPan;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class controleurLogin implements ActionListener {
    private LoginPan pan;
    private CodeNamesClient serv;

    public controleurLogin(LoginPan pan) {
        this.pan = pan;
        this.serv = new CodeNamesClient("http://51.178.49.138:3000/api/v0");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        joueur j = new joueur(pan.getPseudoLog(), pan.getmdpLog());

        try {
            if (joueur.listEnregistrement().contains(j)
                && serv.isRegisteredPlayer(pan.getPseudoLog())
            ){
                // On se co avec le bon login
                pan.setJoueur(j);
            }
        } catch (CnNetworkException ex) {
            ex.printStackTrace();
        }

    }
}
