import modele.Joueur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test de la classe Joueur
 *
 * @author Paul Vernin, Thomas Peray, Matéo Esteves, Mathys Gagner
 * @version 4.6
 */
public class testJoueur {

        Joueur joueurTest;

        @BeforeEach
        void setUp() {
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
