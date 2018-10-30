/**
 * Interface for graph.
 */
interface Graph {
    /**
     * Vertices variable.
     *
     * @return     { description_of_the_return_value }
     */
    public int V();
    /**
     * Edge variable.
     *
     * @return     { description_of_the_return_value }
     */
    public int E();
    /**
     * Adds an edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    public void addEdge(int v, int w);
    /**
     * { function_description }
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Integer> adj(int v);
    /**
     * Determines if it has edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(int v, int w);
}
/**
 * Class for graph adt.
 */
class GraphADT implements Graph {
    /**
     * for vertices.
     */
    private int V;
    /**
     * fir edges.
     */
    private int E;
    /**
     * for bag.
     */
    private Bag<Integer>[] adj;
    /**
     * Constructs the object.
     */
    protected GraphADT() {

    }
    /**
     * Constructs the object.
     *
     * @param      V     { parameter_description }
     */
    public GraphADT(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }
    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int E() {
        return E;
    }
    /**
     * Adds an edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    public void addEdge(final int v, final int w) {
        if (v == w) {
            return;
        }
        if (!hasEdge(v,w)) {
            E++;
            
        }
        adj[v].add(w);
        //adj[w].add(v);
    }
    /**
     * { function_description }
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Integer> adj(final int v) {
        return adj[v];
    }
    /**
     * Determines if it has edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v, final int w) {
        for(int k : adj[w]) {
                if (k == v) {
                    return true;
                }
        }
        return false;
    }
    // /**.
    //  * To display the list.
    //  *
    //  * @param      v2          { parameter_description }
    //  * @param      e2          { parameter_description }
    //  * @param      tokens     The tokens
    //  *
    //  * @throws     Exception  { exception_description }
    //  */
    // public void listdisplay(final int v2, final int e2, final String[] tokens) throws Exception {
    //     if (e2 <= 1 && v2 <= 1) {
    //         System.out.println(V() + " vertices" + ", " + E() + " edges");
    //         throw new Exception("No edges");
    //     } else {
    //         System.out.println(V() + " vertices" + ", " + E() + " edges");
    //         for (int i = 0; i < tokens.length; i++) {
    //         String str = "";
    //         str = tokens[i] + ": ";
    //         for (int k : adj(i)) {
    //             str = str + tokens[k] + " ";
    //         }
    //         System.out.println(str);
    //         }
    //     }
    // }

    /**
     * to display the matrix.
     *
     * @param      v1          { parameter_description }
     * @param      e1          { parameter_description }
     *
     * @throws     Exception  { exception_description }
     */
    public void matrixdisplay(final int v1, final int e1) throws Exception {
        if (e1 <= 1 && v1 <= 1) {
            System.out.println(V() + " vertices" + ", " + E() + " edges");
            throw new Exception("No edges");
        } else {
            System.out.println(V() + " vertices" + ", " + E() + " edges");
            int[][] disp = new int[V][V];
            for (int i = 0; i  < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (hasEdge(i, j)) {
                        disp[i][j] = 1;
                    }
                }
            }

            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    System.out.print(disp[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
