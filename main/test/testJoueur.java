
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import code.Joueur;

    public class testJoueur {

        Joueur joueurTest;

        @BeforeEach
        void setUp() throws Exception {
            joueurTest = new Joueur("Test");
        }

        @Test
        public void testWinrate() {
            assertEquals(0, joueurTest.getWinrate());
            joueurTest.PartieWinUp();
            assertEquals(1, joueurTest.getWinrate());
            joueurTest.PartieLoosUp();
            assertEquals(0.5, joueurTest.getWinrate());
        }

        @Test
        public void testUpdateJoueur() {
            joueurTest.enregistrement();
            joueurTest.PartieWinUp();
            assertEquals(1, joueurTest.getPWin());
        }
}
