import java.util.Scanner; 
class Solution {
 	Solution() {

 	}
 	public static void main(String[] args) {
 		Scanner sc = new Scanner(System.in);
 		int sizeofgrid = Integer.parseInt(sc.nextLine());
 		Percolation perco = new Percolation(sizeofgrid);
 		while(sc.hasNext()) {
 			String[] tokens = sc.nextLine().split(" ");
 			perco.open(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
 		}
 		System.out.println(perco.percolates());

 	}
 }