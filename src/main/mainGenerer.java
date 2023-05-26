package main;

import ressources.GrapheListe;

public class mainGenerer {
    public static void main(String[] args) {
        GrapheListe g = new GrapheListe();
        g.genererGraphe(10);
        System.out.println(g);
        System.out.println(g.toGraphViz());
    }
}