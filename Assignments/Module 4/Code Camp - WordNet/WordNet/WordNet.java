/**
 * wordnet.
 */
import java.util.ArrayList;
/**
 * Class for word net.
 */
public class WordNet {
    /**
     * declaration of ST.
     */
    private LinearProbingHashST<String, ArrayList<Integer>> nounST;
    /**
     * declaration of ST.
     */
    private LinearProbingHashST<Integer, String> linearst;
    /**
     * declaration of digraph.
     */
    private Digraph digraph;
    /**
     * declaration of SAP.
     */
    private SAP sap;
    /**
     * Gets the digraph.
     *
     * @return     The digraph.
     */
    public Digraph getDigraph() {
        return this.digraph;
    }
    /**
     * Constructs the object.
     *
     * @param      synsets    The synsets
     * @param      hypernyms  The hypernyms
     */
    public WordNet(final String synsets, final String hypernyms) {
        nounST = new LinearProbingHashST<String, ArrayList<Integer>>();
        linearst = new LinearProbingHashST<Integer, String>();
        try {
            In in = new In("./Files/" + synsets);
            int id = 0;
            while (!in.isEmpty()) {
                String line = in.readLine();
                assert !line.equals("");
                String[] tokens = line.split(",");
                id = Integer.parseInt(tokens[0]);
                String[] nouns = tokens[1].split(" ");
                ArrayList<String> nounsList = new ArrayList<String>();
                for (String noun : nouns) {
                    nounsList.add(noun);
                }
                linearst.put(id, tokens[1]);
                for (String noun : nouns) {
                    if (nounST.contains(noun)) {
                        nounST.get(noun).add(id);
                    } else {
                        ArrayList<Integer> s = new ArrayList<Integer>();
                        s.add(id);
                        nounST.put(noun, s);
                    }
                }
            }
            //Hypernyms
            assert id != 1;
            this.digraph = new Digraph(id + 1);
            in = new In("./Files/" + hypernyms);
            while (!in.isEmpty()) {
                String line = in.readLine();
                String[] tokens = line.split(",");
                int synsetIds = Integer.parseInt(tokens[0]);
                for (int i = 1; i < tokens.length; i++) {
                    digraph.addEdge(synsetIds, Integer.parseInt(tokens[i]));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //graph built
        sap = new SAP(digraph);
    }

    /**
     * displaying the output.
     */
    public void display() {
        DirectedCycle directedcycle = new DirectedCycle(digraph);
        if (directedcycle.hasCycle()) {
            throw new IllegalArgumentException("Cycle detected");
        } else if (digraph.edg() > 1) {
            throw new IllegalArgumentException("Multiple roots");
        } else {
            System.out.println(digraph.toString());
        }
    }
    /**
     * return nouns.
     *
     * @return     String iterable.
     */
    public Iterable<String> nouns() {
        return nounST.keys();
    }
    /**
     * Determines if noun.
     *
     * @param      word  The word
     *
     * @return     True if noun, False otherwise.
     */
    public boolean isNoun(final String word) {
        return nounST.contains(word);
    }
    /**
     * checks the distance.
     *
     * @param      nounA  The noun a
     * @param      nounB  The noun b
     *
     * @return     { description_of_the_return_value }
     */
    public int distance(final String nounA, final String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        ArrayList<Integer> idsofA = nounST.get(nounA);
        ArrayList<Integer> idsofB = nounST.get(nounB);

        return sap.length(idsofA, idsofB);
    }
    /**
     * ancestor path.
     *
     * @param      nounA  The noun a
     * @param      nounB  The noun b
     *
     * @return     String.
     */
    public String sap(final String nounA, final String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        ArrayList<Integer> idsofA = nounST.get(nounA);
        ArrayList<Integer> idsofB = nounST.get(nounB);
        int ancestor1 = sap.ancestor(idsofA, idsofB);
        return linearst.get(ancestor1);
    }
}


