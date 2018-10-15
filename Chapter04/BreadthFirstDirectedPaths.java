import java.util.LinkedList;

public class BreadthFirstDirectedPaths{
    private int s;
    private boolean[] marked;
    private int[] edgeTo;

    public BreadthFirstDirectedPaths(Digraph DG, int s){
        this.s = s;
        marked = new boolean[DG.V()];
        edgeTo = new int[DG.V()];
        bfs(DG, s);
    }

    private void bfs(Digraph DG, int v){
        LinkedList<Integer> queue = new LinkedList<>();
        marked[v] = true;
        queue.add(v);
        while(!queue.isEmpty()){
            int w = queue.remove();
            for(int x : DG.adj(w)){
                if(!marked[x]){
                    marked[x] = true;
                    edgeTo[x] = w;
                    queue.add(x);
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
}