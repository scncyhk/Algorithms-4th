//有向图中基于深度优先搜索的顶点排序

import java.util.LinkedList;

public class DepthFirstOrder{
    private boolean[] marked;
    private LinkedList<Integer> pre; //前序
    private LinkedList<Integer> post; //后序
    private Stack<Integer> reversePost; //逆后序

    public DepthFirstOrder(Digraph G){
        marked = new boolean[G.V()];
        pre = new LinkedList<>();
        post = new LinkedList<>();
        reversePost = new Stack<>();
        for(int v = 0; v < G.V(); v++){
            if(!marked[v]){
                dfs(G, v);
            }
        }
    }

    public DepthFirstOrder(EdgeWeightedDigraph G){
        marked = new boolean[G.V()];
        pre = new LinkedList<>();
        post = new LinkedList<>();
        reversePost = new Stack<>();
        for(int v = 0; v < G.V(); v++){
            if(!marked[v]){
                dfs(G, v);
            }
        }
    }

    private void dfs(Digraph G, int v){
        pre.add(v);
        marked[v] = true;
        for(int w : G.adj(v)){
            if(!marked[w]){
                dfs(G, w);
            }
        }
        post.add(v);
        reversePost.push(v);
    }

    private void dfs(EdgeWeightedDigraph G, int v){
        pre.add(v);
        marked[v] = true;
        for(DirectedEdge e : G.adj(v)){
            int w = e.to();
            if(!marked[w]){
                dfs(G, w);
            }
        }
        post.add(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre(){
        return pre;
    }

    public Iterable<Integer> post(){
        return post;
    }

    public Iterable<Integer> reversePost(){
        return reversePost;
    }
}