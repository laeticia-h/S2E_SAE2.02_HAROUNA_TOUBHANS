package main;
import ressources.Dijkstra;
import ressources.GrapheListe;
import ressources.Valeur;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;

public class MainDijkstra{


    public static void main(String[] args) {
        String fichierGraph = "./Graphes/Graphe903.txt";
        String depart = "1";
        String destination = "10";

        GrapheListe graphe = new GrapheListe(fichierGraph);
        System.out.println(graphe);
        Dijkstra d = new Dijkstra();

        long debut = System.nanoTime();
        Valeur v = d.resoudre(graphe, depart);
        long fin = System.nanoTime();
        System.out.println(v.calculerChemin(destination));

        long tempsExecution = fin - debut;

        // Enregistrement des données dans le fichier CSV
        String fichierCSV = "resultatsDijkstra.csv";

        // Vérifier si le fichier CSV existe déjà
        boolean fichierExiste = Files.exists(Path.of(fichierCSV));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichierCSV, true))) {
            // Ajouter les noms des colonnes si le fichier n'existe pas
            if (!fichierExiste) {
                writer.write("NomFichier,Chemin,Temps\n");
            }

            // Écrire les données
            String ligne = String.format("%s,%s-%s,%d%n", fichierGraph, depart, destination, tempsExecution);
            writer.write(ligne);

            System.out.println("Données ajoutées avec succès au fichier CSV : " + fichierCSV);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}