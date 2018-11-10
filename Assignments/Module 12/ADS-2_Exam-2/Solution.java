import java.util.Scanner;

/**
 * Class for solution.
 */
public final class Solution {
  /**
   * Constructs the object.
   */
  private Solution() {
    // unused constructor
  }
  /**
   * main method.
   *
   * @param      args  The arguments
   */
  public static void main(final String[] args) {
    // Self loops are not allowed...
    // Parallel Edges are allowed...
    // Take the Graph input here...
    Scanner scan = new Scanner(System.in);

    int n = Integer.parseInt(scan.nextLine());
    int k = Integer.parseInt(scan.nextLine());
    EdgeWeightedGraph edgeweightedgraph = new EdgeWeightedGraph(n);

    for (int i = 0; i < k; i++) {
      String[] inputs = scan.nextLine().split(" ");
      int v = Integer.parseInt(inputs[0]);
      int w = Integer.parseInt(inputs[1]);
      double weight = Double.parseDouble(inputs[2]);
      Edge e = new Edge(v, w, weight);
      edgeweightedgraph.addEdge(e);
    }

    String caseToGo = scan.nextLine();
    switch (caseToGo) {
    case "Graph":
      System.out.println(edgeweightedgraph);
      break;

    case "DirectedPaths":
      // Handle the case of DirectedPaths, where two integers are given.
      // First is the source and second is the destination.
      // If the path exists print the distance between them.
      // Other wise print "No Path Found."
      String[] inputarray = scan.nextLine().split(" ");
      int source = Integer.parseInt(inputarray[0]);
      int destination = Integer.parseInt(inputarray[1]);
      DijkstraUndirectedSP dijkstrasp = new DijkstraUndirectedSP(
      	edgeweightedgraph, source);
      if (dijkstrasp.hasPathTo(destination)) {
        System.out.println(dijkstrasp.distTo(destination));
      } else {
        System.out.println("No Path Found.");
      }
      break;


    case "ViaPaths":
      // Handle the case of ViaPaths, where three integers are
      // given.
      // First is the source and second is the via is the
      // one where path should pass throuh.
      // third is the destination.
      // If the path exists print the distance between them.
      // Other wise print "No Path Found."
      String[] inputarray1 = scan.nextLine().split(" ");
      int source1 = Integer.parseInt(inputarray1[0]);
      int via = Integer.parseInt(inputarray1[1]);
      int destination1 = Integer.parseInt(inputarray1[2]);
      DijkstraUndirectedSP dijkstrausp1 = new DijkstraUndirectedSP(
      	edgeweightedgraph, source1);
      DijkstraUndirectedSP dijkstrausp2 = new DijkstraUndirectedSP(
      	edgeweightedgraph, via);
      String s = "";
      if (dijkstrausp1.hasPathTo(destination1)) {
      	int i = 0;
      	System.out.println(dijkstrausp1.distTo(via)
      	 + dijkstrausp2.distTo(destination1));
      	for (Integer e : dijkstrausp1.pathTo(via) ) {
      		s += e + " ";	
      	}
      	for (Integer e : dijkstrausp2.pathTo(destination1)) {
      		if (i != 0) {
      			s += e + " ";
      		}
      		i += 1;
      	}
      	System.out.println(s.substring(0, s.length() - 1));

      } else {
        System.out.println("No Path Found.");
      }
      break;

    default:
      break;
    }

  }
}
