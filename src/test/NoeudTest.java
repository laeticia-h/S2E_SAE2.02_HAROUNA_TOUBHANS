package test;

import org.junit.jupiter.api.Test;
import ressources.Noeud;

import static org.junit.jupiter.api.Assertions.*;

public class NoeudTest {
    /**
     * Classe de tests de la classe Noeud
     */

    Noeud n1, n2;

    /**
     * Verifie que deux noeuds soient egaux car de meme nom
     */

    @Test
    public void memeNom() {
        //Initialisation
        n1 = new Noeud("Noeud1");
        n2 = new Noeud("Noeud1");

        //Method
        boolean res = n1.equals(n2);

        //Test
        assertTrue(res, "Noeuds doivent etre egaux car de meme nom");
    }

    /**
     * Verifie que deux noeuds ne soient pas egaux car noms differents
     */

    @Test
    public void nomDifferent() {
        //Initialisation
        n1 = new Noeud("Noeud1");
        n2 = new Noeud("Noeud2");

        //Method
        boolean res = n1.equals(n2);

        //Test
        assertFalse(res, "Noeuds ne doivent pas etre egaux car pas le meme nom");
    }

    /**
     * Verifie qu un arc a bel et bien ete ajoute au noeud
     */

    @Test
    public void ajouterArc_ok() {
        //Initialisation
        n1 = new Noeud("Noeud1");

        //Method
        n1.ajouterArc("Noeud2", 3);

        // Test
        assertEquals(n1.getArc().get(0).getCout(), 3, "le cout de l arc doit etre de 3");
        assertEquals(n1.getArc().get(0).getDestination(), "Noeud2", "le noeud de destination doit etre Noeud2");

    }

    /**
     * Verifie que la methode getNom retourne bien le bon nom
     */

    @Test
    public void getNom_ok() {
        //Initialisation
        n1 = new Noeud("Noeud1");

        String res = n1.getNom();

        //Test
        assertEquals("Noeud1", res, "doit etre la meme chaine");

    }

    /**
     * Vérifie qu'un noeud ne contient aucun arc initialement
     */
    @Test
    public void aucunArcInitialement() {
        // Initialisation
        Noeud n1 = new Noeud("Noeud1");

        // Test
        assertTrue(n1.getArc().isEmpty(), "Le noeud ne doit contenir aucun arc");
    }

    /**
     * Vérifie qu'un arc est correctement supprimé du noeud
     */
    @Test
    public void supprimerArc_ok() {
        // Initialisation
        Noeud n1 = new Noeud("Noeud1");
        n1.ajouterArc("Noeud2", 3);
        n1.ajouterArc("Noeud3", 5);
        int tailleActuel = n1.getArc().size();

        // Suppression de l'arc
        n1.getArc().remove(0);

        // Test
        assertEquals(tailleActuel - 1, n1.getArc().size(), "Le nombre d'arcs doit être réduit de 1");
    }
}