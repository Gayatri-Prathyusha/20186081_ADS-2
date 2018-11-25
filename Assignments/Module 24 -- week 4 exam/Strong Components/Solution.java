import java.util.*;

class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of vertices and edges");
        int vertex = sc.nextInt();
        int edge = sc.nextInt();
        int condition = (vertex * (vertex - 1)) / 2;
        if (edge <= condition) {
            System.out.println("For " + vertex + " Number of vertices we have " + condition + " number of edges");
            DigraphGenerator dg = new DigraphGenerator();
            System.out.println(
                "Enter the number for the type of graph \n 1.  Complete Graph \n 2.  Simple Graph \n" +
                " 3.  Path Graph \n 4.  Cycle Graph \n 5.  Eulierian path graph \n 6.  Eulierian cycle graph \n" +
                " 7.  Binary tree \n 8.  Tournament \n 9.  DAG \n 10. Rooted-in DAG \n" +
                " 11. Rooted-out DAG \n 12. Rooted-in tree \n 13. Rooted-out tree \n");
            int input = sc.nextInt();
            switch (input) {
                case 1:
                    KosarajuSharirSCC kscc = new KosarajuSharirSCC(dg.complete(vertex));
                    int components = kscc.count();
                    System.out.println("For Complete graph, Number of Strong components are " + components);
                    //break;
                case 2:
                    kscc = new KosarajuSharirSCC(dg.simple(vertex, edge));
                    components = kscc.count();
                    System.out.println("For Simple graph, Number of Strong components are " + components);
                    //break;
                case 3:
                    kscc = new KosarajuSharirSCC(dg.path(vertex));
                    components = kscc.count();
                    System.out.println("For Path graph, Number of Strong components are " + components);
                    //break;
                case 4:
                    kscc = new KosarajuSharirSCC(dg.cycle(vertex));
                    components = kscc.count();
                    System.out.println("For Cycle graph, Number of Strong components are " + components);
                    //break;
                case 5:
                    kscc = new KosarajuSharirSCC(dg.eulerianPath(vertex, condition));
                    components = kscc.count();
                    System.out.println("For Eulierian path graph, Number of Strong components are " + components);
                    //break;
                case 6:
                    kscc = new KosarajuSharirSCC(dg.eulerianCycle(vertex, condition));
                    components = kscc.count();
                    System.out.println("For Eulierian cycle graph, Number of Strong components are " + components);
                    //break;
                case 7:
                    kscc = new KosarajuSharirSCC(dg.binaryTree(vertex));
                    components = kscc.count();
                    System.out.println("For Binary tree, Number of Strong components are " + components);
                    //break;
                case 8:
                    kscc = new KosarajuSharirSCC(dg.tournament(vertex));
                    components = kscc.count();
                    System.out.println("For Tournament, Number of Strong components are " + components);
                    //break;
                case 9:
                    kscc = new KosarajuSharirSCC(dg.dag(vertex, condition));
                    components = kscc.count();
                    System.out.println("For DAG, Number of Strong components are " + components);
                    //break;
                case 10:
                    kscc = new KosarajuSharirSCC(dg.rootedInDAG(vertex, condition));
                    components = kscc.count();
                    System.out.println("For Rooted-in DAG, Number of Strong components are " + components);
                    //break;
                case 11:
                    kscc = new KosarajuSharirSCC(dg.rootedOutDAG(vertex, condition));
                    components = kscc.count();
                    System.out.println("For Rooted-out DAG, Number of Strong components are " + components);
                    //break;
                case 12:
                    kscc = new KosarajuSharirSCC(dg.rootedInTree(vertex));
                    components = kscc.count();
                    System.out.println("For Rooted-in tree, Number of Strong components are " + components);
                    //break;
                case 13:
                    kscc = new KosarajuSharirSCC(dg.rootedOutTree(vertex));
                    components = kscc.count();
                    System.out.println("For Rooted-out tree, Number of Strong components are " + components);
                    break;
                default:
                    System.out.println("Wrong case number");
                    break;
            } 
        } else {
            System.out.println("Number of edges for vertices " + vertex + " must be less than " + condition);
        }
    }
}