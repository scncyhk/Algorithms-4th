import java.util.LinkedList;
import java.util.PriorityQueue;

public class KruskalMST{
    private LinkedList<Edge> mst;
    private PriorityQueue<Edge> pq;

    public KruskalMST(EdgeWeightedGraph G){
        mst = new LinkedList<>();
        pq = new PriorityQueue<>();
        for(Edge e : G.edges()){
            pq.add(e);
        }
        WeightQuickUnionUF uf = new WeightQuickUnionUF(G.V());

        while(!pq.isEmpty() && mst.size() < G.V() - 1){
            Edge e = pq.poll();
            int v = e.either();
            int w = e.other(v);
            if(uf.connected(v, w)) continue;
            uf.union(v, w);
            mst.add(e);
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
}