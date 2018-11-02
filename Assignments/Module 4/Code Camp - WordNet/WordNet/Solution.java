/**
 * class for Solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //empty constructor.
    }
    /**
     * Client program.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        String synsets = StdIn.readLine();
        String hypernyms = StdIn.readLine();
        String str2 = StdIn.readLine();
        switch (str2) {
        case "Graph":
            try {
                WordNet wordnet = new WordNet(synsets, hypernyms);
                wordnet.display();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            break;
        case "Queries":
            try {
                WordNet wordnetqueries = new WordNet(synsets,
                                          hypernyms);
                while (StdIn.hasNextLine()) {
                    String line = StdIn.readLine();
                    String[] strarray = line.split(" ");
                    if (strarray[0].equals("null")) {
                        throw new IllegalArgumentException(
                            "IllegalArgumentException");
                    }
                    System.out.println("distance = " + wordnetqueries.distance(strarray[0],
                                       strarray[1]) + ", ancestor = " + wordnetqueries.sap(strarray[0],
                                               strarray[1]));
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            break;
        default:
            break;
        }
    }
}
