import java.util.HashMap;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SymbolDigraph{
    private HashMap<String, Integer> hm;
    private String[] keys;
    private Digraph G;

    public SymbolDigraph(String filename, String sp){
        hm = new HashMap<>();
        In in = new In(filename);
        while(in.hasNextLine()){//构造符号表
            String[] a = in.readLine().split(sp);
            for(int i = 0; i < a.length; i++){
                if(!hm.containsKey(a[i])){
                    hm.put(a[i], hm.size());
                }
            }
        }

        keys = new String[hm.size()];
        for(String name : hm.keySet()){
            keys[hm.get(name)] = name;
        }
        //构造图
        G = new Digraph(hm.size());
        in = new In(filename);
        while(in.hasNextLine()){
            String a[] = in.readLine().split(sp);
            int v = hm.get(a[0]);
            for(int i = 1; i < a.length; i++){
                G.addEdge(v, hm.get(a[i]));
            }
        }
    }

    public boolean contains(String s){
        return hm.containsKey(s);
    }

    public int index(String name){
        return hm.get(name);
    }

    public String name(int v){
        return keys[v];
    }

    public Digraph graph(){
        return G;
    }

    public static void main(String[] args){
        String filename = args[0];
        String sp = args[1];
        SymbolGraph sg = new SymbolGraph(filename, sp);

        Graph G = sg.graph();

        while(StdIn.hasNextLine()){
            String s = StdIn.readLine();
            for(int w : G.adj(sg.index(s))){
                StdOut.println(" " + sg.name(w));
            }
        }
    }
}