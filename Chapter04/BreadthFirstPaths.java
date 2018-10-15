import java.util.LinkedList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BreadthFirstPaths{
    private final int s;
    private boolean[] marked;
    private int[] edgeTo;

    public BreadthFirstPaths(Graph g, int s){
        this.s = s;
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        bfs(g, s);
    }

    private void bfs(Graph g, int s){
        LinkedList<Integer> queue = new LinkedList<>();
        marked[s] = true;
        queue.add(s);
        while(!queue.isEmpty()){
            int v = queue.remove();
            for(int w : g.adj(v)){
                if(!marked[w]){
                    marked[w] = true;
                    edgeTo[w] = v;
                    queue.add(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v)) return null;
        Stack<Integer> paths = new Stack<>();
        for(int x = v; x != s; x = edgeTo[x]){
            paths.push(x);
        }
        paths.push(s);
        return paths;
    }

    public static void main(String[] args){
        Graph g = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        BreadthFirstPaths path = new BreadthFirstPaths(g, s);
        for(int v = 0; v < g.V(); v++){
            //StdOut.print(s + " to " + v + ": ");
            if(path.hasPathTo(v)){
                StdOut.print(s + " to " + v + ": ");
                for(int w : path.pathTo(v)){
                    if(w == s){
                        StdOut.print(s);
                    } else {
                        StdOut.print("-" + w);
                    }
                }
            }
            StdOut.print("\n");           
        }
    }
}