/**
 * @author Les Infopotes
 * @version 4
 */
package swing.controleur;

import modele.Joueur;
import codenames.CodeNamesClient;
import codenames.exceptions.CnBadLoginException;
import codenames.exceptions.CnNetworkException;
import swing.vue.FenetreLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class controleurRegister implements ActionListener {
    private FenetreLogin pan;
    private CodeNamesClient serv;

    /**
     * Constructeur du contrôleur pour enregistrer un nouveau login
     * @param pan
     * @param leserv
     */
    public controleurRegister(FenetreLogin pan, CodeNamesClient leserv) {
        this.pan = pan;
        this.serv = leserv;
    }

    /**
     * Vérifie si le nouveau login n'est pas déjà enregistré: enregistre le login, renvoie le mdp généré;
     * affiche une erreur sinon
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Joueur j = new Joueur(pan.getPseudoReg());

        if (pan.getPseudoReg().isBlank())
            pan.ouvrirMessageErreur("zone de texte vide", "Erreur d'input");


        try {
            if (serv.isRegisteredPlayer(pan.getPseudoReg())){
                pan.ouvrirMessageErreur("Il existe déjà un compte avec ce login.\n " +
                        "Repartez sur l'onglet login pour vous connecter", "Erreur de register");
                return;
            }
        } catch (CnNetworkException ex) {
            pan.ouvrirMessageErreur(ex.getMessage(),"Erreur serveur");
        }

        try {
            j.setMdp(serv.addPlayer(j));
            j.enregistrement();
        } catch (CnNetworkException ex) {
            pan.ouvrirMessageErreur(ex.getMessage(),"Erreur serveur");
        } catch (CnBadLoginException ex) {
            pan.ouvrirMessageErreur("Il n'existe pas de login ayant ce nom sur le serveur\n" +
                    "OU erreur de login autre","Erreur login");
        }
        System.out.println("Création du nouveau joueur et authentification réussite");
        pan.setJoueur(j);
        pan.setLabelMdp(j.getMdp());
    }
}
