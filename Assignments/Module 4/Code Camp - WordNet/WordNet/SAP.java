/**
 * Class for sap.
 */
public class SAP {
    /**
     * digraph.
     */
    private Digraph digraph;
    /**
     * breadth first directed paths.
     */
    private BreadthFirstDirectedPaths[] bfs;

    /**
    * constructor takes a digraph (not necessarily a DAG).
    **/
    public SAP(final Digraph digraph1) {
        this.digraph = new Digraph(digraph1);
        bfs = new BreadthFirstDirectedPaths[this.digraph.ver()];
    }
    /**
     * length of shortest ancestral path between v and w.
     * -1 if no such path
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int length(final int v, final int w) {
        if (v < 0 || v > digraph.ver() - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (w < 0 || w > digraph.ver() - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (bfs[v] == null) {
            bfs[v] = new BreadthFirstDirectedPaths(digraph, v);
        }
        if (bfs[w] == null) {
            bfs[w] = new BreadthFirstDirectedPaths(digraph, w);
        }
        int length = Integer.MAX_VALUE;
        for (int i = 0; i < digraph.ver(); i++) {
            if (bfs[v].hasPathTo(i) && bfs[w].hasPathTo(i)) {
                int l = bfs[v].distTo(i) + bfs[w].distTo(i);
                if (l < length) {
                    length = l;
                }
            }
        }
        bfs[v] = null;
        bfs[w] = null;
        if (length != Integer.MAX_VALUE) {
            return length;
        } else {
            return -1;
        }
    }
    /**
     * a common ancestor of v and w that participates in a shortest ancestral.
     * path; -1 if no such path
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int ancestor(final int v, final int w) {
        if (v < 0 || v > digraph.ver() - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (w < 0 || w > digraph.ver() - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (bfs[v] == null) {
            bfs[v] = new BreadthFirstDirectedPaths(digraph, v);
        }
        if (bfs[w] == null) {
            bfs[w] = new BreadthFirstDirectedPaths(digraph, w);
        }
        int length = Integer.MAX_VALUE;
        int ancestor = -1;
        for (int i = 0; i < digraph.ver(); i++) {
            if (bfs[v].hasPathTo(i) && bfs[w].hasPathTo(i)) {
                int l = bfs[v].distTo(i) + bfs[w].distTo(i);
                if (l < length) {
                    length = l;
                    ancestor = i;
                }
            }
        }
        bfs[v] = null;
        bfs[w] = null;
        return ancestor;
    }
    /**
     * length of shortest ancestral path between any vertex in v and any.
     * vertex in w; -1 if no such path
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int length(final Iterable<Integer> v, final Iterable<Integer> w) {
        if (v == null || w == null) {
            throw new NullPointerException();
        }
        int length = Integer.MAX_VALUE;
        for (int i : v) {
            for (int j : w) {
                int l = length(i, j);
                if (l != -1 && l < length) {
                    length = l;
                }
            }
        }
        assert length != -1;
        if (length != Integer.MAX_VALUE) {
            return length;
        } else {
            return -1;
        }
    }
    /**
     * a common ancestor that participates in shortest ancestral path.
     * -1 if no such path
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int ancestor(final Iterable<Integer> v, final
                        Iterable<Integer> w) {
        if (v == null || w == null) {
            throw new NullPointerException();
        }

        int length = Integer.MAX_VALUE;
        int ancestor = -1;

        for (int i : v) {
            for (int j : w) {
                int l = length(i, j);
                if (l != -1 && l < length) {
                    length = l;
                    ancestor = ancestor(i, j);
                }
            }
        }
        assert length != -1;
        return ancestor;
    }
}
