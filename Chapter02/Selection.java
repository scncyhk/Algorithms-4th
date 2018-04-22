/**选择排序算法
*N次交换，N^2/2次比较
*时间复杂度O(N^2)
*特点：1）不能利用数据的初始状态；2）数据移动是最少的，是线性的。
*不稳定
*-encoding utf-8
*/
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Selection
{
    public static void sort(Comparable[] a)
    {
        for(int i = 0; i < a.length; ++i){
            int min = i;
            for(int j = i + 1; j < a.length; ++j){
                if(less(a[j],a[min])){
                    min = j;
                }
            }
            exch(a,i,min);
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