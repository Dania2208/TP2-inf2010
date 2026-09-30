/**
 * INF2010 - ASD
 * Table de dispersement avec resolution des collisions par
 * double hachage (Double Hashing Hash Table).
 * Ce code est basé sur Chapitre 5 de *Data Structures and Algorithms
 * Analysis in Java* (2e ed.) de Mark Allen Weiss, avec modifications
 * par Susanna Rumsey (2026).
 *
 */
public class DoubleHashingHashTable<AnyType> extends ProbingHashTable<AnyType> {
    /**
     * TODO: À remplir en utilisant hashage double ou f(i) = i*myhash(x).  Astuce : examinez le code pour la
     * methode findPos dans QuadraticProbingHashTable pour commencer.
     */

    private int R;

    protected int findPos(AnyType x) {
      int currentPos = x.hashCode() % array.length;
      int offset = myhash(x);
      if (currentPos < 0) {
        currentPos += array.length;
      }
        
      while((array)[ currentPos] != null && !array[currentPos].element.equals(x)) {
            currentPos += offset;
            collisionCounter++;

            if(currentPos>= array.length) {
                currentPos-=array.length;
            }      
        }
        return currentPos;
    }

    @Override
    protected void allocateArray(int arraySize) {
      super.allocateArray(arraySize);
      int length = array.length;
      R = nextPrime(MATRICULE % length);
      while (R >= length) {
        R -= length;
        R = nextPrime(R);
      }
    }
    
    @Override
    protected int myhash(AnyType x) {
      if (MATRICULE == 0) {
        throw new ArithmeticException("Entrez votre matricule dans DoubleHashingHashTable.java avant de proceder.");
      }
      return R - (x.hashCode() % R);
    }

    public static void main(String[] args) {
        DoubleHashingHashTable<Integer> table = new DoubleHashingHashTable<>();
        test(table);
    }
}
