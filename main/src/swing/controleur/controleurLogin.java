package swing.controleur;

import code.joueur;
import codenames.CodeNamesClient;
import codenames.exceptions.CnBadLoginException;
import codenames.exceptions.CnNetworkException;
import swing.vue.LoginPan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class controleurLogin implements ActionListener {
    private LoginPan pan;
    private CodeNamesClient serv;

    public controleurLogin(LoginPan pan, CodeNamesClient leserv) {
        this.pan = pan;
        this.serv = leserv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        joueur j = new joueur(pan.getPseudoLog(), pan.getmdpLog());

        if (pan.getPseudoLog().isBlank() || pan.getmdpLog().isBlank())
            pan.ouvrirMessageErreur("zone de texte vide", "Erreur d'input");

        try {
            if (joueur.listEnregistrement().contains(j)
                && serv.isRegisteredPlayer(pan.getPseudoLog())){
                // On se co avec le bon login
                System.out.println("authentification réussite.");
                pan.setJoueur(j);
                pan.second();
            }
            // pas sur de cette partie.
            if (joueur.listEnregistrement().contains(j) && !serv.isRegisteredPlayer(pan.getPseudoLog())){

                j.setMdp(serv.addPlayer(j));
                j.enregistrement();
            }
        } catch (CnNetworkException ex) {
            pan.ouvrirMessageErreur(ex.getMessage(), "Erreur serveur");
        } catch (IOException ex) {
            pan.ouvrirMessageErreur("Erreur d'enregistrement du joueur (partie interne)", "Erreur Enregistrement");
        } catch (CnBadLoginException ex) {
            pan.ouvrirMessageErreur(ex.getMessage(), "Erreur de login");

        }


    }
}
