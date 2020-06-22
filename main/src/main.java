import code.joueur;
import codenames.*;
import codenames.states.*;
import codenames.exceptions.*;

public class main {
    public static void main(String[] args) throws CnNetworkException {
        CodeNamesClient codenameClient = new CodeNamesClient("http://51.178.49.138:3000/api/v0");

        State etat = null;
        joueur j1 = new joueur("Paul3");
        String mdp1 = null;
        int id = -1;
        try {
            mdp1 = codenameClient.addPlayer(j1);
        } catch (CnBadLoginException e) {
            e.printStackTrace();
        }

        System.out.println(mdp1);

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

        System.out.println(etat);
    }
}

