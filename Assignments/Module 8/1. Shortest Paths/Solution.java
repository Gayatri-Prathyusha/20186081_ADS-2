import java.util.Scanner;
import java.util.HashMap;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        // unused Constructor.
    }
    /**
     * {Client Program}.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] tokens = sc.nextLine().split(" ");
        int nameOfStations = Integer.parseInt(tokens[0]);
        int m = Integer.parseInt(tokens[1]);
        String[] check = sc.nextLine().split(" ");
        EdgeWeightedGraph edgeWeightedGraph =
        new EdgeWeightedGraph(nameOfStations);
        HashMap<String, Integer> hash = new HashMap<String, Integer>();
        for (int i = 0; i < check.length; i++) {
            hash.put(check[i], i);
        }
        while (m > 0) {
            String[] line = sc.nextLine().split(" ");
            edgeWeightedGraph.addEdge(new Edge(
                            hash.get(line[0]), hash.get(line[1]),
                            Double.parseDouble(line[2])));
            m--;
        }
        int queries = Integer.parseInt(sc.nextLine());
        while (queries > 0) {
            String[] check1 = sc.nextLine().split(" ");
            int a = hash.get(check1[0]);
            DijkstraUndirectedSP dijkstra =
            new DijkstraUndirectedSP(edgeWeightedGraph, a);
            if (dijkstra.hasPathTo(
                hash.get(check1[1]))) {
                System.out.println((int)
                    dijkstra.distTo(hash.get(check1[1])));
            }
            queries--;
        }
    }
}





