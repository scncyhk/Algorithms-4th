import java.util.Queue;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class RedBlackBST<Key extends Comparable<Key>, Value>{
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{
        private Key key;
        private Value val;
        private Node left,right;
        private int N;
        private boolean color;

        public Node(Key key, Value val, int N, boolean color){
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public RedBlackBST(){

    }

    private boolean isRed(Node x){
        if(x == null) return false;
        return x.color == RED;
    }

    public int size(){
        return size(root);
    }

    private int size(Node x){
        if(x == null) return 0;
        return x.N;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public Value get(Key key){
        if(key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }

    private Value get(Node x, Key key){
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else              return x.val;
        }
        return null;  
    }

    public boolean contains(Key key){
        return get(key) != null;
    }

    public void put(Key key, Value val){
        if(key == null) throw new IllegalArgumentException("argument to contains() is null");
        if(val == null){
            delete(key);
            return;
        }
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value val){
        if(h == null) return new Node(key, val, 1, RED);

        int cmp = key.compareTo(h.key);
        if(cmp < 0){
            h.left = put(h.left, key, val);
        } else if(cmp > 0){
            h.right = put(h.right, key, val);
        } else{
            h.val = val;
        }

        if(isRed(h.right) && !isRed(h.left)) {
            h = leftRotate(h);
        }
        if(isRed(h.left) && isRed(h.left.left)){
            h = rightRotate(h);
        }
        if(isRed(h.left) && isRed(h.right)){
            flipColors(h);
        }

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    public void deleteMin(){
        if(isEmpty()) throw new NoSuchElementException("Symbol table is empty");
        if(!isRed(root.left) && !isRed(root.right)){ //根节点是-2节点
            root.color = RED;
        }
        root = deleteMin(root);
        if(!isEmpty())root.color = BLACK;
    }

    private Node deleteMin(Node h){
        if(h.left == null) return null;
        if(!isRed(h.left) && !isRed(h.left.left)){ //左子节点是-2节点
            h = moveRedLeft(h);
        }
        h.left = deleteMin(h.left);
        return balance(h);
    }

    public void deleteMax(){
        if(isEmpty()) throw new NoSuchElementException("Symbol table is empty");
        if(!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }
        root = deleteMax(root);
        if(!isEmpty()) root.color = BLACK;
    }

    private Node deleteMax(Node h){
        if(isRed(h.left)){
            h = rightRotate(h);
        }
        if(h.right == null){
            return null;
        }
        if(!isRed(h.right) && !isRed(h.right.left)){//右子节点是-2节点
            h = moveRedRight(h);
        }
        h.right = deleteMax(h.right);
        return balance(h);
    }

    public void delete(Key key){
        if(key == null) throw new IllegalArgumentException("argument to get() is null");
        if(!contains(key)) return;

        if(!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }
        root = delete(root, key);
        if(!isEmpty()) root.color = BLACK;
    }

    private Node delete(Node h, Key key){
        if(key.compareTo(h.key) < 0){
            if(!isRed(h.left) && !isRed(h.left.left)){
                h = moveRedLeft(h);
            }
            h.left = delete(h.left, key);
        } else{
            if(isRed(h.left)){
                h = rightRotate(h);
            }
            if(key.compareTo(h.key) == 0 && (h.right == null)){
                return null;
            }
            if(!isRed(h.right) && !isRed(h.right.left)){
                h = moveRedRight(h);
            }
            if(key.compareTo(h.key) == 0){
                Node x = min(h.right);
                h.key = x.key;
                h.val = x.val;
                h.right = deleteMin(h.right);
            } else{
                h.right = delete(h.right, key);
            }
        }
        return balance(h);
    }

    public Key min(){
        if(isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node x){
        if(x.left == null) return x;
        else return min(x.left);
    }

    public Key max(){
        if(isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }

    private Node max(Node x){
        if(x.right == null) return x;
        else return max(x.right);
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


    /*********************************************************
     * Red-black tree helper functions.
     *********************************************************/

    private Node leftRotate(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node rightRotate(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColors(Node h){
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    private Node moveRedLeft(Node h){
        flipColors(h);//合并左右子节点
        if(isRed(h.right.left)){//右子节点不是-2节点
            h.right = rightRotate(h.right);
            h = leftRotate(h);
            flipColors(h);
        }
        return h;
    }

    private Node moveRedRight(Node h){
        flipColors(h);
        if(isRed(h.left.left)){
            h = rightRotate(h);
            flipColors(h);
        }
        return h;
    }

    private Node balance(Node h){
        if(isRed(h.right)) h = leftRotate(h);
        if(isRed(h.left) && isRed(h.left.left)) h = rightRotate(h);
        if(isRed(h.left) && isRed(h.right)) flipColors(h);
        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }
}