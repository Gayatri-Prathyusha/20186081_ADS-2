/**
 * No such element exception.
 */
// import java.util.NoSuchElementException;
import java.util.Arrays;

/**
 * Class for digraph.
 */
public class Digraph {
    /**
     * New line.
     */
    private static final String NEWLINE
    = System.getProperty("line.separator");
    /**
     * number of vertices in this digraph.
     */
    private final int v;
    /**
     * number of edges in this digraph.
     */
    private int e;
    /**
     * adj[v] = adjacency list for vertex v.
     */
    private Bag<Integer>[] adj;
    /**
     * indegree[v] = indegree of vertex v.
     */
    private int[] indegree;
    /**
     * Marked array.
     */
    private boolean[] marked;

    private Bag<Integer>[] incoming;

   /**
    * Constructs the object.
    * Initializes an empty digraph with <em>V</em> vertices.
    *
    * @param      v1    The v 1
    */
    public Digraph(final int v1) {
        this.v = v1;
        this.e = 0;
        indegree = new int[v1];
        adj = (Bag<Integer>[]) new Bag[v1];
        incoming = (Bag<Integer>[]) new Bag[v1];
        for (int ver = 0; ver < v1; ver++) {
            incoming[ver] = new Bag<Integer>();
        }
        for (int ver = 0; ver < v1; ver++) {
            adj[ver] = new Bag<Integer>();
        }
    }

    /**
     * Constructs the object.
     * Initializes a new digraph that is a deep copy
     * of the specified digraph.
     *
     * @param      grp   The group
     */
    public Digraph(final Digraph grp) {
        this(grp.vertex());
        this.e = grp.edge();
        for (int ver = 0; ver < v; ver++) {
            this.indegree[ver] = grp.indegree(ver);
        }
        for (int ver = 0; ver < grp.vertex(); ver++) {
            // reverse so that adjacency list is in same
            //order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : grp.adj[ver]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[ver].add(w);
            }
        }
    }

    /**
     * Returns the number of vertices in this digraph.
     * Complexity is 1.
     *
     * @return     the number of vertices in this digraph
     */
    public int vertex() {
        return v;
    }

    /**
     * Returns the number of edges in this digraph.
     * Complexity is 1.
     *
     * @return the number of edges in this digraph
     */
    public int edge() {
        return e;
    }

    /**
     * Validates the vertecies.
     * Complexity is 1.
     *
     * @param      v1    The v 1
     */
    private void validateVertex(final int v1) {
        if (v1 < 0 || v1 >= v) {
            throw new IllegalArgumentException("vertex "
                + v1 + " is not between 0 and " + (v - 1));
        }
    }

    /**
     * Adds the directed edge v→w to this digraph.
     * Complexity is 1.
     *
     * @param  v1 the tail vertex
     * @param  w the head vertex
     * @throws IllegalArgumentException unless both
     * {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(final int v1, final int w) {
        validateVertex(v1);
        validateVertex(w);
        adj[v1].add(w);
        incoming[w].add(v1);
        indegree[w]++;
        e++;
    }

    /**
     * Returns the vertices adjacent from vertex
     * {@code v} in this digraph.
     * Complexity is V.
     * Iterates throughout the adjecency array and returns
     * the required vertex.
     *
     * @param  v1 the vertex
     * @return the vertices adjacent from vertex
     * {@code v} in this digraph, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(final int v1) {
        validateVertex(v1);
        return adj[v1];
    }

    /**
     * Returns the number of directed edges incident from vertex {@code v}.
     * This is known as the <em>outdegree</em> of vertex {@code v}.
     * Complexity is 1.
     *
     * @param  v1 the vertex
     * @return the outdegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int outdegree(final int v1) {
        validateVertex(v1);
        return adj[v1].size();
    }

    /**
     * Returns the number of directed edges incident to vertex {@code v}.
     * This is known as the <em>indegree</em> of vertex {@code v}.
     *
     * Complexity is  1.
     * @param  v1 the vertex
     * @return the indegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int indegree(final int v1) {
        validateVertex(v1);
        return indegree[v1];
    }

    /**
     * Returns the reverse of the digraph.
     * Complexity is N^2.
     *
     * @return the reverse of the digraph
     */
    public Digraph reverse() {
        Digraph reverse = new Digraph(v);
        for (int ver = 0; ver < v; ver++) {
            for (int w : adj(ver)) {
                reverse.addEdge(w, ver);
            }
        }
        return reverse;
    }

    /**
     * Returns a string representation of the graph.
     * Complexity is N^2.
     *
     * @return the number of vertices <em>V</em>, followed by
     * the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(v + " vertices, " + e + " edges " + NEWLINE);
        for (int ver = 0; ver < v; ver++) {
            s.append(String.format("%d: ", ver));
            for (int w : adj[ver]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
    /**
     * Determines if it has parallel edges.
     *
     * @param      v     { parameter_description }
     *
     * @return     True if has parallel edges, False otherwise.
     */
    public boolean hasParallelEdges(int v) {
        marked = new boolean[vertex()];
        for (int w : adj(v)) {
            if (marked[w]) {
                return true;
            }
            marked[w] = true;
        }

        // for (int w : adj(v)) {
        //     marked[w] = false;
        // }
        return false;
    }
    /**
     * Iterable for  incoming edge.
     *
     * @param      v1    The v 1
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Integer> incomingedge(final int v1) {
        validateVertex(v1);
        return incoming[v1];
    }
}
