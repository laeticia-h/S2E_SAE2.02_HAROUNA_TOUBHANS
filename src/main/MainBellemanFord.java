package main;
import ressources.BellmanFord;
import ressources.GrapheListe;
import ressources.Valeur;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MainBellemanFord {
        public static void main(String[] args) {
                String fichierGraph = "./Graphes/Graphe903.txt";
                String depart = "1";
                String destination = "10";

                GrapheListe graphe = new GrapheListe(fichierGraph);
                System.out.println(graphe);
                BellmanFord bf = new BellmanFord();

                long debut = System.nanoTime();
                Valeur v = bf.resoudre(graphe, depart);
                long fin = System.nanoTime();
                List<String> chemin = v.calculerChemin(destination);
                System.out.println(chemin);

                long tempsExecution = fin - debut;

                // Enregistrement des données dans le fichier CSV
                String fichierCSV = "resultatsBellmanFord.csv";

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