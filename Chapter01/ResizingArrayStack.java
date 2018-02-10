import java.util.Iterator;

public class ResizingArrayStack<T> implements Iterable<T>
{
    private T[] a;
    private int N;

    public ResizingArrayStack(){
        a = (T[]) new Object[1];
        N = 0;
    }
    public boolean isEmpty(){
        return N == 0;
    }
    public int size(){
        return N;
    }
    private void resize(int max){
        T[] t = (T[]) new Object[max];
        for(int i = 0; i < a.length; ++i){
            t[i] = a[i];
        }
        a = t;
    }
    public void push(T t){
        if(N == a.length){
            resize(2 * a.length);
        }
        a[N++] = t;
    }
    public T pop(){
        T t = a[--N];
        a[N] = null;
        if(N > 0 && N == a.length / 4){
            resize(a.length / 2);
        }
        return t;
    }
    public Iterator<T> iterator(){
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<T> 
    {
        private int i = N;
        public boolean hasNext(){
            return i > 0;
        }
        public T next(){
            return a[--i];
        }
        public void remove(){
            ;
        }
    }

    public static void main(String[] args){
        ResizingArrayStack<Integer> ras = new ResizingArrayStack<>();
        ras.push(1);
        ras.push(2);
        ras.push(3);
        for(int i : ras){
            System.out.println(i);
        }
    }
}