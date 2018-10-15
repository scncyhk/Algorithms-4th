import edu.princeton.cs.algs4.In;

public class EdgeWeightedDigraph{
    private final int v;
    private int e;
    private Bag<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int v){
        this.v = v;
        this.e = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[v];
        for(int i = 0; i < v; i++){
            adj[i] = new Bag<DirectedEdge>();
        }
    }

    public EdgeWeightedDigraph(In in){
        this(in.readInt());
        int te = in.readInt();
        for(int i = 0; i < te; i++){
            int tv = in.readInt();
            int tw = in.readInt();
            double tweight = in.readDouble();
            addEdge(new DirectedEdge(tv, tw, tweight));
        }
    }

    public int V(){
        return v;
    }

    public int E(){
        return e;
    }

    public void addEdge(DirectedEdge de){
        adj[de.from()].add(de);
        e++;
    }

    public Iterable<DirectedEdge> adj(int v){
        return adj[v];
    }

    public Iterable<DirectedEdge> edges(){
        Bag<DirectedEdge> edges = new Bag<DirectedEdge>();
        for(int i = 0; i < v; i++){
            for(DirectedEdge de : adj[i]){
                edges.add(de);
            }
        }
        return edges;
    }

    public String toString(){
        String s = "";
        for(DirectedEdge e : edges()){
            s += e.toString() + "\n";
        }
        return s;
    }
}