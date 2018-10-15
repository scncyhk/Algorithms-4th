public class TwoColor{
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = true;

    public TwoColor(Graph g){
        marked = new boolean[g.V()];
        color = new boolean[g.V()];
        for(int x = 0; x < g.V(); x++){
            if(!marked[x]){
                dfs(g, x);
            }
        }
    }

    private void dfs(Graph g, int v){
        marked[v] = true;
        for(int w : g.adj(v)){
            if(!marked[w]){
                color[w] = !color[v];
            }else if(color[w] == color[v]){
                isTwoColorable = false;
            }           
        }
    }

    public boolean isTwoColorable(){
        return isTwoColorable;
    }
}