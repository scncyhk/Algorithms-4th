public class DepthFirstDirectedPaths{
    private int s;
    private boolean[] marked;
    private int[] edgeTo;

    public DepthFirstDirectedPaths(Digraph DG, int s){
        this.s = s;
        marked = new boolean[DG.V()];
        edgeTo = new int[DG.V()];
        dfs(DG, s);
    }

    private void dfs(Digraph DG, int v){
        marked[v] = true;
        for(int w : DG.adj(v)){
            if(!marked[w]){
                marked[w] = true;
                edgeTo[w] = v;
                dfs(DG, w);
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if(!marked[v]) return null;
        Stack<Integer> paths = new Stack<>();
        for(int x = v; x != s; x = edgeTo[x]){
            paths.push(x);
        }
        paths.push(s);
        return paths;
    }
}