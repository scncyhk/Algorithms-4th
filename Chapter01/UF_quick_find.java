import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdDraw;

public class UF_quick_find
{
    private int[] id;
    private int count;
    private static int union_cost;

    public UF_quick_find(int n){
        count = n;
        id = new int[n];
        for(int i = 0; i < n; ++i){
            id[i] = i;
        }
        union_cost = 0;
    }

    public int count(){
        return count;
    }

    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    public int find(int p){
        return id[p];
    }

    public void union(int p, int q){
        int idp = find(p);
        int idq = find(q);

        union_cost += 2;

        if(idp == idq){
            return;
        }

        for(int i = 0; i < id.length; ++i){
            if(id[i] == idp){
                id[i] = idq;
                union_cost += 2;
            }
            union_cost += 1;
        }
        --count;
    }

    public static void main(String[] args){
        int cost = 0;
        int total = 0;
        int i = 1;
        int N = StdIn.readInt();
        UF_quick_find uf = new UF_quick_find(N);
        StdDraw.setXscale(0,N);
        StdDraw.setYscale(0,1300);
        StdDraw.setPenRadius(0.005);
        while(!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            union_cost = 0;
            cost = 2;
            if(uf.connected(p, q)){
                total += cost;
                StdDraw.setPenColor(StdDraw.DARK_GRAY);
                StdDraw.point(i,cost);
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.point(i,total / i);
                ++i;               
                continue;
            }
            uf.union(p, q);
            cost += union_cost;
            total += cost;
            StdDraw.setPenColor(StdDraw.DARK_GRAY);
            StdDraw.point(i,cost);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.point(i,total / i);
            ++i;
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + "components");
    }
}