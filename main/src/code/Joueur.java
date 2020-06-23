package code;

import codenames.iPlayer;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Joueur implements iPlayer {

    private String pseudo;
    private String mdp;
    private int partieWin, partieLoos;

    public Joueur(String lenom) {
        pseudo = lenom;
        mdp = null;
    }
    public Joueur(String lenom, String lemdp) {
        pseudo = lenom;
        mdp = lemdp;
    }

    @Override
    public String login() {
        return pseudo;
    }

    public void setMdp(String s) {
        mdp = s;
    }
    public void PartieWinUp() { partieWin++;}
    public void PartieLoosUp() { partieLoos++;}

    public String getMdp() {
        return mdp;
    }
    public String getPseudo() { return pseudo; }
    public String getPWin() { return String.valueOf(partieWin);}
    public String getPLoos() { return String.valueOf(partieLoos);}

    public void enregistrement() throws IOException {
        if (Joueur.listEnregistrement().contains(this)){
            System.out.println(this.pseudo + "est déjà enregistré");
            return;
        }
        try {
            FileWriter fileWriter = new FileWriter("joueurs.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(pseudo + "," + mdp+","+partieWin+","+partieLoos+"\n");
            bufferedWriter.close();
        } catch (IOException err) {
            System.out.println("erreur de recopie dans le fichier.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joueur joueur = (Joueur) o;
        return Objects.equals(pseudo, joueur.pseudo) &&
                Objects.equals(mdp, joueur.mdp);
    }


    public static List<Joueur> listEnregistrement() {
        String line = null;
        List<Joueur> lesjoueurs = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("joueurs.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String[] parts;
            while((line = bufferedReader.readLine()) != null) {
                parts = line.split(",");
                lesjoueurs.add(new Joueur(parts[0], parts[1]));
            }
            bufferedReader.close();
        } catch (IOException err ) {
            err.getMessage();
        }
        return lesjoueurs;
    }
}
