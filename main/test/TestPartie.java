
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import code.JoueurException;
import codenames.CodeNamesClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import code.*;
import codenames.cards.*;

public class TestPartie {

    private Partie partieTest;
    private CodeNamesClient serv = new CodeNamesClient("http://51.178.49.138:3000/api/v0", true);
    private Joueur j = new Joueur("joueurTest","p234058");
    @BeforeEach
    void setUp() throws Exception {
        partieTest = new Partie(serv, 9, j, serv.createGame(j));
    }


    @Test
    public void testUpdatePlateau() {
        List<Card> Answer = new ArrayList<>();
        Card c1,c2,c3;
        Answer.add(c1 = new Card("THÉ", CARD_ROLE.KILLER));
        Answer.add(c2 = new Card("PUCE", CARD_ROLE.NEUTRAL));
        Answer.add(c3 = new Card("SAHARA", CARD_ROLE.NEUTRAL));

        partieTest.plateauMAJ(Answer);
        List<Card> l = partieTest.getPlateau();

        assertEquals(c1.word(), l.get(0).word());
        assertEquals(c1.cardRole(), l.get(0).cardRole());
        assertEquals(c1.word(), l.get(1).word());
        assertEquals(c1.cardRole(), l.get(1).cardRole());
        assertEquals(c1.word(), l.get(2).word());
        assertEquals(c1.cardRole(), l.get(2).cardRole());
    }


}
