import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class CC{
    private boolean[] marked;
    private int[] id;
    private int count;

    public CC(Graph g){
        marked = new boolean[g.V()];
        id = new int[g.V()];
        this.count = 0;
        for(int x = 0; x < g.V(); x++){
            if(!marked[x]){
                dfs(g, x);
                count++;
            }
        }
    }

    private void dfs(Graph g, int v){
        marked[v] = true;
        id[v] = count;
        for(int w : g.adj(v)){
            if(!marked[w]){
                dfs(g, w);
            }
        }
    }

    public boolean connected(int w, int v){
        return id[w] == id[v];
    }

    public int id(int v){
        return id[v];
    }

    public int count(){
        return count;
    }

    public static void main(String[] args){
        Graph g = new Graph(new In(args[0]));
        StdOut.print(g.toString());
        CC cc = new CC(g);

        int m = cc.count();
        StdOut.println(m + "components.");

        Bag<Integer>[] components = (Bag<Integer>[])new Bag[m];
        for(int i = 0; i < m; i++){
            components[i] = new Bag<Integer>();
        }
        for(int v = 0; v < g.V(); v++){
            components[cc.id(v)].add(v);
        }
        for(int i = 0; i < m; i++){
            for(int v : components[i]){
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }
}