package ressources;

/**
 * Classe permettant de résoudre le problème du plus court chemin d'un graphe.
 */

public class BellmanFord implements Algorithme{
    /**
     * Méthode permettant de résoudre le problème du plus court chemin d'un graphe.
     * @param g Graphe sur lequel calculer le chemin le plus court
     * @param depart Nom du noeud de départ
     * @return Retourne un objet valeur complet comportant les données de résultat de l'algorithme de BellmanFord.
     */
    public Valeur resoudre(Graphe g, String depart)  {
        Valeur valeurs = new Valeur();

        // Initialisation on met toutes les valeurs à +infini sauf le départ à 0
        valeurs.setValeur(depart, 0);
        for (String noeud : g.listeNoeuds()) {
            if (!noeud.equals(depart)) {
                valeurs.setValeur(noeud, Double.MAX_VALUE);
            }
        }


        // Étapes de l'algorithme
        //On cree une variable pointFixe qui nous permettra de savoir si on a fini l'algorithme.

        boolean pointFixe = false;
        // iteration sert pour connaitre le nombre d'iteration total de l'algorithme lors de son execution.
        int iteration = 1;
        // Tant que le point fixe n'est pas atteint on continue l'algorithme.
        while (!pointFixe) {
            pointFixe = true;
            // Pour chaque noeud du graphe on regarde ses successeurs.
            for (String noeud : g.listeNoeuds()) {
                for (Arc arc : g.suivants(noeud)) {
                    String destination = arc.getDestination();
                    // On regarde si la valeur actuelle est plus grande que la nouvelle valeur.
                    double valActuel = valeurs.getValeur(destination);
                    double nouvelleVal = valeurs.getValeur(noeud) + arc.getCout();

                    if (nouvelleVal < valActuel) {
                        // Mets à jour la valeur uniquement si la nouvelle valeur est inférieure
                        valeurs.setValeur(destination, nouvelleVal);
                        valeurs.setParent(destination, noeud);
                        pointFixe = false;
                    }
                }
            }
            iteration++;
        }
        System.out.println("Nombre d'itération de l'algorithme de BellmanFord : "+iteration);
        //On retourne l'objet valeur complet.
        return valeurs;
    }



}
