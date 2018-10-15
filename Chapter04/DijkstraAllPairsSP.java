public class DijkstraAllPairsSP{
    private DijstraSP[] all;

    public DijkstraAllPairsSP(EdgeWeightedDigraph G){
        all = new DijstraSP[G.V()];
        for(int i = 0; i < G.V(); i++){
            all[i] = DijstraSP(G, i);
        }
    }

    public Iterable<DirectedEdge> path(int s, int t){
        return all[s].pathTo(t);
    }

    public double dist(int s, int t){
        return all[s].distTo(t);
    }
}