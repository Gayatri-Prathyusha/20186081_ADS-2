import java.util.Scanner;
class Solution {
	Solution () {

	}
	public static void main(String[] args) {
		String synsets = StdIn.readString();
		//System.out.println(synsets);
	    In in1 = new In("./Files/" + synsets);
		String hypernyms = StdIn.readString();
		String type = StdIn.readString();
		
		if (type.equals("Graph")) {
			WordNet wordnet = new WordNet(synsets, hypernyms);
		} else {
			
		}

	}
}