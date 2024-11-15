package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ressources.Dijkstra;
import ressources.GrapheListe;
import ressources.Valeur;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DijkstraTest {
    /**
     * Classe de tests destinee a verifier si l'algorithme de Dijkstra code est operationnel
     */

    GrapheListe g1;

    @BeforeEach
    public void initGrapheExemple()
    {
        g1 = new GrapheListe();
        g1.ajouterArc("A", "B", 12);
        g1.ajouterArc("A", "D", 87);
        g1.ajouterArc("B", "E", 11);
        g1.ajouterArc("C", "A", 19);
        g1.ajouterArc("D", "B", 23);
        g1.ajouterArc("D", "C", 10);
        g1.ajouterArc("E", "D", 43);
    }

    /**
     * Test verifiant si l algorithme trouve bien le chemin minimal dans un graphe
     * Valeur v cree correspond aux L(X) calcules par l utilisation de l algorithme de Dijkstra
     */

    @Test
    public void resoudre()
    {
        // initialisation
        Valeur v=new Valeur();
        v.setValeur("A", 0);
        v.setValeur("B", 12);
        v.setParent("B", "A");
        v.setValeur("C", 76);
        v.setParent("C", "D");
        v.setValeur("D", 66);
        v.setParent("D", "E");
        v.setValeur("E", 23);
        v.setParent("E", "B");

        // Appel de la methode
        Dijkstra dj = new Dijkstra();
        Valeur test= dj.resoudre(g1, "A");

        // Test de la methode
        assertEquals(v.getValeur("A"), test.getValeur("A"), "Les valeurs doivent etre identiques");
        assertEquals(v.getValeur("B"), test.getValeur("B"), "Les valeurs doivent etre identiques");
        assertEquals(v.getValeur("C"), test.getValeur("C"), "Les valeurs doivent etre identiques");
        assertEquals(v.getValeur("D"), test.getValeur("D"), "Les valeurs doivent etre identiques");
        assertEquals(v.getValeur("E"), test.getValeur("E"), "Les valeurs doivent etre identiques");

        assertEquals(v.getParent("B"), test.getParent("B"), "Les parents doivent etre identiques");
        assertEquals(v.getParent("C"), test.getParent("C"), "Les parents doivent etre identiques");
        assertEquals(v.getParent("D"), test.getParent("D"), "Les parents doivent etre identiques");
        assertEquals(v.getParent("E"), test.getParent("E"), "Les parents doivent etre identiques");
    }


}