import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DepthFirstPaths{
    private int s;
    private boolean[] marked;
    private int[] edgeTo;//存储到该点的最后一个顶点

    public DepthFirstPaths(Graph g, int s){
        this.s = s;
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        dfs(g, s);
    }

    private void dfs(Graph g, int s){
        marked[s] = true;
        for(int w : g.adj(s)){
            if(!marked[w]){
                edgeTo[w] = s;
                dfs(g, w);
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for(int x = v; x != s; x = edgeTo[x]){
            path.push(x);
        }
        path.push(s);
        return path;
    }

    public static void main(String[] args){
        Graph g = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        DepthFirstPaths path = new DepthFirstPaths(g, s);
        for(int v = 0; v < g.V(); v++){
            StdOut.print(s + " to " + v + ": ");
            if(path.hasPathTo(v)){
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