/*三向切分快速排序
*适合处理包含重复元素的数组
*时间复杂度O(N ~ NlgN)，空间复杂度O(lgN)
*不稳定
*/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Quick3way
{
    public static void sort(Comparable[] a)
    {
        StdRandom.shuffle(a);
        sort(a,0,a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi)
    {
        if(lo >= hi) return;
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while(i <= gt){
            int cmp = a[i].compareTo(v);
            if(cmp < 0){
                exch(a,i++,lt++);
            } else if(cmp > 0){
                exch(a,i,gt--);
            } else {
                ++i;
            }
        } 
        sort(a,lo,lt - 1);
        sort(a,gt + 1, hi);
    }

    private static boolean less(Comparable a, Comparable b)
    {
        return (a.compareTo(b) < 0);
    }

    private static void exch(Comparable[] a,int i, int j)
    {
        Comparable v = a[i];
        a[i] = a[j];
        a[j] = v;
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
        sort(a);
        assert isSorted(a);
        show(a);
    }
}