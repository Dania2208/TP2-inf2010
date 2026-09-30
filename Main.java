import java.util.Random;

public class Main {

    // Définis tes tailles d'éléments (conformes au tableau demandé)
    static final int[] TAILLES = { 100, 300, 500, 700, 900, 1000, 3000, 5000, 7000, 9000, 
        10000, 30000, 50000, 70000, 90000, 100000, 300000, 500000, 700000, 900000,
        1000000, 3000000, 5000000, 7000000, 9000000, 10000000 }; 

    static final Random RAND = new Random(42); // Graine fixe : mêmes nombres tirés à chaque exécution

    public static void main(String[] args) {

        System.out.println("Éléments ajoutés\tTaille totale\tRehash\tTemps (ns)\tFacteur de charge\tCollisions");

        for (int n : TAILLES) {
            HashTable<Integer> table = new SeparateChainingHashTable<>();
            
            // Tu peux tester tes différentes classes ici :
            //LinearProbingHashTable<Integer> table = new LinearProbingHashTable<>();
            //QuadraticProbingHashTable<Integer> table = new QuadraticProbingHashTable<>();
            // DoubleHashingHashTable<Integer> table = new DoubleHashingHashTable<>();
            // SeparateChainingHashTable<Integer> table = new SeparateChainingHashTable<>();
            
            // Mesure du temps total d'insertion
            long startTime = System.nanoTime();
            
            while (table.size() < n) {
                table.insert(RAND.nextInt(Integer.MAX_VALUE));
            }
            
            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;

            // Récupération des métriques instrumentées
            long tailleTotale = table.tableLength();
            long rehash = table.rehashCount();
            double facteurCharge = table.loadFactor();
            long collisions = table.collisionCount();

            // Affichage de la ligne pour le tableau
            System.out.println(n + "\t" + tailleTotale + "\t" + rehash + "\t" + totalTime + "\t" + String.format("%.4f", facteurCharge) + "\t" + collisions);
        }
    }
}