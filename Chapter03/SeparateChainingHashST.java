import java.util.Queue;
import java.util.LinkedList;

public class SeparateChainingHashST<Key, Value>{
    private static final int INIT_CAPACITY = 4;

    private int m;
    private int n;
    private SequentialSearchST<Key, Value>[] st;

    public SeparateChainingHashST(){
        this(INIT_CAPACITY);
    }

    public SeparateChainingHashST(int M){
        this.m = M;
        st = (SequentialSearchST<Key, Value>[])new SequentialSearchST[M];
        for(int i = 0; i < M; ++i){
            st[i] = new SequentialSearchST<Key,Value>();
        }
    }

    private void resize(int chains){
        SeparateChainingHashST<Key,Value> temp = new SeparateChainingHashST<>(chains);
        for(int i = 0; i < m; ++i){
            for(Key key : st[i].keys()){
                temp.put(key, st[i].get(key));
            }
        }
        this.m = temp.m;
        this.n = temp.n;
        this.st = temp.st;
    }

    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public int size(){
        return n;
    }

    public boolean isEmpty(){
        return size() == 0;
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

        if(n >= 8 * m) resize(2 * m);
        if(!st[hash(key)].contains(key)) n++;
        st[hash(key)].put(key, val);
    }

    public Value get(Key key){
        if(key == null) throw new IllegalArgumentException("argument to get() is null");
        return (Value)st[hash[key]].get(key);
    } 

    public void delete(Key key){
        if(key == null) throw new IllegalArgumentException("argument to delete() is null");
        int i = hash(key);
        if(st[i].contains(key)) n--;
        st[i].delete(key);

        if(m > INIT_CAPACITY && n <= 2 * m) resize(m / 2);
    }

    public Iterable<Key> keys(){
        Queue<Key> queue = new LinkedList<>();
        for(int i = 0; i < m; ++i){
            for(Key key : st[i].keys){
                queue.add(key);
            }
        }
        return queue;
    }
}