package main;
import laby.Labyrinthe;
import ressources.Dijkstra;
import ressources.GrapheLabyrinthe;
import ressources.GrapheListe;
import ressources.Valeur;

import java.io.IOException;

/**
 * Class main permettant de tester les différentes méthodes de Labyrinthe et de son adaptateur graphe GrapheLabyrinthe.
 */
public class MainLabyrinthe{

public static void main(String[] args) throws IOException {

    //Test de la transformation d'un labyrinthe en graphe.

    Labyrinthe laby = new Labyrinthe("./labySimple/laby1.txt");
    GrapheListe graphe = laby.genererGraphe();
    System.out.println(graphe);
    Dijkstra dij = new Dijkstra();
    Valeur v = dij.resoudre(graphe,"[1/1]");
    System.out.println(v.calculerChemin("[8/5]"));

    //Test de l'adaptateur GrapheLabyrinthe.

    GrapheLabyrinthe graphelaby = new GrapheLabyrinthe(laby);
    Valeur w = dij.resoudre(graphelaby, "[1/1]");
    System.out.println(w.calculerChemin("[8/5]"));
}

}