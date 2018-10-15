public class Cycle{
    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph g){
        marked = new boolean[g.V()];
        for(int s = 0; s < g.V(); s++){
            if(!marked[s]){
                dfs(g, s, s);
            }            
        }
    }

    private void dfs(Graph g, int v, int w){
        marked[v] = true;
        for(int x : g.adj(v)){
            if(!marked[x]){
                dfs(g, x, v);
            }else if(x != w){
                hasCycle = true;
            }
        }
    }

    public boolean hasCycle(){
        return hasCycle;
    }
}