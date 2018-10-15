import java.util.LinkedList;

public class PrimMST{
    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGrap G){
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        pq = new IndexMinPQ<>(G.V());
        for(int i = 0; i < G.V(); i++){
            distTo[i] = Double.POSITIVE_INFINITY;
        }

        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while(!pq.isEmpty()){
            visit(G, pq.delMin());
        }
    }

    private void visit(EdgeWeightedGrap G, int v){
        marked[v] = true;
        for(Edge e : G.adj(v)){
            int w = e.other(v);
            if(marked[w]) continue;
            if(e.weight() < distTo[w]){
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if(pq.contains(w)) {
                    pq.change(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                } 
            }
        }
    }

    public Iterable<Edge> edges(){
        LinkedList<Edge> edges = new LinkedList<>();
        for(int i = 1; i < G.V(); i++){
            edges.add(edgeTo[i]);
        }
        return edges;
    }

    public double weight(){
        double weight = 0.0;
        for(Edge e : this.edges()){
            weight += e.weight();
        }
        return weight;
    }
}