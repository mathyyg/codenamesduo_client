package swing.controleur;

import codenames.CodeNamesClient;
import codenames.exceptions.CnNetworkException;
import modele.Joueur;
import swing.vue.FenetreLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Contrôleur qui permet de se login et d'accèder au Menu
 *
 * @author Paul Vernin, Thomas Peray, Matéo Esteves, Mathys Gagner
 * @version 4.6
 */
public class controleurLogin implements ActionListener {
    private FenetreLogin pan;
    private CodeNamesClient serv;

    /**
     * Constructeur du contrôleur pour la validité et l'enregistrement du login
     * @param pan la fenêtre associée
     * @param leserv le serveur associé
     */
    public controleurLogin(FenetreLogin pan, CodeNamesClient leserv) {
        this.pan = pan;
        this.serv = leserv;
    }

    /**
     * vérifie si le login est existant dans joueurs.txt et ouvre la fenêtre de recherche si oui
     * @param e ActionEvent (inutilisé)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Joueur j = new Joueur(pan.getPseudoLog(), pan.getmdpLog());

        if (pan.getPseudoLog().isBlank() || pan.getmdpLog().isBlank()){
            pan.ouvrirMessageErreur("zone de texte vide", "Erreur d'input");
            return;
        }

        try {
            if (Joueur.listEnregistrement().contains(j)
                && serv.isRegisteredPlayer(pan.getPseudoLog())){
                int i = Joueur.listEnregistrement().indexOf(j);
                j = Joueur.listEnregistrement().get(i);
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
