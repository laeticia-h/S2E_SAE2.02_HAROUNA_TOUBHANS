package main;

import ressources.GrapheListe;

/**
 * Main afin de tester la classe GrapheListe et de l'exécuter.
 */
public class main {

    public static void main(String[] args){

        GrapheListe graphe = new GrapheListe();
        //Ajout des arc dans le graphe
        graphe.ajouterArc("A", "B", 12);
        graphe.ajouterArc("A", "D", 87);
        graphe.ajouterArc("B", "E", 11);
        graphe.ajouterArc("E", "D", 43);
        graphe.ajouterArc("D", "B", 23);
        graphe.ajouterArc("D", "C", 10);
        graphe.ajouterArc("C", "A", 19);

        //affichage du graphe au format normal et format GrapheViz
        System.out.println(graphe);
        System.out.println(graphe.toGraphViz()+"\n");

        //Test du constructeur à partir d'un nom de fichier.
        GrapheListe graphe2 = new GrapheListe("./Graphes/Graphe25.txt");
        //affichage du graphe au format normal et format GrapheViz
        System.out.println(graphe2);
        System.out.println(graphe2.toGraphViz());

        //Test de la méthode convertirMatriceEnListe
        GrapheListe.convertirMatriceEnListe("./Graphes/Matrice.txt", "./Graphes/ResultatMatrice.txt");
        GrapheListe graphe3 = new GrapheListe("./Graphes/ResultatMatrice.txt");
        //affichage du graphe au format normal et format GrapheViz
        System.out.println(graphe3);
        System.out.println(graphe3.toGraphViz());

    }
}


