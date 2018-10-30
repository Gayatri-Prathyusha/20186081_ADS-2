import java.util.Scanner;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    protected Solution() {
        //unused constructor.
    }
    /**
     * Client program.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        Percolation pobj = new Percolation(n);
        while (scan.hasNext()) {
            String[] tokens = scan.nextLine().split(" ");
            pobj.open(Integer.parseInt(tokens[0]),
                      Integer.parseInt(tokens[1]));
        }
        System.out.println(pobj.percolates()
                           && pobj.numberOfOpenSites() != 0);
    }
}