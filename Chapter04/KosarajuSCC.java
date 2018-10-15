import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class KosarajuSCC{
    private boolean[] marked;
    private int[] id;
    private int count;

    public KosarajuSCC(Digraph G){
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for(int v : order.reversePost()){
            if(!marked[v]){
                dfs(G, v);
                count++;
            }
        }
    }

    private void dfs(Digraph G, int v){
        marked[v] = true;
        id[v] = count;
        for(int w : G.adj(v)){
            if(!marked[w]){
                dfs(G, w);
            }      
        }
    }

    public boolean stronglyConnected(int v, int w){
        return id[v] == id[w];
    }

    public int id(int v){
        return id[v];
    }

    public int count(){
        return count;
    }

    public static void main(String[] args){
        Digraph g = new Digraph(new In(args[0]));
        StdOut.print(g.toString());
        KosarajuSCC scc = new KosarajuSCC(g);

        int m = scc.count();
        StdOut.println(m + "components.");

        Bag<Integer>[] components = (Bag<Integer>[])new Bag[m];
        for(int i = 0; i < m; i++){
            components[i] = new Bag<Integer>();
        }
        for(int v = 0; v < g.V(); v++){
            components[scc.id(v)].add(v);
        }
        for(int i = 0; i < m; i++){
            for(int v : components[i]){
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }
}