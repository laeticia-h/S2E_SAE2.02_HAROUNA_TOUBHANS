package main;
import ressources.BellmanFord;
import ressources.Dijkstra;
import ressources.GrapheListe;
import ressources.Valeur;

import java.util.Scanner;

/**
 * Tests de performances avec graphes generes automatiquement
 */
import java.util.Scanner;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class mainGenereGraphe {
    private static GrapheListe g;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nombre de nœuds : ");
        int nbNoeuds = sc.nextInt();
        System.out.print("Nombre d'essais : ");
        int nbEssais = sc.nextInt();

        double tempsTotalBF = 0;
        double tempsTotalDj = 0;
        double ratioTotal = 0;

        for (int i = 1; i <= nbEssais; i++) {
            long geneTps = geneGraphe(nbNoeuds);

            // Bellman-Ford
            BellmanFord bF = new BellmanFord();
            long bFTps = calculTempsBF(bF);
            tempsTotalBF += bFTps;

            // Dijkstra
            Dijkstra dj = new Dijkstra();
            long djTps = calculTempsDj(dj);
            tempsTotalDj += djTps;

            double ratio = (double) djTps / bFTps;
            ratioTotal += ratio;

            System.out.println(nbNoeuds + "\t\t" + bFTps + "\t\t" + djTps + "\t\t" + ratio);
        }

        double tempsMoyenBF = tempsTotalBF / nbEssais;
        double tempsMoyenDj = tempsTotalDj / nbEssais;
        double ratioMoyen = ratioTotal / nbEssais;

        System.out.println("Temps moyen BF : " + tempsMoyenBF + " ns");
        System.out.println("Temps moyen Dj : " + tempsMoyenDj + " ns");
        System.out.println("Ratio moyen : " + ratioMoyen);
    }

    public static long geneGraphe(int taille) {
        long dateInit = System.nanoTime();

        g = new GrapheListe();
        g.genererGraphe(taille);

        // Pour que tous les noeuds soient connectés
        for (int i = 1; i < taille; i++) {
            String noeudActuel = String.valueOf(i);
            String noeudSuivant = String.valueOf(i + 1);
            g.ajouterArc(noeudActuel, noeudSuivant, 1);
        }

        long dateFin = System.nanoTime();

        return dateFin - dateInit;
    }

    public static long calculTempsBF(BellmanFord bF) {
        long debtTps = System.nanoTime();
        Valeur v = bF.resoudre(g, "1");
        long finTps = System.nanoTime();
        return finTps - debtTps;
    }

    public static long calculTempsDj(Dijkstra dj) {
        long debtTps = System.nanoTime();
        Valeur v = dj.resoudre(g, "1");
        long finTps = System.nanoTime();
        return finTps - debtTps;
    }
}
