package code;

import java.util.List;
import java.io.*;

public class EnregistrementJoueur {

    private List<joueur> lesjoueurs;
    private String line;

    public EnregistrementJoueur() {
        line = null;
        try {
            FileReader fileReader = new FileReader("joueurs.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String[] parts;
            while((line = bufferedReader.readLine()) != null) {
                parts = line.split(",");
                lesjoueurs.add(new joueur(parts[0], parts[1]));
            }
            bufferedReader.close();
        } catch (IOException err ) {
            err.getMessage();
        }
    }

    public boolean dejaEnregistre(joueur j) {
        return lesjoueurs.contains(j);
    }

}
