package ressources;

import java.util.ArrayList;
import java.util.*;

/**
 * Classe Noeud permettant de creer un noeud
 */
public class Noeud {

    //attributs
    private String nom;
    private ArrayList<Arc> adj;

    /**
     * Constructeur de la classe Noeud
     *
     * @param n nom du noeud
     */
    public Noeud(String n) {
        nom = n;
        adj = new ArrayList<>();
    }

    /**
     * Retourne le nom du noeud
     *
     * @return nom du noeud
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Retourne la liste des arcs du noeud
     *
     * @return liste des arcs
     */
    public ArrayList<Arc> getArc() {
        return adj;
    }

    /**
     * Verifie que les deux noeuds sont egaux
     *
     * @param n noeud a comparer
     * @return true si les noeuds sont egaux, false sinon
     */
    public boolean equals(Noeud n) {
        return (this.nom.equals(n.getNom()));
    }

    /**
     * Ajoute un arc au noeud
     *
     * @param destination nom du noeud de destination
     * @param cout        cout de l'arc
     */

    public void ajouterArc(String destination, double cout) {
        adj.add(new Arc(destination, cout));
    }

}
