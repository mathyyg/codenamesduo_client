package swing.controleur;

import code.Joueur;
import codenames.CodeNamesClient;
import codenames.exceptions.CnBadLoginException;
import codenames.exceptions.CnNetworkException;
import swing.vue.FenetreLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class controleurLogin implements ActionListener {
    private FenetreLogin pan;
    private CodeNamesClient serv;

    public controleurLogin(FenetreLogin pan, CodeNamesClient leserv) {
        this.pan = pan;
        this.serv = leserv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Joueur j = new Joueur(pan.getPseudoLog(), pan.getmdpLog());

        if (pan.getPseudoLog().isBlank() || pan.getmdpLog().isBlank())
            pan.ouvrirMessageErreur("zone de texte vide", "Erreur d'input");

        try {
            if (Joueur.listEnregistrement().contains(j)
                && serv.isRegisteredPlayer(pan.getPseudoLog())){
                System.out.println("authentification réussite.");
                pan.setJoueur(j);
                pan.second();
            } else if (serv.isRegisteredPlayer(pan.getPseudoLog())){
                pan.ouvrirMessageErreur("le mot de passe ne correspond pas à l'identifiant","Erreur login");
            } else {
                pan.ouvrirMessageErreur("Il n'existe pas de login ayant ce nom sur le serveur","Erreur login");
            }
        } catch (CnNetworkException ex) {
            pan.ouvrirMessageErreur(ex.getMessage(), "Erreur serveur");
        }
    }
}
