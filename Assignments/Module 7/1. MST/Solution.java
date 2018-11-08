import java.util.Scanner;
/**
 * Class for solution.
 */
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //unused constructor.
    }
    /**
     * { Client program }.
     *
     * @param      args  The arguments.
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertices = Integer.parseInt(sc.nextLine());
        int edges = Integer.parseInt(sc.nextLine());
        EdgeWeightedGraph edgeWeightedGraph =
        new EdgeWeightedGraph(vertices);
        for (int i = 0; i < edges; i++) {
            String[] tokens = sc.nextLine().split(" ");
            Edge edge = new Edge(Integer.parseInt(tokens[0]),
                Integer.parseInt(tokens[1]), Double.parseDouble(tokens[2]));
            edgeWeightedGraph.addEdge(edge);
        }
        KruskalMST kruskal = new KruskalMST(edgeWeightedGraph);
        System.out.printf("%.5f\n",  kruskal.weight());

    }
}




