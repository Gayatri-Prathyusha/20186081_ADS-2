/**
 * percolation theory.
 * @author Gayatri Prathyusha.
 */
import java.util.Scanner;
/**
 * Class for percolation.
 */
class Percolation {
    /**
     * declaration of weightqUnion.
     */
    private Graph graph;
    /**
     * private declaration of n.
     */
    private int n;
    /**
     * declaration of size.
     */
    private int size;
    /**
     * declaration of top.
     */
    private int top;
    /**
     * declaration of bottom.
     */
    private int bottom;
    /**
     * declaration of count.
     */
    private int count;
    /**
     * declaration of boolean array.
     */
    private boolean[] connected;
    /**
     * Constructs the object.
     *
     * @param      n1    The n 1
     */
    Percolation(final int n1) {
        this.n = n1;
        this.size = n1 * n1;
        this.top = size;
        this.bottom = size + 1;
        this.count = 0;
        graph = new Graph(size + 2);
        connected = new boolean[size];
        for (int i = 0; i < n; i++) {
            graph.addEdge(top, i);
            graph.addEdge(bottom, size - i);
        }
    }
    /**
     * converts to 1D array.
     *
     * @param      i     { parameter_description }
     * @param      j     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private int convert(final int i, final int j) {
    //since we take size as 0 to n-1, we decrement one value from rows and columns.
        return n * (i) + (j);
    }
    /**
     * Connects open sites(== full site).
     *
     * @param      row   The row
     * @param      col   The col
     */
    private void connectOpenSites(final int row, final int col) {
        if (connected[col] && !graph.hasEdge(row, col)) {
            graph.addEdge(row, col);
        }
    }
    /**
     * opens the blocked sites.
     *
     * @param      row   The row
     * @param      col   The col
     */
    public void open(final int row, final int col) {
        // int count = 0;
        int index = convert(row, col);
        connected[index] = true;
        count++;
        int toprow = index - n;
        int bottomrow = index + n;
        if (n == 1) {
            graph.addEdge(top, index);
            graph.addEdge(bottom, index);
        }
        if (bottomrow < size) { //bottom
            connectOpenSites(index, bottomrow);
        }
        if (toprow >= 0) { //top
            connectOpenSites(index, toprow);
        }
        if (col == 1) { //left
            if (col != n) {
                connectOpenSites(index, index + 1);
            }
            return;
        }
        if (col == n) { //right
            connectOpenSites(index, index - 1);
            return;
        }
        connectOpenSites(index, index + 1);
        connectOpenSites(index, index - 1);
    }
    /**
     * Determines if it is an open site.
     *
     * @param      row   The row
     * @param      col   The col
     *
     * @return     True if open, False otherwise.
     */
    public boolean isOpen(final int row, final int col) {
        return connected[convert(row, col)];
    }
    /**
     * counts number of open sites.
     *
     * @return     { description_of_the_return_value }
     */
    public int findOpenSites() {
        return count;
    }
    /**
     * returns true if percolates.
     *
     * @return     { description_of_the_return_value }
     */
    public boolean percolates() {
        ConnectedComponents cc = new ConnectedComponents(graph);
        return cc.connected(top, bottom);
    }
}