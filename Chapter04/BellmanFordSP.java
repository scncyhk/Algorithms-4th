import java.util.ArrayDeque;

public class BellmanFordSP{
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private boolean[] onQ;
    private ArrayDeque<Integer> queue;
    private int cost;
    private Iterable<DirectedEdge> cycle;

    public BellmanFordSP(EdgeWeightedDigraph G, int s){
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onQ = new boolean[G.V()];
        queue = new ArrayDeque<>();

        for(int i = 0; i < G.V(); i++){
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        queue.add(s);
        onQ[s] = true;
        while(!queue.isEmpty() && !hasNegativeCycle()){
            int v = queue.pollFirst();
            onQ[v] = false;
            relax(G, v);
        }
    }

    private void relax(EdgeWeightedDigraph G, int v){
        for(DirectedEdge e : G.adj(v)){
            int w = e.to();
            if(distTo[w] > distTo[v] + e.weight()){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if(!onQ[w]){
                    queue.add(w);
                    onQ[w] = true;
                }
            }
            if(cost++ % G.V() == 0){
                findNegativeCycle();
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

    private void findNegativeCycle(){
        int v = edgeTo.length;
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(v);
        for(int i = 0; i < v; i++){
            if(edgeTo[i] != null){
                G.addEdge(edgeTo[i]);
            }
        }

        DirectedCycle dc = new DirectedCycle(G);
        cycle = dc.cycle();
    }

    public boolean hasNegativeCycle(){
        return cycle != null;
    }

    public Iterable<DirectedEdge> negativeCycle(){
        return cycle;
    }
}