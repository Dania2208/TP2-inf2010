import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Tu peux tester tes différentes classes ici :
        //LinearProbingHashTable<Integer> table = new LinearProbingHashTable<>();
        //QuadraticProbingHashTable<Integer> table = new QuadraticProbingHashTable<>();
        // DoubleHashingHashTable<Integer> table = new DoubleHashingHashTable<>();
        // SeparateChainingHashTable<Integer> table = new SeparateChainingHashTable<>();

        // Exemple avec LinearProbingHashTable (à adapter pour chaque table)
        HashTable<Integer> table = new SeparateChainingHashTable<>();
        // Définis tes tailles d'éléments (conformes au tableau demandé)
        int[] taillesElements = {100, 200, 300, 1000, 2000, 3000, 10000, 20000, 30000, 100000};

        System.out.println("Éléments ajoutés\tTaille totale\tRehash\tTemps (ns)\tFacteur de charge\tCollisions");
        System.out.println("--------------------------------------------------------------------------------------------------");

        Random rand = new Random(42); // Graine fixe pour avoir les mêmes insertions partout

        for (int n : taillesElements) {
            // Réinitialiser la table pour chaque test
            table.makeEmpty();
            
            // Mesure du temps total d'insertion
            long startTime = System.nanoTime();
            
            for (int i = 0; i < n; i++) {
                table.insert(rand.nextInt(1_000_000));
            }
            
            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;

            // Récupération des métriques instrumentées
            long tailleTotale = table.tableLength();
            long rehash = table.rehashCount();
            double facteurCharge = table.loadFactor();
            long collisions = table.collisionCount();

            // Affichage de la ligne pour le tableau
            System.out.println(n + "\t\t\t" + tailleTotale + "\t\t" + rehash + "\t" + totalTime + "\t" + String.format("%.4f", facteurCharge) + "\t\t" + collisions);
        }
    }
}