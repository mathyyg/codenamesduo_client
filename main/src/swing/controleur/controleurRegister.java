package swing.controleur;

import code.joueur;
import codenames.CodeNamesClient;
import codenames.exceptions.CnBadLoginException;
import codenames.exceptions.CnNetworkException;
import swing.vue.Fenetre;
import swing.vue.LoginPan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class controleurRegister implements ActionListener {
    private LoginPan pan;
    private CodeNamesClient serv;

    public controleurRegister(LoginPan pan) {
        this.pan = pan;
        this.serv = new CodeNamesClient("http://51.178.49.138:3000/api/v0");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        joueur j = new joueur(pan.getPseudoReg());

        try {
            if (serv.isRegisteredPlayer(pan.getPseudoReg())){
                System.out.println("Il existe déjà un compte avec ce login.");
                return;
            }
        } catch (CnNetworkException ex) {
            ex.printStackTrace();
        }

        try {
            j.setMdp(serv.addPlayer(j));
            j.enregistrement();
        } catch (CnNetworkException ex) {
            ex.printStackTrace();
        } catch (CnBadLoginException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            //fail enregistrement
            ex.printStackTrace();
        }
        pan.setJoueur(j);
    }
}