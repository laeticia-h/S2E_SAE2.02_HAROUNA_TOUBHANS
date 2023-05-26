package test;

import org.junit.jupiter.api.*;
import ressources.BellmanFord;
import ressources.GrapheListe;
import ressources.Valeur;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BellmanTest {
    @Test
    public void testPointFixe() {
        GrapheListe graphe = new GrapheListe();
        graphe.ajouterArc("D", "C", 10);
        graphe.ajouterArc("A", "B", 12);
        graphe.ajouterArc("D", "B", 23);
        graphe.ajouterArc("A", "D", 87);
        graphe.ajouterArc("E", "D", 43);
        graphe.ajouterArc("B", "E", 11);
        graphe.ajouterArc("C", "A", 19);

        // Appliquer l'algorithme du point fixe
        BellmanFord bellmanFord = new BellmanFord();
        Valeur result = bellmanFord.resoudre(graphe, "A");

        // Vérification des valeurs de distance calculées
        assertEquals(0, result.getValeur("A"));  // Distance de A (devrait être 0)
        assertEquals(12.0, result.getValeur("B"));  // Distance de B (devrait être 12)
        assertEquals(76.0, result.getValeur("C"));  // Distance de C (devrait être 76)
        assertEquals(66.0, result.getValeur("D"));  // Distance de D (devrait être 66)
        assertEquals(23, result.getValeur("E"));  // Distance de E (devrait être 23)
    }
}

