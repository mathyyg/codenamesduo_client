package code;

import codenames.iPlayer;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class joueur implements iPlayer {

    private String nom;
    private String mdp;
    private int partieWin, partieLoos;

    public joueur(String lenom) {
        nom = lenom;
        mdp = null;
    }
    public joueur(String lenom, String lemdp) {
        nom = lenom;
        mdp = lemdp;
    }

    @Override
    public String login() {
        return nom;
    }

    public void setMdp(String s) {
        mdp = s;
    }

    public String getMdp() {
        return mdp;
    }

    public void enregistrement() throws IOException {
        if (joueur.listEnregistrement().contains(this)){
            System.out.println(this.nom + "est déjà enregistré");
            return;
        }
        try {
            FileWriter fileWriter = new FileWriter("joueurs.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(nom + "," + mdp+","+partieWin+","+partieLoos+"\n");
            bufferedWriter.close();
        } catch (IOException err) {
            System.out.println("erreur de recopie dans le fichier.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        joueur joueur = (joueur) o;
        return Objects.equals(nom, joueur.nom) &&
                Objects.equals(mdp, joueur.mdp);
    }


    public static List<joueur> listEnregistrement() {
        String line = null;
        List<joueur> lesjoueurs = new ArrayList<>();
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
        return lesjoueurs;
    }
}
