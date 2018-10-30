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
    ConnectedComponents(final Graph g, final int s) {
        marked = new boolean[g.vertices()];
        id  = new int[g.vertices()];
        for (int i = 0; i < g.vertices(); i++) {
            marked[i] = false;
            if (!marked[i]) {
                dfs(g, i);
                count++;
            }
        }
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
    public boolean percolates() {
        if (count > 1) {
            return false;
        } else {
            return true;
        }
    }
}