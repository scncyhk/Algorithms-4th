/*归并排序
*时间复杂度O(NlgN),空间复杂度O(N)
*不稳定
*/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Merge
{
    private static Comparable[] aux;

    //自顶向下归并
    public static void sort(Comparable[] a)
    {
        aux = new Comparable[a.length];
        sort(a,0,a.length - 1);
    }

    //自底向上归并
    public static void sortBU(Comparable[] a)
    {
        int n = a.length;
        aux = new Comparable[n];
        for(int sz = 1; sz < n; sz *= 2){
            for(int lo = 0; lo < n - sz; lo += 2 * sz){
                merge(a,lo,lo + sz - 1, Math.min(lo + 2 * sz - 1, n - 1));
            }
        }
    }

    private static void sort(Comparable[] a, int lo, int hi)
    {
        if(lo >= hi){
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a,lo,mid);
        sort(a,mid + 1,hi);
        merge(a,lo,mid,hi);
    }

    private static void merge(Comparable[] a,int lo, int mid, int hi)
    {
        int i = lo, j = mid + 1;
        for(int k = lo; k <= hi; ++k){
            aux[k] = a[k];
        }
        for(int k = lo; k <= hi; ++k){
            if(i > mid){
                a[k] = aux[j++];
            } else if(j > hi){
                a[k] = aux[i++];
            } else if(less(aux[i],aux[j])){
                a[k] = aux[i++];
            } else {
                a[k] = aux[j++];
            }
        }
    }

    private static boolean less(Comparable a, Comparable b)
    {
        return (a.compareTo(b) < 0);
    }

    private static void show(Comparable[] a)
    {
        for(int i = 0; i < a.length; ++i){
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a)
    {
        for(int i = 1; i < a.length; ++i){
            if(less(a[i],a[i - 1])){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args)
    {
        String[] a = In.readStrings();
        sortBU(a);
        assert isSorted(a);
        show(a);
    }
}