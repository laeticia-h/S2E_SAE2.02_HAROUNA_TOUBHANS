package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ressources.Arc;
import ressources.GrapheListe;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrapheListeTest {
    /**
     * Classe de tests de la classe GrapheListe verifiant ses differentes methodes
     */

    GrapheListe g1;

    public void initGrapheExemple() {
        g1.ajouterArc("A", "B", 12);
        g1.ajouterArc("A", "D", 87);
        g1.ajouterArc("B", "E", 11);
        g1.ajouterArc("C", "A", 19);
        g1.ajouterArc("D", "B", 23);
        g1.ajouterArc("D", "C", 10);
        g1.ajouterArc("E", "D", 43);
    }

    /**
     * Initialise l'objet GrapheListe avant chaque test
     */
    @BeforeEach
    public void init() {
        g1 = new GrapheListe();
    }

    /**
     * Verifie que la methode listeNoeuds retourne bien la liste de noeuds d'un graphe
     */

    @Test
    public void listeNoeuds_ok() {
        //Initialisation
        g1.setNom("A");
        g1.setNom("B");

        List<String> comp = new ArrayList<>();
        comp.add("A");
        comp.add("B");

        //Methode
        List<String> res = g1.listeNoeuds();

        //Test
        assertEquals(comp, res, "doivent etre egaux");
    }

    /**
     * Verifie qu'un graphe est vide
     */

    @Test
    void grapheListVide_ok() {
        assertNotNull(g1.listeNoeuds());
        assertNotNull(g1.getEnsNoeuds());
    }

    /**
     * Vérifie que la méthode suivants renvoie les arcs corrects pour un nœud donné
     */
    @Test
    public void suivants_ok() {
        // Ajout des noeuds et arcs nécessaires pour le test
        g1.ajouterArc("A", "B", 12);
        g1.ajouterArc("A", "D", 87);

        // Méthode
        List<Arc> arcs = g1.suivants("A");

        // Vérification
        assertNotNull(arcs, "La liste des arcs ne doit pas être nulle");
        assertEquals(2, arcs.size(), "La liste doit contenir 2 arcs");

        Arc arc1 = arcs.get(0);
        assertEquals("B", arc1.getDestination(), "Le premier arc doit avoir pour destination B");
        assertEquals(12, arc1.getCout(), "Le premier arc doit avoir un coût de 12");

        Arc arc2 = arcs.get(1);
        assertEquals("D", arc2.getDestination(), "Le deuxième arc doit avoir pour destination D");
        assertEquals(87, arc2.getCout(), "Le deuxième arc doit avoir un coût de 87");
    }
}
