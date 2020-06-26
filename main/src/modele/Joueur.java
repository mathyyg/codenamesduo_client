package modele;

import codenames.iPlayer;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe Joueur qui implements iPlayer
 *
 * @author Paul Vernin, Thomas Peray, Matéo Esteves, Mathys Gagner
 * @version 4.6
 */
public class Joueur implements iPlayer {
    private String pseudo;
    private String mdp;
    private int partieWin, partieLoos;


    /**
     * Constrcuteur n°1
     * Constructeur qui permet de saisir que le pseudo
     * @param lenom String qui correspond au pseudo
     */
    public Joueur(String lenom) {
        pseudo = lenom;
        mdp = null;
    }

    /**
     * Constrcuteur n°2
     * Constructeur qui permet de saisir le peusdo et le mot de passe
     * @param lenom String qui correspond au pseudo
     * @param lemdp String qui coorrespond au mot de passe 
     */
    public Joueur(String lenom, String lemdp) {
        pseudo = lenom;
        mdp = lemdp;
    }

    /**
     * Constrcuteur n°3
     * Constructeur qui permet de saisir toutes les données
     * @param lenom String qui correspond au pseudo
     * @param lemdp String qui correspond au mot de passe
     * @param win Int qui correspond au nombre de partie Gagner par le joueur (s'il change de compte)
     * @param loos Int qui correspond au nombre de partie Perdu par le joueur (s'il change de compte)
     */
    public Joueur(String lenom, String lemdp, int win, int loos) {
        pseudo = lenom;
        mdp = lemdp;
        partieWin = win;
        partieLoos = loos;
    }


    /**
     * Méthode login
     * Elle permet de communiquer avec le serveur
     * @return string qui est le pseudo
     */
    @Override
    public String login() {
        return pseudo;
    }


    /**
     * Méthode setMdp
     * Elle permet de définir le mot de passe d'un compte
     * @param s String qui le mot de passe
     */
    public void setMdp(String s) {
        mdp = s;
    }

    /**
     * Méthode qui permet de mettre à jour le nombre de partie gagné par un joueur
     */
    public void partieWinUp() { partieWin++;}

    /**
     * Méthode qui permet de mettre à jour le nombre de partie perdu par un joueur
     */
    public void partieLoosUp() { partieLoos++;}


    /**
     * Méthode getMdp
     * Méthode qui permet de renvoyer le mot de passe du compte
     * @return string qui est le mot de passe du compte
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * Méthode getPseudo
     * Méthode qui permet de renvoyer le pseudo du compte
     * @return string qui est le pseudo du compte
     */
    public String getPseudo() { return pseudo; }

    /**
     * Méthode getPwin
     * Elle permet de renvoyer le nombre de partie gagné
     * @return int qui correspond au nombre de partie Gagné
     */
    public int getPWin() { return partieWin;}

    /**
     * Méthode getPloos
     * Elle permet de renvoyer le nombre de partie Perdu
     * @return int qui correspond au nombre de partie Perdu
     */
    public int getPLoos() { return partieLoos;}

    /**
     * Méthode setPWin 
     * Elle permet de définir le nombre de partie gagné par le joueur
     * @param i int qui est mis à jour par PartieWinUp()
     */
    public void setPWin(int i) { partieWin = i;}

    /**
     * Méthode setPLoos 
     * Elle permet de définir le nombre de partie perdu par le joueur
     * @param i int qui est mis à jour par PartieLoosUp()
     */
    public void setPLoos(int i) { partieLoos = i;}


    /**
     * Méthode enregistrement
     * ELle permet de vérifier si le pseudo saisit est déjà utilisé et de stocker les informations saisis dans un fichier
     */
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


    /**
     * Méthode updateWin
     * Elle permet de mettre à jour le compteur de nombre de partie gagné ou perdu pour le joueur
     * @param aWin boolean qui correspond à la victoire de la partie par le joueur
     */
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


    /**
     * Méthode equals
     * Elle permet tester l'égalité entre deux objets
     * @return le boolean qui test si le pseudo et mot de passe saisit correspondent 
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joueur joueur = (Joueur) o;
        return Objects.equals(pseudo, joueur.pseudo) &&
                Objects.equals(mdp, joueur.mdp);
    }

    /**
     * Méthode toString
     * ELle permet de rassembler toutes les informations du joueur
     * @return un string qui correspond aux informations du joueur
     */
    @Override
    public String toString() {
        return pseudo + "," + mdp + "," + partieWin + "," + partieLoos + "\n";
    }


    /**
     * Méthode qui renvoie la liste des joueurs enregistrés dans joueurs.txt
     * @return la liste des joueurs enregistrés
     */
    public static List<Joueur> listEnregistrement() {
        String line;
        List<Joueur> lesjoueurs = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("ressource"+File.separator+"joueurs.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String[] parts;

            while((line = bufferedReader.readLine()) != null && !line.isBlank()) {
                parts = line.split(",");
                lesjoueurs.add(new Joueur(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3])));
            }
            bufferedReader.close();
        } catch (IOException err ) {
            err.getMessage();
        }
        return lesjoueurs;
    }


    /**
     * Méthode getWinrate
     * Elle récupère le nombre de partie gagnés et perdue par le joueur pour calculer le winrate du joueur
     * @return float qui correspond au pourcentage de partie gagné du joueur
     */
    public float getWinrate() {
        if (partieWin == 0 && partieLoos == 0)
            return 0;
        if (partieWin == 0)
            return 0;
        if (partieLoos == 0)
            return 1;
        return (float) partieWin/(partieWin+partieLoos);
    }

    /**
     * Méthode getClassement
     * Elle permet de renvoyer le classement du joueur 
     * @return rank   int qui correspond à la position du joueur dans le classement 
     */
    public String getClassement() {
        List<Joueur> list = Joueur.listEnregistrement();
        list.sort(new ComparateurWinrate());
        return list.indexOf(this)+1 + "/" + list.size();
    }

}
