import java.util.Arrays;
public class WordNet {
    LinearProbingHashST<String, Integer> linearprobing;



    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        
        //linearprobing = new LinearProbingHashST<String, Integer>();
        readsynsets(synsets, hypernyms);
        //readhypernyms(hypernyms);


    }
    public void readsynsets(String synsets1, String hypernyms1) {

            int id = 0;
            int vertices = 0;
        try {            
            In in = new In("./Files/" + synsets1);
            String[] nouns = null;
            while (!in.isEmpty()) {
            String[] tokens = in.readString().split(",");
            id = Integer.parseInt(tokens[0]);
            nouns = tokens[1].split(" ");
            //System.out.println(Arrays.toString(nouns));
        }
        Digraph digraph = new Digraph(vertices);
        readHypernyms(hypernyms1, digraph);

    } catch (Exception e) {
        System.out.println("File not Found");
    }
}
    void readHypernyms(String hypernyms1, Digraph digraph1) {
        try {
            In in = new In(hypernyms1);
            while (!in.isEmpty()) {
                String[] filearr = in.readString().split(",");
                int v = Integer.parseInt(filearr[0]);
                int w = Integer.parseInt(filearr[1]);
                digraph1.addEdge(v, w);
            }
            System.out.println(digraph1);

    } catch(Exception e) {

    }
}

    // returns all WordNet nouns
    //public Iterable<String> nouns()

    // is the word a WordNet noun?
    //public boolean isNoun(String word)

    // distance between nounA and nounB (defined below)
    //public int distance(String nounA, String nounB)

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    //public String sap(String nounA, String nounB)

    // do unit testing of this class
    //public static void main(String[] args) {
    //}
}
