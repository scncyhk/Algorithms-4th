import java.util.Queue;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value>{
    private class Node{
        private Key key;
        private Value val;
        private Node left,right;
        private int n;

        public Node(Key key, Value val, int n){
            this.key = key;
            this.val = val;
            this.n = n;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public int size(){
        return size(root);
    }

    private int size(Node x){
        if(x == null) return 0;
        else return x.n;
    }

    public Value get(Key key){
        if(key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(root, key);
    }

    private Value get(Node x, Key key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0){
            return get(x.left, key);
        } else if(cmp > 0){
            return get(x.right, key);
        } else {
            return x.val;
        }      
    }

    public void put(Key key, Value val){
        if(key == null) throw new IllegalArgumentException("argument to contains() is null");
        if(val == null){
            delete(key);
            return;
        }
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val){
        if(x == null){
            return new Node(key, val, 1);
        }
        int cmp = key.compareTo(x.key);
        if(cmp < 0){
            x.left = put(x.left, key, val);
        } else if(cmp > 0){
            x.right = put(x.right, key, val);
        } else{
            x.val = val;
        }
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    public boolean contains(Key key){
        if(key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public Key max(){
        if(isEmpty()) throw new NoSuchElementException("Symbol table is empty");
        return max(root).key;
    }

    private Node max(Node x){
        if(x.right == null) return x;
        else return max(x.right);
    }

    public Key min(){
        if(isEmpty()) throw new NoSuchElementException("Symbol table is empty");
        return min(root).key;
    }

    private Node min(Node x){
        if(x.left == null) return x;
        else return min(x.left);
    }

    public Key floor(Key key){
        if(key == null) throw new IllegalArgumentException("argument to contains() is null");
        Node x = floor(root, key);
        if(x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp == 0) {
            return x;
        } else if(cmp < 0){
            return floor(x.left, key);
        } else{
            Node t = floor(x.right, key);
            if(t != null) return t;
            return x;
        }
    }

    public Key ceiling(Key key){
        if(key == null) throw new IllegalArgumentException("argument to contains() is null");
        Node x = ceiling(root, key);
        if(x == null) return null;
        return x.key;
    }

    private Node ceiling(Node x, Key key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp == 0){
            return x;
        } else if(cmp > 0){
            return ceiling(x.right, key);
        } else {
            Node t = ceiling(x.left, key);
            if(t != null) return t;
            return x;
        }
    }

    public Key select(int k){
        if(k < 0 || k >= size()){
            throw new IllegalArgumentException("called select() with invalid argument: " + k);
        }
        return select(root, k).key;
    }

    private Node select(Node x, int k){
        if(root == null) return null;
        int t = size(x.left);
        if(t > k){
            return select(x.left, k);
        } else if(t < k){
            return select(x.right, k - t - 1);
        } else {
            return x;
        }
    }

    public int rank(Key key){
        if(key == null) throw new IllegalArgumentException("argument to contains() is null");
        return rank(root, key);
    }

    private int rank(Node x, Key key){
        if(x == null) return 0;
        int cmp = key.compareTo(x.key);
        if(cmp == 0){
            return size(x.left);
        } else if(cmp < 0){
            return rank(x.left, key);
        } else {
            return size(x.left) + 1 + rank(x.right, key);
        }
    }

    public void deleteMin(){
        if(isEmpty()) throw new NoSuchElementException("Symbol table is empty");
        root = deleteMin(root);
    }

    private Node deleteMin(Node x){
        if(x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax(){
        if(isEmpty()) throw new NoSuchElementException("Symbol table is empty");
        root = deleteMax(root);
    }

    private Node deleteMax(Node x){
        if(x.right == null) {
            return x.left;
        }
        x.right = deleteMax(x.right);
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key){
        if(key == null) throw new IllegalArgumentException("argument to contains() is null");
        root = delete(root, key);
    }

    private Node delete(Node x, Key key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0){
            x.left = delete(x.left, key);
        } else if(cmp > 0){
            x.right = delete(x.right, key);
        } else{
            if(x.left == null) return x.right;
            if(x.right == null) return x.left;
            Node t = x;
            x = min(x.right);
            x.left = t.left;
            x.right = deleteMin(t.right);
        }
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Iterable<Key> keys(){
        return keys(min(),max());
    }

    public Iterable<Key> keys(Key lo, Key hi){
        Queue<Key> queue = new LinkedList<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi){
        if(x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if(cmplo < 0) keys(x.left, queue, lo, hi);
        if(cmplo <= 0 && cmphi >= 0) queue.add(x.key);
        if(cmphi > 0) keys(x.right, queue, lo, hi);
    }
}