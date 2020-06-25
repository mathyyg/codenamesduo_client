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
    public Joueur(String lenom, String lemdp, int win, int loos) {
        pseudo = lenom;
        mdp = lemdp;
        partieWin = win;
        partieLoos = loos;
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
    public int getPWin() { return partieWin;}
    public int getPLoos() { return partieLoos;}
    public void setPWin(int i) { partieWin = i;}
    public void setPLoos(int i) { partieLoos = i;}

    public void enregistrement() {
        if (!Joueur.listEnregistrement().isEmpty()) {
            if (Joueur.listEnregistrement().contains(this)) {
                System.out.println(this.pseudo + "est déjà enregistré");
                return;
            }
        }
        try {
            FileWriter fileWriter = new FileWriter("ressource"+File.separator+"joueurs.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(pseudo + "," + mdp+","+partieWin+","+partieLoos+"\n");
            bufferedWriter.close();
        } catch (IOException err) {
            System.out.println("erreur de recopie dans le fichier.\n" + err.getMessage());
        }
    }

    public void updateWin(boolean aWin) {
        List<Joueur> listEnr = Joueur.listEnregistrement();

        int i = listEnr.indexOf(this);
        Joueur j = listEnr.get(i);
        if (aWin)
            j.setPWin(j.getPWin()+1);
        else
            j.setPLoos(j.getPLoos()+1);

        listEnr.set(i,j);
        try {
            FileWriter fileWriter = new FileWriter("ressource"+File.separator+"joueurs.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Joueur j2 : listEnr) {
                bufferedWriter.write(j2.toString());
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
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

    @Override
    public String toString() {
        return pseudo + "," + mdp + "," + partieWin + "," + partieLoos + "\n";
    }


    public static List<Joueur> listEnregistrement() {
        String line = null;
        List<Joueur> lesjoueurs = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("ressource"+File.separator+"joueurs.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String[] parts;
            while((line = bufferedReader.readLine()) != null) {
                parts = line.split(",");
                lesjoueurs.add(new Joueur(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3])));
            }
            bufferedReader.close();
        } catch (IOException err ) {
            err.getMessage();
        }
        return lesjoueurs;
    }


}
