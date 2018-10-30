import java.util.Scanner; 
class Solution {
 	Solution() {

 	}
 	public static void main(String[] args) {
 		Scanner sc = new Scanner(System.in);

 		int sizeofgrid = Integer.parseInt(sc.nextLine());
 		GraphADT graph = new GraphADT(sizeofgrid * sizeofgrid); 		
 		while(sc.hasNext()) {
 			String[] tokens = sc.nextLine().split(" ");
 			graph.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
            graph.hasEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
 		}
 		ConnectedComponents ccobject = new ConnectedComponents(graph);

 	}
 }