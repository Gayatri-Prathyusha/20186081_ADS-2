import java.util.Scanner;
/**
 * Client class.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    protected Solution() {
        //Empty Constructer.
    }
    /**
     * Client function.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        GraphADT graph = new GraphADT();
        String str = scan.nextLine();
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        String[] data = scan.nextLine().split(",");
        graph = new GraphADT(vertices);
        while (scan.hasNext()) {
            String connect = scan.nextLine();
            String[] connector = connect.split(" ");
            graph.addEdge(Integer.parseInt(connector[0]),
            Integer.parseInt(connector[1]));
        }
        switch (str) {
            case "List":
            try {
                graph.listdisplay(vertices, edges, data);
            } catch (Exception p) {
                System.out.println(p.getMessage());
            }
            break;
            case "Matrix":
            try {
                graph.matrixdisplay(vertices, edges);
            } catch (Exception p) {
                System.out.println(p.getMessage());
            }
            break;
            default:
            break;
        }
    }
}
