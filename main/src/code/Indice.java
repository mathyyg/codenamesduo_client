package code;

import java.util.List;

public class Indice {
    private String indice;
    private int nbMotPourIndice;

    public Indice(String s, int nb){
        indice = s;
        nbMotPourIndice = nb;
    }

    public String getIndice() { return indice; }
    public int getNbMotPourIndice() { return nbMotPourIndice; }

    public void MotTrouve() {
        if (nbMotPourIndice > 0)
            nbMotPourIndice--;
    }

    public boolean estValide(Partie p) {
        boolean valid = true;

        /* Cas où l'indice est invalide : */
        if (indice == null) {                           // Si l'indice proposé est vide
            return false;
        }

        List<String> mots = p.getPlateau();             // Si l'indice proposé comporte un mot du plateau
        for (String m : mots) {
            if (indice.contains(m)) {
                valid = false;
            }
        }

        try {
            Double num = Double.parseDouble(indice);    // Si l'indice proposé est un nombre
        } catch (NumberFormatException e) {
            valid = false;
        }

        /*Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");    // Version expression régulière
        if (pattern.matcher(indice).matches()) {
            valid = false;
        }*/

        return valid;
    }
}
