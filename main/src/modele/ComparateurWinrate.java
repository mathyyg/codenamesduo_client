package modele;

import java.util.Comparator;

/**
 * Classe permettant de comparer deux candidatures par ordre alphabétique Nom, Prénom
 */
public class ComparateurWinrate implements Comparator<Joueur> {

    public ComparateurWinrate() {
    }

    /**
     * Compare deux candidats.
     *
     * @param j1 le premier joueur
     * @param j2 le second joueur
     * @return un entier < 0, =0 ou > 0 si le winrate du premier candidat est plus grand,
     * égale ou plus grande que le winrate du second candidat
     */
    @Override
    public int compare(Joueur j1, Joueur j2) {
        return Float.compare(j2.getWinrate(), j1.getWinrate());
    }
}
