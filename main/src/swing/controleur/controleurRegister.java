package swing.controleur;

import codenames.CodeNamesClient;
import codenames.exceptions.CnBadLoginException;
import codenames.exceptions.CnNetworkException;
import modele.Joueur;
import swing.vue.FenetreLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Contrôleur qui permet de créer un compte
 *
 * @author Paul Vernin, Thomas Peray, Matéo Esteves, Mathys Gagner
 * @version 4.6
 */
public class controleurRegister implements ActionListener {
    private FenetreLogin fn;
    private CodeNamesClient serv;

    /**
     * Constructeur du contrôleur Register
     * @param lafn la fenêtre Login
     * @param leserv le serveur
     */
    public controleurRegister(FenetreLogin lafn, CodeNamesClient leserv) {
        fn = lafn;
        serv = leserv;
    }

    /**
     * Vérifie si le nouveau login n'est pas déjà enregistré: enregistre le login, renvoie le mdp généré;
     * affiche une erreur sinon
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Joueur j = new Joueur(fn.getPseudoReg());

        if (fn.getPseudoReg().isBlank())
            fn.ouvrirMessageErreur("zone de texte vide", "Erreur d'input");


        try {
            if (serv.isRegisteredPlayer(fn.getPseudoReg())){
                fn.ouvrirMessageErreur("Il existe déjà un compte avec ce login.\n " +
                        "Repartez sur l'onglet login pour vous connecter", "Erreur de register");
                return;
            }
        } catch (CnNetworkException ex) {
            fn.ouvrirMessageErreur(ex.getMessage(),"Erreur serveur");
        }

        try {
            j.setMdp(serv.addPlayer(j));
            j.enregistrement();
        } catch (CnNetworkException ex) {
            fn.ouvrirMessageErreur(ex.getMessage(),"Erreur serveur");
        } catch (CnBadLoginException ex) {
            fn.ouvrirMessageErreur("Il n'existe pas de login ayant ce nom sur le serveur\n" +
                    "OU erreur de login autre","Erreur login");
        }
        fn.setJoueur(j);
        fn.setLabelMdp(j.getMdp());
        fn.preremplir(j.getPseudo(),j.getMdp());
    }
}
