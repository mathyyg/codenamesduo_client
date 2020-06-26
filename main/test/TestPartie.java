import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import codenames.CodeNamesClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modele.*;

/**
 * Classe de test de la classe Partie
 */
public class TestPartie {

    private Partie partieTest;

    @BeforeEach
    void setUp() throws Exception {
        CodeNamesClient serv = new CodeNamesClient("http://51.178.49.138:3000/api/v0", true);
        Joueur j = new Joueur("joueurTest", "p234058");
        partieTest = new Partie(serv, 9, j, serv.createGame(j));
    }


    @Test
    public void testUpdatePlateau() {
        List<Carte> Answer = new ArrayList<>();
        Carte c1, c2, c3;
        Answer.add(c1 = new Carte("THÉ", TYPE_CARTE.ASSASSIN));
        Answer.add(c2 = new Carte("PUCE", TYPE_CARTE.NEUTRAL));
        Answer.add(c3 = new Carte("SAHARA", TYPE_CARTE.NEUTRAL));

        partieTest.updatePlateau(Answer);
        List<Carte> l = partieTest.getPlateau();

        assertEquals(c1.getMot(), l.get(0).getMot());
        assertEquals(c1.getType(), l.get(0).getType());
        assertEquals(c2.getMot(), l.get(1).getMot());
        assertEquals(c2.getType(), l.get(1).getType());
        assertEquals(c3.getMot(), l.get(2).getMot());
        assertEquals(c3.getType(), l.get(2).getType());
    }

}
