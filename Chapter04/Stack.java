import java.util.Iterator;

public class Stack<E> implements Iterable<E>
{
    private class Node
    {
        E e;
        Node next;
    }

    private Node first;
    private int N;

    public Stack(){
        first = null;
        N = 0;
    }
    public void push(E e){
        Node oldFirst = first;
        first = new Node();
        first.e = e;
        first.next = oldFirst;
        ++N;
    }
    public E pop(){
        E e = first.e;
        first = first.next;
        --N;
        return e;
    }
    public boolean isEmpty(){
        return first == null;
    }
    public int size(){
        return N;
    }
    public Iterator<E> iterator(){
        return new StackIterator();
    }

    private class StackIterator implements Iterator<E> 
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
        public void remove(){
            ;
        }
    }

    public static void main(String args[]){
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(2);
        s.push(3);
        for(int i : s){
            System.out.println(i);
        }
        s.pop();
        for(int i : s){
            System.out.println(i);
        }
    }
}