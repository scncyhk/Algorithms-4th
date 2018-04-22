import java.util.Comparator;

public class MaxPQ<Key extends Comparable<Key>>{
    private Key[] pq;
    private int N = 0;

    public MaxPQ(int maxN){
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void insert(Key k){
        if(N == pq.length - 1){
            resize(2 * N);
        }
        pq[++N] = k;
        swim(N);
    }

    public Key delMax(){
        Key max = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        if(N > 0 && N == (pq.length - 1) / 4){
            resize((pq.length - 1) / 2);
        }
        sink(1);
        return max;
    }

    private void resize(int max){
        Key[] t = (Key[]) new Comparable[max + 1];
        for(int i = 0; i < t.length; ++i){
            t[i] = pq[i];
        }
        pq = t;
    }

    private void swim(int k){
        while(k > 1 && less(k / 2, k)){
            exch(k / 2, k);
            k /= 2;
        }
    }

    private void sink(int k){
        while(2 * k <= N){
            int i = 2 * k;
            if(i < N && less(i, i + 1)){
                ++i;
            }
            if(!less(k, i)) break;
            exch(i, k);
            k = i;
        }
    }

    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j){
        Key k = pq[i];
        pq[i] = pq[j];
        pq[j] = k;
    }
}