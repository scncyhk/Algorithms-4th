/*插入排序
*最好情况下比较N-1次，交换0次；最坏情况下比较和交换N^2/2次
*时间复杂度O(N~N^2)，取决与输入的排列情况
*适用于部分有序的数组排序
*稳定的
*/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Insertion
{
    public static void sort(Comparable[] a)
    {
        int n = a.length;
        for(int i = 1; i < n; ++i){
            for(int j = i; j > 0 && less(a[j],a[j - 1]); --j){
                exch(a,j,j - 1);
            }
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