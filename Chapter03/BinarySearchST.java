import java.util.Queue;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class BinarySearchST<Key extends Comparable<Key>, Value>{
    private int N;
    private Key[] keys;
    private Value[] vals;

    public BinarySearchST(int capacity){
        N = 0;
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public void put(Key k, Value v){
        if(k == null) throw new IllegalArgumentException("argument to contains() is null");
        if(v == null){
            delete(k);
            return;
        }
       
        int i = rank(k);
        if(i < N && keys[i].compareTo(k) == 0){
            vals[i] = v;
            return;
        }

        if(N == keys.length){
            resize(2 * N);
        }
        for(int j = N; j > i; --j){
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = k;
        vals[i] = v;
        ++N;
    }

    private void resize(int max){
        Key[] ks = (Key[])new Comparable[max];
        Value[] vs = (Value[])new Object[max];
        for(int i = 0; i < N; ++i){
            ks[i] = keys[i];
            vs[i] = vals[i];
        }
        keys = ks;
        vals = vs;
    }

    public int rank(Key k){
        if(k == null) throw new IllegalArgumentException("argument to contains() is null");
        int lo = 0;
        int hi = N - 1;
        while(lo <= hi){
            int midle = lo + (hi - lo) / 2;
            int cmp = k.compareTo(keys[midle]);
            if(cmp < 0){
                hi = midle - 1;
            } else if(cmp > 0){
                lo = midle + 1;
            } else {
                return midle;
            }
        }
        return lo;
    }

    public Value get(Key k){
        if(k == null) throw new IllegalArgumentException("argument to contains() is null");
        if(isEmpty()) {
            return null;
        }
        int i = rank(k);
        if(i < N && keys[i].compareTo(k) == 0){
            return vals[i];
        }else{
            return null;
        }
    }

    public boolean isEmpty(){
        return N==0;
    }

    public void delete(Key k){
        if(k == null) throw new IllegalArgumentException("argument to contains() is null");
        if(isEmpty()) return;
        int i = rank(k);
        if(i < N && k.compareTo(keys[i]) == 0){
            for(int j = i; j < N - 1; ++j){
                keys[j] = keys[j + 1];
                vals[j] = vals[j + 1];
            }
            keys[N - 1] = null;
            vals[N - 1] = null;
            --N;
        }
    }

    public boolean contains(Key k){
        if(k == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(k) != null;
    }

    public int size(){
        return N;
    }

    public Key min(){
        if(isEmpty())throw new NoSuchElementException("Symbol table underflow error");
        return keys[0];
    }

    public Key max(){
        if(isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
        else return keys[N - 1];
    }

    public Key floor(Key k){
        if (k == null) throw new IllegalArgumentException("argument to floor() is null");
        int i = rank(k);
        if(i < N && k.compareTo(keys[i]) == 0){
            return keys[i];
        }
        if(i ==0) return null;
        else return keys[i - 1];
    }

    public Key ceiling(Key k){
        if (k == null) throw new IllegalArgumentException("argument to ceiling() is null"); 
        int i = rank(k);
        if(i == N) return null;
        else return keys[i];
    }

    public Key select(int k){
        if(k < 0 || k >= size()){
            throw new IllegalArgumentException("called select() with invalid argument: " + k);
        }
        return keys[k];
    }

    public void deleteMin(){
        if(isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
        delete(min());
    }

    public void deleteMax(){
        if(isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
        delete(max());
    }

    public int size(Key lo, Key hi){
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null"); 
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null"); 

        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else              return rank(hi) - rank(lo);
    }

    public Iterable<Key> keys(Key lo, Key hi){
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null"); 
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new LinkedList<Key>(); 
        if (lo.compareTo(hi) > 0) return queue;
        for (int i = rank(lo); i < rank(hi); i++) 
            queue.add(keys[i]);
        if (contains(hi)) queue.add(keys[rank(hi)]);
        return queue; 
    }

    public Iterable<Key> keys(){
        return keys(min(),max());
    }
}