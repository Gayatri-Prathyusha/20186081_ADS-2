import java.util.Scanner; 
class Solution {
 	Solution() {

 	}
 	public static void main(String[] args) {
 		Scanner sc = new Scanner(System.in);

 		int sizeofgrid = Integer.parseInt(sc.nextLine());
 		Graph graph = new Graph(sizeofgrid * sizeofgrid); 		
 		while(sc.hasNext()) {
 			String[] tokens = sc.nextLine().split(" ");
 			graph.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
            graph.hasEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
 		}
 		Percolation perco = new Percolation(sizeofgrid);
 		System.out.println(perco.percolates());

 	}
 }