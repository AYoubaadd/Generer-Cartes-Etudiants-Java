import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class fichier {

    // Chemin complet vers le fichier
    String fich = "C:\\Users\\HP\\Desktop\\TpJava";

    public void setAnneeUni(String annee) {
        try {
            // Créer un objet File représentant le fichier
            File fi = new File(this.fich,"fichier.txt");

            // Vérifie si le fichier n'existe pas, et le crée si nécessaire
            if (!fi.exists()) {
                fi.getParentFile().mkdirs(); // Crée les répertoires parents si nécessaires
                fi.createNewFile();
            }

            // Écrire dans le fichier
            try (Writer w = new FileWriter(fi)) {
                w.write(annee);
            }
        } catch (IOException e) {
            System.err.println("Erreur: " + e.getMessage());
        }
    }

    public String afficherAnne() {
        String ligne = null;
        try (BufferedReader lir = new BufferedReader(new FileReader(fich+"//fichier.txt"))) {
            ligne = lir.readLine();
        } catch (IOException e) {
            System.err.println("Erreur: " + e.getMessage());
        }
        return ligne;
    }
}
