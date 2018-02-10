import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Queue<E> implements Iterable<E>
{
    private class Node
    {
        E e;
        Node next;
    }

    private Node first;
    private Node last;
    private int N;

    public Queue(){
        first = null;
        last = null;
        N = 0;
    }
    public boolean isEmpty(){
        return first == null;
    }
    public int size(){
        return N;
    }
    public void enqueue(E e){
        Node oldLast = last;
        last = new Node();
        last.e = e;
        last.next = null;
        if(isEmpty()){
            first = last;
        } else {
            oldLast.next = last;
        }
        ++N;
    }
    public E dequeue(){
        E e = first.e;
        first = first.next;
        if(isEmpty()){
            last = null;
        }
        --N;
        return e;
    }
    public Iterator<E> iterator(){
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<E>
    {
        private Node f = first;
        public boolean hasNext(){
            return f != null;
        }
        public E next(){
            E e = f.e;
            f = f.next;
            return e;
        }
        public void remove(){}
    }

    public static void main(String args[]){
        Queue<String> q = new Queue<>();
        q.enqueue("to");
        q.enqueue("be");
        for(String s : q){
            StdOut.print(s + " ");
        }
        q.dequeue();
        StdOut.println();
        for(String s : q){
            StdOut.print(s + " ");
        }
        StdOut.println("(" + q.size() + " left on queue.)");
    }
}