/**
 * @author Les Infopotes
 * @version 4
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modele.Joueur;

    public class testJoueur {

        Joueur joueurTest;

        @BeforeEach
        void setUp() throws Exception {
            joueurTest = new Joueur("Test");
        }

        @Test
        public void testWinrate() {
            assertEquals(0, joueurTest.getWinrate());
            joueurTest.partieWinUp();
            assertEquals(1, joueurTest.getWinrate());
            joueurTest.partieLoosUp();
            assertEquals(0.5, joueurTest.getWinrate());
        }

        @Test
        public void testUpdateJoueur() {
            joueurTest.enregistrement();
            joueurTest.partieWinUp();
            assertEquals(1, joueurTest.getPWin());
        }
}
