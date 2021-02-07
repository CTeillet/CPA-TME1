import org.jetbrains.annotations.NotNull;

public class Main {
    public static void main(String @NotNull [] args) {

        long debut = System.currentTimeMillis();
        AdjArray le = new AdjArray(args[0]);
        long fin = System.currentTimeMillis();

        System.out.println("Temps Lecture fichier " + (fin-debut));

        System.out.println("Edges : " + le.getEdgesNumber());
        System.out.println("Nodes : " + le.getNodesNumber());

        //BFS
        debut = System.currentTimeMillis();
        System.out.println("Diametre " + le.getMaxDiameter());
        fin = System.currentTimeMillis();

        System.out.println("Temps calcul Diametre " + (fin-debut)/AdjArray.nbIterationDiametre);

        //Triangle
        /*le.getTriangles();
        System.out.println("Edges : " + le.getEdgesNumber());
        System.out.println("Nodes : " + le.getNodesNumber());*/

    }
}
