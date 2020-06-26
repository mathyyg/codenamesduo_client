package modele;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Classe Indice qui représente l'indice envoyé pendant une partie
 *
 * @author Paul Vernin, Thomas Peray, Matéo Esteves, Mathys Gagner
 * @version 4.6
 */
public class Indice {
    private String indice;
    private int nbMotPourIndice;


    /**
     * Constructeur de la classe Indice
     * Prend en parametre un String et un Int qui compose l'indice à envoyer à l'autre joueur ( un mot et un chiffre)
     * @param s l'indice
     * @param nb le nombre de mot à trouver avec l'indice
     * @since 1.0
     */
    public Indice(String s, int nb){
        indice = s;
        nbMotPourIndice = nb;
    }


    /**
     * Méthode getIndice
     * Elle permet de renvoyer l'indice 
     * @return un string qui est l'indice
     * @since 1.0
     */
    public String getIndice() {
         return indice;
    }

    /**
     * Méthode get du nombre qui compose l'indice
     * Elle permet de renvoyer le nombre qui compose l'indice 
     * @return un int qui est le nombre de mots désignés par l'indice
     * @since 2.0
     */
    public int getNbMotPourIndice() {
         return nbMotPourIndice;
    }

    /**
     * Méthode estValide
     * Cette méthode permet de vérifier la validité de l'indice envoyer par le joueur :
     * -En vérifiant qu'il n'est pas vide
     * -En vérifiant que ce n'est pas un des mots du plateau
     * 
     * @param p qui représente la partie en cours
     * @return un boolean qui représente la validité ou non de l'indice
     */
    public boolean estValide(Partie p) {

        List<String> mots = p.getWords();
        for (String m : mots)
            if (indice.toLowerCase().contains(m.toLowerCase()) || m.toLowerCase().contains(indice.toLowerCase()))
                return false;

        Pattern patternMin = Pattern.compile("^[a-zÀ-ÿ]*-?[a-zÀ-ÿ]*$");
        Pattern patternMaj = Pattern.compile("^[A-ZÀ-ÿ]*-?[A-ZÀ-ÿ]*$");

        return patternMin.matcher(this.indice).matches() || patternMaj.matcher(this.indice).matches();
    }

    /**
     * Méthode toString
     * Elle permet de rassembler le mot valide et le chiffre valide dans une seule chaîne de caractère
     * @return le string composé du lit indice et du chiffre qui l'accompagne
     */
    public String toString() {
        return this.getIndice() + " : " + this.getNbMotPourIndice();
    }
 }
