package modele;

import codenames.CodeNamesClient;
import codenames.exceptions.CnBadIdException;
import codenames.exceptions.CnNetworkException;
import codenames.states.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe JoueurOnline, spécifie les joueurs dont on connait le login mais dont on ne connaît pas le password
 * Nous avons créé cette classe pour pouvoir redéfinir le equals qui nous sert dans les méthodes List.contains(..)
 * qu'on utilise pour généré le classement.
 *
 * @author Paul Vernin, Thomas Peray, Matéo Esteves, Mathys Gagner
 * @version 4.6
 */
public class JoueurOnline extends Joueur {
    String login;

    /**
     * Constructeur n°1 : constructeur à partir d'un simple login
     * ce constructeur sert à créer des instances de JoueurOnline à partir des logins obtenus dans les listes
     * des parties finies (win/loos)
     * @param lenom le login
     */
    public JoueurOnline(String lenom) {
        super(lenom);
        login = lenom;
    }

    /**
     * Constructeur n°2 : constructeur à partir d'un joueur déjà existant
     * @param j un joueur
     */
    public JoueurOnline(Joueur j){
        super(j.login(), j.getMdp(), j.getPWin(), j.getPLoos());
        login = j.login();
    }

    /**
     * Méthode equals qui compare seulement le login des deux joueurs.
     * @param o l'objet auquel this est comparé
     * @return <code>true</code> si this et o sont identiques, sinon <code>false</code>
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        JoueurOnline that = (JoueurOnline) o;
        return Objects.equals(login, that.login);
    }

    /**
     * Méthode qui calcule le classement du joueur entré en paramètre par rapport aux autres
     * users qui ont fini des parties sur le serveur.
     * @param j le joueur dont on veut connaître le classement.
     * @param serv le serveur d'où on récupère les listes de parties finies (Win/Loos).
     * @return un string représentant le classement du joueuer j.
     */
    public static String getClassementOnline(JoueurOnline j, CodeNamesClient serv) {
        List<Integer> wonGames;
        List<Integer> lostGames;
        List<JoueurOnline> listJ = new ArrayList<>();
        State etat;
        JoueurOnline jCourant;
        try {
            wonGames = serv.wonGames();
            lostGames = serv.lostGames();
        } catch (CnNetworkException e) {
            System.err.println("Erreur de récupération des listes wonGames et lostGames\n");
            return "NaN";
        }
        try {
            for (Integer wG : wonGames){
                etat = serv.consultGame(wG);
                if (listJ.contains(etat.creator()))
                    listJ.get(listJ.indexOf(etat.creator())).partieWinUp();
                else if (!listJ.contains(etat.creator())){
                    jCourant = new JoueurOnline(etat.creator());
                    jCourant.partieWinUp();
                    listJ.add(jCourant);
                }
                else if (listJ.contains(etat.secondPlayer()))
                    listJ.get(listJ.indexOf(etat.secondPlayer())).partieWinUp();
                else if (!listJ.contains(etat.secondPlayer())){
                    jCourant = new JoueurOnline(etat.secondPlayer());
                    jCourant.partieWinUp();
                    listJ.add(jCourant);
                }
        }
        for (Integer lG : lostGames) {

            etat = serv.consultGame(lG);
            if (listJ.contains(etat.creator()))
                listJ.get(listJ.indexOf(etat.creator())).partieLoosUp();
            else if (!listJ.contains(etat.creator())) {
                jCourant = new JoueurOnline(etat.creator());
                jCourant.partieLoosUp();
                listJ.add(jCourant);
            } else if (listJ.contains(etat.secondPlayer()))
                listJ.get(listJ.indexOf(etat.secondPlayer())).partieLoosUp();
            else if (!listJ.contains(etat.secondPlayer())) {
                jCourant = new JoueurOnline(etat.secondPlayer());
                jCourant.partieLoosUp();
                listJ.add(jCourant);
            }
        }
        } catch (CnNetworkException e) {
            System.err.println("Erreur serveur");
        } catch (CnBadIdException e) {
            System.err.println("Erreur login");
        } catch (IllegalAccessException e) {
            System.err.println("Erreur IllegalAcessException");
        }
        if (!listJ.contains(j))
            listJ.add(j);
        listJ.sort(new ComparateurWinrate());
        return listJ.indexOf(j)+1 +"/"+listJ.size();
    }
}
