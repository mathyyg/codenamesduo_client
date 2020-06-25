
import static org.junit.jupiter.api.Assertions.*;

import code.Indice;
import code.Partie;
import codenames.CodeNamesClient;
import codenames.exceptions.CnBadLoginException;
import codenames.exceptions.CnNetworkException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import code.Joueur;

public class testIndice {

    Indice indiceTest;
    CodeNamesClient serv;
    Partie p;
    Joueur j = new Joueur("JTEST","p77231");

    /* Les mots sont :
    * "THÉ", "PUCE", "SAHARA", "SAPIN", "MARIAGE",
    * "BACON", "ADRESSE", "OREILLER", "MOSCOU", "MODÈLE",
    * "CERVEAU", "RIZ", "MATIÈRE", "HERCULE", "INDEX",
    * "HAWAÏ", "SAMOURAÏ", "SKI", "TRONC", "PYTHON",
    * "CAMÉLÉON", "STATION", "NOYAU", "GOÛT", "VACHE"
    */
    @BeforeEach
    void setUp() {
        serv = new CodeNamesClient("http://51.178.49.138:3000/api/v0", true);
        try {
            p = new Partie(serv, 9, j, serv.createGame(j));
        } catch (CnNetworkException e) {
            System.err.println("Problème serveur : createGame");
        } catch (CnBadLoginException e) {
            System.err.println("Problème login : createGame");
        }
    }

    @Test
    public void estValide0() {
        indiceTest = new Indice("THÉ",3);
        assertEquals(false, indiceTest.estValide(p));
    }
    @Test
    public void estValide1() {
        indiceTest = new Indice("THE",3);
        assertEquals(true, indiceTest.estValide(p));
    }
    @Test
    public void estValide2() {
        indiceTest = new Indice("thé",3);
        assertEquals(false, indiceTest.estValide(p));
    }

    @Test
    public void estValide3() {
        indiceTest = new Indice("oreille",3);
        assertEquals(false, indiceTest.estValide(p));
    }
    @Test
    public void estValide4() {
        indiceTest = new Indice("GOÛTER",3);
        assertEquals(false, indiceTest.estValide(p));
    }
    @Test
    public void estValide5() {
        indiceTest = new Indice("goûter",3);
        assertEquals(false, indiceTest.estValide(p));
    }

    @Test
    public void estValide6() {
        indiceTest = new Indice("plusieurs mots",3);
        assertEquals(false, indiceTest.estValide(p));
    }

    @Test
    public void estValide7() {
        indiceTest = new Indice("mot-composé",3);
        assertEquals(true, indiceTest.estValide(p));
    }

    @Test
    public void estValide8() {
        indiceTest = new Indice("mot-trop-composé",3);
        assertEquals(false, indiceTest.estValide(p));
    }

    @Test
    public void estValide9() {
        indiceTest = new Indice("mot--------composé",3);
        assertEquals(false, indiceTest.estValide(p));
    }

    @Test
    public void estValide10() {
        indiceTest = new Indice("",3);
        assertEquals(false, indiceTest.estValide(p));
    }


}

