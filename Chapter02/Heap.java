/*堆排序：
*时间复杂度：O(NlgN)，只需要少于（2NlgN+2N）次比较，空间复杂度O(1)
*不稳定
*/

import java.util.Comparator;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Heap{
    private Heap(){}

    public static void sort(Comparable[] a){
        int n = a.length;
        for(int k = n / 2; k >= 1; --k){
            sink(a, k, n);
        }
        while(n > 1){
            exch(a, 1, n--);
            sink(a, 1, n);
        }
    }

    private static void sink(Comparable[] a, int k, int n){
        while(2 * k <= n){
            int i = 2 * k;
            if(i < n && less(a, i, i + 1)){
                ++i;
            }
            if(!less(a, k, i)) break;
            exch(a, k, i);
            k = i;
        }        
    }

    private static boolean less(Comparable[] a, int i, int j){
        return a[i - 1].compareTo(a[j - 1]) < 0;
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i - 1];
        a[i - 1] = a[j - 1];
        a[j - 1] = t;
    }

    private static void show(Comparable[] a){
        for(Comparable c : a){
            StdOut.println(c);
        }
    }

    public static void main(String[] args){
        String[] a = StdIn.readAllStrings();
        Heap.sort(a);
        show(a);
    }
}