/**
 * Class for connected components.
 */
class ConnectedComponents {
    /**
     * declaration of boolean array.
     */
    private boolean[] marked;
    /**
     * declaration of variable.
     */
    private int[] id;
    /**
     * declaration of variable.
     */
    private int count;
    /**
     * Constructs the object.
     *
     * @param      g     { parameter_description }
     * @param      s     { parameter_description }
     */
    ConnectedComponents(final Graph g) {
        marked = new boolean[g.V()];
        id  = new int[g.V()];
        for (int i = 0; i < g.V(); i++) {
            marked[i] = false;
            if (!marked[i]) {
                dfs(g, i);
                count++;
            }
        }
    }
    public boolean connected(int v, int w) {
       return id[v] == id[w];
    }

    public int count() {
        return count;
    }
    public int id(int v) {
        return id[v];
    }
    private void dfs(Graph g, int v) {
        marked[v] = true;
        id[v] = count;
        for (int each : g.adj(v)) {
            if (!marked[each]) {
                dfs(g, each);
            }
        }
    }

}