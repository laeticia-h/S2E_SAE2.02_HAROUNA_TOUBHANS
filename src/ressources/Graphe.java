package ressources;

import java.util.ArrayList;
import java.util.*;

/**
 * Interface Graphe permettant de definir les methodes necessaires a la resolution de l'algorithme de Dijkstra
 */
public interface Graphe{

    /**
     * Retourne tous les noeuds du graphe
     * @return liste des noeuds
     */
    public ArrayList<String> listeNoeuds();

    /**
     * Retourne la liste des arcs du noeud
     * @param n nom du noeud
     * @return liste des noeuds suivants
     */
    public ArrayList<Arc> suivants(String n);

}