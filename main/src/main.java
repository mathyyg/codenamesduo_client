import code.Joueur;
import codenames.*;
import codenames.exceptions.*;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws CnNetworkException, IOException {
        CodeNamesClient codenameClient = new CodeNamesClient("http://51.178.49.138:3000/api/v0");
/*
        State etat = null;
        joueur j1 = new joueur("Paul8");
        int id = -1;
        try {
            j1.setMdp(codenameClient.addPlayer(j1));
        } catch (CnBadLoginException e) {
            e.printStackTrace();
        }

        System.out.println(j1.getMdp());

        try {
            id = codenameClient.createGame(j1);
        } catch (CnBadLoginException e) {
            e.printStackTrace();
        }
        try {
            etat = codenameClient.consultGame(id);
        } catch (CnBadIdException e) {
                e.printStackTrace();
        }

        System.out.println(etat.state() + "\n" + etat.creator() + "\n");

        joueur j2 = new joueur("Thomas4");

        try {
            codenameClient.addPlayer(j2);
            codenameClient.joinGame(id, j2);
        } catch (CnBadIdException e) {
            e.printStackTrace();
        } catch (CnBadLoginException e) {
            e.printStackTrace();
        } catch (CnFullPlayersException e) {
            e.printStackTrace();
        }

        try {
            etat = codenameClient.consultGame(id);
        } catch (CnBadIdException e) {
            e.printStackTrace();
        }
        System.out.println(etat.state() + "\n" + etat.creator());

*/
        Joueur j1 = new Joueur("Paul", "mdp1");
        j1.enregistrement();
        Joueur j2 = j1;
        j2.enregistrement();
        Joueur j3 = new Joueur("Paul", "mdp2");
        j3.enregistrement();
    }
}

