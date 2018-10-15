import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DirectedDFS{
    private boolean[] marked;

    public DirectedDFS(Digraph dg, int s){
        marked = new boolean[dg.V()];
        dfs(dg, s);
    }

    public DirectedDFS(Digraph dg, Iterable<Integer> source){
        marked = new boolean[dg.V()];
        for(int s : source){
            if(!marked[s]){
                dfs(dg, s);
            }
        }
    }

    private void dfs(Digraph dg, int v){
        marked[v] = true;
        for(int w : dg.adj(v)){
            if(!marked[w]){
                dfs(dg, w);
            }
        }
    }

    public boolean marked(int v){
        return marked[v];
    }

    public static void main(String[] args){
        Digraph DG = new Digraph(new In(args[0]));

        Bag<Integer> sources = new Bag<>();
        for(int i = 1; i < args.length; i++){
            sources.add(Integer.parseInt(args[i]));
        }

        DirectedDFS dfs = new DirectedDFS(DG, sources);
        for(int i = 0; i < DG.V(); i++){
            if(dfs.marked(i)){
                StdOut.print(i + " ");
            }
        }
        StdOut.println();
    }
}