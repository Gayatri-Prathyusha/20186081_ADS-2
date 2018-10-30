/**
 * Class for graph.
 */
class Graph {
    /**
     * matrix declaration.
     */
    private int[][] grid;
    /**
     * declaration of variable.
     */
    private int vertices;
    /**
     * declaration of variable.
     */
    private int edges;
    /**
     * Constructs the object.
     *
     * @param      vertices  The vertices
     */
    Graph(final int vertices) {
        grid = new int[vertices][vertices];
    }
    public int vertices() {
        return vertices;
    }
    public void addEdge(final int vertexOne, final int vertexTwo) {
        if (vertexOne != vertexTwo) {
            if (!hasEdge(vertexOne, vertexTwo)) {
                grid[vertexOne][vertexTwo] = 1;
                // grid[vertexTwo][vertexOne] = 1;
                edges++;
            }
        }
    }
    public boolean hasEdge(final int vertexOne, final int vertexTwo) {
        if (grid[vertexOne][vertexTwo] == 1) {
            return true;
        }
        return false;
    }
    // public void connected(final int v1, final int w1) {
    //     grid[v1][w1] = 1;
    // }
    public int[] adj(final int v) {
        return grid[v];
    }
}