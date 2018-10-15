public class AcyclicLP{
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    
    public AcyclicLP(EdgeWeightedDigraph G, int s){
        int v = G.V();
        edgeTo = new DirectedEdge[v];
        distTo = new double[v];
        for(int i = 0; i < v; i++){
            distTo[i] = Double.NEGATIVE_INFINITY;
        }
        distTo[s] = 0.0;

        Topological top = new Topological(G);
        for(int w : top.order()){
            relax(G, w);
        }
    }

    private void relax(EdgeWeightedDigraph G, int v){
        for(DirectedEdge e : G.adj(v)){
            int w = e.to();
            if(distTo[w] < distTo[v] + e.weight()){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
            }
        }
    }

    public double distTo(int v){
        return distTo[v];
    }

    public boolean hasPathTo(int v){
        return distTo[v] > Double.NEGATIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v){
        if(!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<>();
        for(DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]){
            path.push(e);
        }
        return path;
    }
}