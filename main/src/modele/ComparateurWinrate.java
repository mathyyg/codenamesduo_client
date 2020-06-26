package modele;

import java.util.Comparator;

/**
 * Classe permettant de comparer deux candidatures par ordre alphabétique Nom, Prénom
 *
 * @author Paul Vernin, Thomas Peray, Matéo Esteves, Mathys Gagner
 * @version 4.6
 */
public class ComparateurWinrate implements Comparator<Joueur> {

    public ComparateurWinrate() {
    }

    /**
     * Compare deux candidats.
     *
     * @param j1 le premier joueur
     * @param j2 le second joueur
     * @return un entier supérieur à 0 si le winrate de j1 est plus grand que j2<br>
     *         un entier égal à 0 si les winrates sont égaux<br>
     *         un entier inférieur à 0 si le winrate de j1 est plus petit.
     */
    @Override
    public int compare(Joueur j1, Joueur j2) {
        return Float.compare(j2.getWinrate(), j1.getWinrate());
    }
}
