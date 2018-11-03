import java.util.Scanner;
/**
 * Class for page rank.
 */
class PageRank {

  double[] pr;

  private final static int THOUS = 1000;

  PageRank(Digraph g) {

    pr = new double[g.ver()];
    // Digraph reverse = g.reverse();
    calculatePR(g);
  }

  public void calculatePR(Digraph gra) {    
    Digraph gra1 = gra.reverse();
    for (int i = 0; i < gra.ver(); i++) {

      double tempPageRank = (double) 1 / gra.ver();
      int count = 0;
      for (int j = 0; j < THOUS; j++) {
          
        for (int k : gra1.adj(i)) {
            // System.out.println(i + ">" + k);
          count += 1;
          if (gra.outdegree(i) != 0) {
            tempPageRank = (double) tempPageRank / gra.outdegree(k);
            } else {
              tempPageRank = (double) tempPageRank / (gra.ver() - 1);
            }
          }
        }

      if (count != 0) {
        pr[i] = tempPageRank;   
      } else {
        pr[i] = 0.0;
      }
      
    }

    // return tempPageRank
  }

  public double getPR(int i) {
    return pr[i];
  }

  public String toString() {
    String s = "";
    for (int i = 0; i < pr.length; i++) {
      s += i + " - " + getPR(i) + "\n";
    }
    return s;
  }
}

class WebSearch {

}


public class Solution {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);		
		int vertices = Integer.parseInt(scan.nextLine());
		// iterate count of vertices times 
		// to read the adjacency list from std input
		// and build the graph
		Digraph digraph = new Digraph(vertices);
		int duplicatecopyofver = vertices;
		while(duplicatecopyofver > 0 ) {
			String[] verticesarray = scan.nextLine().split(" ");
			int vertex = Integer.parseInt(verticesarray[0]);
			for(int i = 1; i < verticesarray.length; i++) {
				digraph.addEdge(vertex, Integer.parseInt(verticesarray[i]));
			}
			duplicatecopyofver--;
		}
		System.out.println(digraph.ver() + " vertices, "+ digraph.edg() + " edges ");

		for(int j = 0; j < vertices; j++) {
			System.out.print(j + ": ");
			for(int k : digraph.adj(j)) {
				System.out.print(k + " ");
			}
			System.out.println();
		}
	    System.out.println();

		// Create page rank object and pass the graph object to the constructor
		PageRank pr = new PageRank(digraph);		
		// print the page rank object
		System.out.println(pr);

		// This part is only for the final test case
		
		// File path to the web content
		String file = "WebContent.txt";
		
		// instantiate web search object
		// and pass the page rank object and the file path to the constructor
		
		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky
		
	}
}
