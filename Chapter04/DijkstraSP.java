import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DijkstraSP{
    private DirectedEdge [] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph G, int s){
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        pq = new IndexMinPQ<>(G.V());

        for(int i = 0; i < G.V(); i++){
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0;
        pq.insert(s, 0.0);
        while(!pq.isEmpty()){
            relax(G, pq.delMin());
        }
    }

    private void relax(EdgeWeightedDigraph G, int v){
        for(DirectedEdge e : G.adj(v)){
            int w = e.to();
            if(distTo[w] > distTo[v] + e.weight()){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if(pq.contains(w)){
                    pq.change(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    public double distTo(int v){
        return distTo[v];
    }

    public boolean hasPathTo(int v){
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v){
        if(!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<>();
        for(DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]){
            path.push(e);
        }
        return path;
    }

    public static void main(String[] args){
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        AcyclicSP sp = new AcyclicSP(G, s);

        for(int i = 0; i < G.V(); i++){
            StdOut.print(s + " to " + i);
            StdOut.printf(" (%4.2f): ", sp.distTo(i));
            if(sp.hasPathTo(i)){
                for(DirectedEdge e : sp.pathTo(i)){
                    StdOut.print(e + " ");
                }
            }
            StdOut.println();
        }
    }
}