import edu.princeton.cs.algs4.In;

public class EdgeWeightedGraph{
    private final int v;
    private int e;
    private Bag<Edge>[] adj;

    public EdgeWeightedGraph(int v){
        this.v = v;
        this.e = 0;
        adj = (Bag<Edge>[]) new Bag[v];
        for(int i = 0; i < v; i++){
            adj[i] = new Bag<Edge>();
        }
    }

    public EdgeWeightedGraph(In in){
        this(in.readInt());
        int te = in.readInt();
        for(int i = 0; i < te; i++){
            int tv = in.readInt();
            int tw = in.readInt();
            double tweight = in.readDouble();
            addEdge(new Edge(tv, tw, tweight));
        }
    }

    public int V(){
        return v;
    }

    public int E(){
        return e;
    }

    public void addEdge(Edge edge){
        int tv = edge.either();
        int tw = edge.other(tv);
        adj[tv].add(edge);
        adj[tw].add(edge);
        e++;
    }

    public Iterable<Edge> adj(int tv){
        return adj[tv];
    }

    public Iterable<Edge> edges(){
        Bag<Edge> es = new Bag<Edge>();
        for(int i = 0; i < v; i++){
            for(Edge e : adj[i]){
                if(e.other(i) > i){
                    es.add(e);
                }
            }
        }
        return es;
    }

    public String toString(){
        String s = v + " vertices, " + e + " edges\n";
        for(int i = 0; i < v; i++){
            s += i + ": ";
            for(Edge e : this.adj[i]){
                s += e.toString() + "  ";
            }
            s += "\n";
        }
        return s;
    }
}