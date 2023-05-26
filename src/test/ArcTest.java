package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ressources.Arc;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test de la classe Arc
 */
public class ArcTest {

    /**
     * Objet de type arc
     */
    Arc a;

    /**
     * Initialise l'objet Arc avant chaque test
     */
    @BeforeEach
    public void init ()
    {
        a = new Arc(new String("A"), 3);
    }

    /**
     * Verifie l'initialisation des attributs de l'arc
     */
    @Test
    public void Arc_ok ()
    {
        assertNotNull(a.getDestination());
        assertTrue( a.getCout() >= 0, "doit etre positif");
    }

    /**
     * Verifie que getDest renvoie bien la valeur attendue
     */
    @Test
    public void getDestination()
    {
        assertEquals("A", a.getDestination().toString(), "doivent etre egaux à A");
    }

    /**
     * Verifie que getCout renvoie bien la valeur attendue
     */
    @Test
    public void getCout()
    {
        assertEquals(3, a.getCout(), "doivent etre egaux à 3");
    }

    /**
     * Verifie que cout ne peut pas être négatif et donc initialise automatiquement à 0
     */
    @Test
    public void coutNegatif() {
        a = new Arc("B", -5);
        assertEquals(0, a.getCout(), "Le coût doit être égal à 0");
    }

    /**
     * Verifie que cout peut être nul
     */
    @Test
    public void coutZero() {
        a = new Arc("B", 0);
        assertEquals(0, a.getCout(), "Le coût doit être égal à 0");
    }
}