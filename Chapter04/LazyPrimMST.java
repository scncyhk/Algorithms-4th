import java.util.PriorityQueue;
import java.util.LinkedList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LazyPrimMST{
    private boolean[] marked;
    private LinkedList<Edge> mst;
    private PriorityQueue<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph G){
        marked = new boolean[G.V()];
        mst = new LinkedList<>();
        pq = new PriorityQueue<>();

        visit(G, 0);
        while(!pq.isEmpty()){
            Edge e = pq.poll();

            int v = e.either();
            int w = e.other(v);
            if(marked[v] && marked[w]) continue;
            mst.add(e);
            if(!marked[v]) visit(G, v);
            if(!marked[w]) visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph G, int v){//横切边
        marked[v] = true;
        for(Edge e : G.adj(v)){
            if(!marked[e.other(v)]) pq.add(e);
        }
    }

    public Iterable<Edge> edges(){
        return mst;
    }

    public double weight(){
        double w = 0.0;
        for(Edge e : mst){
            w += e.weight();
        }
        return w;
    }

    public static void main(String args[]){
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        KruskalMST mst = new KruskalMST(G);
        for(Edge e : mst.edges()){
            StdOut.println(e);
        }
        StdOut.println(mst.weight());
    }
}