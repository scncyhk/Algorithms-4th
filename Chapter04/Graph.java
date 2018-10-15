import edu.princeton.cs.algs4.In;

public class Graph{
    private int v;
    private int e;
    private Bag<Integer>[] adj;

    public Graph(int v){
        this.v = v;
        this.e = 0;
        adj = (Bag<Integer>[]) new Bag[v];
        for(int i = 0; i < v; i++){
            adj[i] = new Bag<Integer>();
        }
    }

    public Graph(In in){
        this(in.readInt());
        int e = in.readInt();
        for(int i = 0; i < e; i++){
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public int V(){
        return v;
    }

    public int E(){
        return e;
    }

    public void addEdge(int v, int w){
        adj[v].add(w);
        adj[w].add(v);
        e++;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    public String toString(){
        String s = v + " vertices, " + e + " edges\n";
        for(int i = 0; i < v; i++){
            s += i + ": ";
            for(int w : this.adj[i]){
                s += w + " ";
            }
            s += "\n";
        }
        return s;
    }
}