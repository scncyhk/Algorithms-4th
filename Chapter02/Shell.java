/*希尔排序
*时间复杂度O(NlogN ~ N6/5)
*不稳定
*/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Shell
{
    public static void sort(Comparable[] a)
    {
        int n = a.length;
        int h = 1;
        while(h < n / 3){
            h = 3 * h + 1;
        }
        while(h >= 1){
            for(int i = h; i < n; ++i){
                for(int j = i; j >= h && less(a[j],a[j - h]); j -= h){
                    exch(a,j,j - h);
                }
            }
            h /= 3;
        }
    }

    public static boolean less(Comparable a, Comparable b)
    {
        return (a.compareTo(b) < 0);
    }

    public static void exch(Comparable[] a, int i, int j)
    {
        Comparable b = a[i];
        a[i] = a[j];
        a[j] = b;
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