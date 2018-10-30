/**
 * Class for percolation.
 */
class Percolation {
    /**
     *the boolean array.
     */
    private boolean[] array;
    /**
     *object declaration.
     */
    private Graph graph;
    /**
     * variable for array size.
     */
    private int arraySize;
    /**
     * variable for size.
     */
    private int size;
    /**
     * initializing count.
     */
    private int count;
    /**
     *first variable.
     */
    private int first;
    /**
     * last variable.
     */
    private int last;
    /**
     * Constructs the object.
     */
    protected Percolation() {

    }
    /**
     * Constructs the object.
     *
     * @param n int
     */
    Percolation(final int n) {
        this.arraySize = n;
        this.size = n * n;
        this.first = size;
        this.last = size + 1;
        this.count = 0;
        graph = new Graph(size + 2);
        array = new boolean[size];
        for (int i = 0; i < arraySize; i++) {
            graph.addEdge(first, i);
            graph.addEdge(last, size - i - 1);
        }
    }
    /**
     * method to convert from two dimensional to one dimensional.
     *
     * @param      row   The row
     * @param      col   The col
     *
     * @return  onedimensional array
     */
    public int toOneD(final int row, final int col) {
        return (arraySize * (row - 1)) + (col - 1);
    }
    /**
     * Connects open sites(== full site).
     *
     * @param      row   The row
     * @param      col   The col
     */
    private void connectOpenSites(final int row, final int col) {
        if (array[col] && !graph.hasEdge(row, col)) {
            graph.addEdge(row, col);
        }
    }
    /**
     * method that opens the blocked site.
     *
     * @param      row     The row
     * @param      col  The column
     */
    public void open(final int row, final int col) {
        int index = toOneD(row, col);
        array[index] = true;
        count++;
        int firstrow = index - arraySize;
        int lastrow = index + arraySize;
        if (arraySize == 1) {
            graph.addEdge(first, index);
            graph.addEdge(last, index);
        }
        if (lastrow < size) {         //last
            connectOpenSites(index, lastrow);
        }
        if (firstrow >= 0) {              //first
            connectOpenSites(index, firstrow);
        }
        if (col == 1) {                 //left
            if (col != arraySize) {
                connectOpenSites(index, index + 1);
            }
            return;
        }
        if (col == arraySize) {         //right
            connectOpenSites(index, index - 1);
            return;
        }
        connectOpenSites(index, index + 1);
        connectOpenSites(index, index - 1);
    }
    /**
     * Determines if open.
     *
     * @param      row   The row
     * @param      col   The col
     *
     * @return     True if open, False otherwise.
     */
    public boolean isOpen(final int row, final int col) {
        return array[toOneD(row, col)];
    }
    /**
     * return number of open sites.
     *
     * @return count
     */
    public int numberOfOpenSites() {
        return count;
    }
    /**
     * method to check whether there is a flow.
     *
     * @return boolean
     */
    public boolean percolates() {
        ConnectedComponents cc = new ConnectedComponents(graph);
        return cc.connected(first, last);
    }
}
