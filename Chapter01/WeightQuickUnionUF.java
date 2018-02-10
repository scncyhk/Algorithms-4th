import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class WeightQuickUnionUF
{
    private int[] id;
    private int[] sz;
    private int count;

    public WeightQuickUnionUF(int n){
        count = n;
        id = new int[n];
        sz = new int[n];
        for(int i = 0; i < n; ++i){
            id[i] = i;
            sz[i] = 1;
        }
    }

    public int count(){
        return count;
    }

    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    public int find(int p){
        while(p != id[p]){
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q){
        int proot = find(p);
        int qroot = find(q);
        if(proot == qroot){
            return;
        }
        if(sz[proot] < sz[qroot]){
            id[proot] = qroot;
            sz[qroot] += sz[proot];
        } else {
            id[qroot] = proot;
            sz[proot] += sz[qroot];
        }
        --count;
    }

    public static void main(String[] args){
        int N = StdIn.readInt();
        WeightQuickUnionUF uf = new  WeightQuickUnionUF(N);
        while(!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if(uf.connected(p, q)){
                continue;
            }
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + "components");
    }
}