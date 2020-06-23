package swing.controleur;

import code.joueur;
import codenames.CodeNamesClient;
import codenames.exceptions.CnBadLoginException;
import codenames.exceptions.CnNetworkException;
import swing.vue.LoginPan;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class controleurRegister implements ActionListener {
    private LoginPan pan;
    private CodeNamesClient serv;

    public controleurRegister(LoginPan pan, CodeNamesClient leserv) {
        this.pan = pan;
        this.serv = leserv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        joueur j = new joueur(pan.getPseudoReg());

        if (pan.getPseudoReg().isBlank())
            pan.ouvrirMessageErreur("zone de texte vide", "Erreur d'input");


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
        System.out.println("Création du nouveau joueur et authentification réussite");
        pan.setJoueur(j);


    }
}
