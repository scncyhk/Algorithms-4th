import java.util.Queue;
import java.util.LinkedList;

/*顺序查找符号表
*插入一次需要~N*N/2次比较
*查找需要的平均比较次数为~N/2
*/

public class SequentialSearchST<Key,Value>{
    private class Node{
        Key k;
        Value v;
        Node next;

        public Node(Key k, Value v, Node next){
            this.k = k;
            this.v = v;
            this.next = next;
        }
    }

    private Node first = null;
    private int N = 0;

    public SequentialSearchST(){

    }

    public void put(Key k, Value v){
        if(k == null) throw new IllegalArgumentException("first argument to put() is null");
        if(v == null){
            delete(k);
            return;
        }
        for(Node x = first; x != null; x = x.next){
            if(k.equals(x.k)){
                x.v = v;
                ++N;
                return;
            }
        }
        first = new Node(k, v, first);
        ++N;
    }

    public Value get(Key k){
        if(k == null) throw new IllegalArgumentException("argument to get() is null");
        for(Node x = first; x != null; x = x.next){
            if(k.equals(x.k)){
                return x.v;
            }
        }
        return null;
    }

    public void delete(Key k){
        if(k == null) throw new IllegalArgumentException("argument to delete() is null");
        first = delete(first, k);
    }

    private Node delete(Node x, Key k){
        if(x == null) return null;
        if(k.equals(x.k)){
            --N;
            return x.next;
        }
        x.next = delete(x.next, k); //递归删除
        return x;
    }

    public boolean contains(Key k){
        for(Node x = first; x != null; x = x.next){
            if(k.equals(x.k)){
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public Iterable<Key> keys(){
        Queue<Key> queue = new LinkedList<Key>();
        for(Node x = first; x != null; x = x.next){
            queue.add(x.k);
        }
        return queue;
    }
}