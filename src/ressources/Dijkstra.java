package ressources;

import java.util.ArrayList;

/**
 * Class comportant les méthodes de l'algorithme de Dijkstra.
 */
public class Dijkstra implements Algorithme{

/*
Entrees :
- G un graphe oriente avec une ponderation (poids) positive des arcs
- A un sommet (depart) de G
- Debut
- Q <- {} // utilisation d’une liste de noeuds a traiter
- Pour chaque sommet v de G faire
- v.distance <- Infini
- v.parent <- Indefini
- Q <- Q U {v} // ajouter le sommet v a la liste Q
- Fin Pour
- A.distance <- 0
- Tant que Q est un ensemble non vide faire
- u <- un sommet de Q telle que u.distance est minimale
- Q <- Q \ {u} // enlever le sommet u de la liste Q
- Pour chaque sommet v de Q tel que l’arc (u,v) existe faire
- D <- u.distance + poids(u,v)
- Si D < v.distance
- Alors v.distance <- D
- v.parent <- u
- Fin Si
- Fin Pour
- Fin Tant que
Fin
*/

    /**
     * Constructeur vide de Dijkstra
     */
    public Dijkstra(){}

    /**
     * Méthode permettant de résoudre le chemin le plus court d'un graphe en initialisant un objet Valeur comportant les données nécessaires.
     * @param g Graphe sur lequel calculer le chemin le plus court
     * @param depart Nom du noeud de départ
     * @return Retourne un objet valeur complet comportant les données de résultat de l'algorithme de dijkstra.
     */
    public Valeur resoudre(Graphe g, String depart) {

        Valeur val = new Valeur();
        ArrayList<String> q = new ArrayList<>();

        //On récupère les différents noeuds du graphe en initialisant leurs valeurs et parents.
        for (String v : g.listeNoeuds()) {
            val.setValeur(v, Double.MAX_VALUE);
            val.setParent(v, null);
            q.add(v);
        }

        //Le noeud de départ vaut 0.
        val.setValeur(depart, 0);

        int iteration = 1;
        //tant que la liste de noeud n'est pas vide (toutes les possibilités explorées).
        while (!q.isEmpty()) {

            //On récupère le noeud possèdant le parcours au coût "minimum".
            String u = noeudMin(val, q);

            //On enlève ce noeud de la liste car nous allons explorer ses arcs donc plus utile par la suite.
            q.remove(u);

            //On parcourt les arcs de ce noeud.
            for (Arc arc : g.suivants(u)) {

                String v = arc.getDestination();
                double d = val.getValeur(u) + arc.getCout();

                //On vérifie si il existe un chemin plus court et si oui on remplace le chemin actuel par le plus court.
                if (d < val.getValeur(v)) {
                    val.setValeur(v, d);
                    val.setParent(v, u);;
                }
            }
            iteration++;
        }

        System.out.println("Nombre d'itération de l'algorithme de Dijkstra: "+iteration);
        //On retourne l'objet valeur complet.
        return val;
    }

    /**
     * Méthode qui permet de calculer le noeud au coût de parcours minimum dans une Liste de nom de noeud et un objet valeur.
     * @param val Objet valeur
     * @param q Liste de String correspondant à des nom de noeuds
     * @return String du nom du noeud au coût de parcours "minimum"
     */
    public String noeudMin(Valeur val, ArrayList<String> q) {

        String noeudmin = q.get(0);
        double valmin = val.getValeur(noeudmin);

        //Pour chaque noeud on vérifie la valeur de parcours, si un noeud en possède un plus petit il devient le noeudmin que l'on retournera.
        for (String noeud : q) {

            double valeur = val.getValeur(noeud);

            if (valeur < valmin) {
                valmin = valeur;
                noeudmin = noeud;

            }
        }
        //On retourne le noeud au coût de parcours mininmum.
        return noeudmin;
    }



}