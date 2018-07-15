import java.util.Queue;
import java.util.LinkedList;

public class LinearProbingHashST<Key, Value>{
    private static final int INIT_CAPACITY = 16;

    private int m;
    private int n;
    private Key[] keys;
    private Value[] vals;

    public LinearProbingHashST(){
        this(INIT_CAPACITY);
    }

    public LinearProbingHashST(int m){
        this.m = m;
        keys = (Key[])new Object[m];
        vals = (Value[])new Object[m];
    }

    private void resize(int cap){
       LinearProbingHashST<Key, Value> t = new LinearProbingHashST<>(cap);
        for(int i = 0; i < m; ++i){
            if(keys[i] != null){
                t.put(keys[i], vals[i]);
            }
        }
        this.m = t.m;
        this.keys = t.keys;
        this.vals = t.vals;
    }

    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public int size(){
        return n;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public boolean contains(Key key){
        if(key == null) throw new IllegalArgumentException("argument to constains() is null");
        return get(key) != null;
    }

    public void put(Key key, Value val){
        if(key == null) throw new IllegalArgumentException("argument to put() is null");
        if(val == null){
            delete(key);
            return;
        }

        if(n >= m / 2) resize(2 * m);
        int i;
        for(i = hash(key); keys[i] != null; i = (i + 1) % m){
            if(keys[i].equals(key)){
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        ++n;
    }

    public Value get(Key key){
        if(key == null) throw new IllegalArgumentException("argument to get() is null");
        for(int i = hash(key); keys[i] != null; i = (i + 1) % m){
            if(keys[i].equals(key)){
                return vals[i];
            }
        }
        return null;
    }

    public void delete(Key key){
        if(key == null) throw new IllegalArgumentException("argument to delete() is null");
        if(!contains(key)) return;
        int i = hash(key);
        for(; keys[i] != null; i = (i + 1) % m){
            if(keys[i].equals(key)){
                keys[i] = null;
                vals[i] = null;
                break;
            }
        }
        i = (i + 1) % m;
        for(; keys[i] != null; i = (i + 1) % m){
            Key tk = keys[i];
            Value tv = vals[i];
            keys[i] = null;
            vals[i] = null;
            --n;
            put(tk, tv);
        }
        --n;
        if(n > 0 && n == m / 8){
            resize(m / 2);
        }
    }

    public Iterable<Key> keys(){
        Queue<Key> queue = new LinkedList<>();
        for(int i = 0; i < m; ++i){
            if(keys[i] != null){
                queue.add(keys[i]);
            }
        }
        return queue;
    }
}