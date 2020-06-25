
import static org.junit.jupiter.api.Assertions.*;

import code.Indice;
import code.Partie;
import codenames.CodeNamesClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import code.Joueur;

public class testIndice {

    Indice indiceTest;
    CodeNamesClient serv;
    Partie p;
    @BeforeEach
    void setUp() {
        serv = new CodeNamesClient("http://51.178.49.138:3000/api/v0");
        p = new Partie(serv,9,new Joueur("a","a"),3);
        indiceTest = new Indice("Test",3);
    }

    @Test
    public void estValide() {
        indiceTest = new Indice("mot",3);
        assertEquals(true, indiceTest.estValide(p));
    }

    @Test
    public void estValide2() {
        indiceTest = new Indice("mot-composé",3);
        assertEquals(true, indiceTest.estValide(p));
    }

    @Test
    public void estValide3() {
        indiceTest = new Indice("MOT",3);
        assertEquals(true, indiceTest.estValide(p));
    }

    @Test
    public void estValide4() {
        indiceTest = new Indice("MOT-COMPOSE",3);
        assertEquals(true, indiceTest.estValide(p));
    }

    @Test
    public void estValide5() {
        indiceTest = new Indice("MOT-COMPOSÉ",3);
        assertEquals(true, indiceTest.estValide(p));
    }

    @Test
    public void estValide6() {
        indiceTest = new Indice("MOT-----COMPOSE",3);
        assertEquals(false, indiceTest.estValide(p));
    }

    @Test
    public void estValide7() {
        indiceTest = new Indice("MOT COMPOSE",3);
        assertEquals(false, indiceTest.estValide(p));
    }
}
