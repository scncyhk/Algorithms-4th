import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.StdRandom;

public class SortCompare
{
    public static double time(String alg, Double[] a)
    {
        Stopwatch timer = new Stopwatch();
        if(alg.equals("Insertion")){
            Insertion.sort(a);
        } else if(alg.equals("Selection")){
            Selection.sort(a);
        } else if(alg.equals("Shell")){
            Shell.sort(a);
        }
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int n, int t)
    {
        double total = 0.0;
        Double[] a = new Double[n];
        for(int i = 0; i < t; ++i){
            for(int j = 0; j < n; ++j){
                a[j] = StdRandom.uniform();
            }
            total += time(alg,a);
        }
        return total;
    }

    public static void main(String args[])
    {
        String alg1 = args[0];
        String alg2 = args[1];
        int n = Integer.parseInt(args[2]);
        int t = Integer.parseInt(args[3]);
        double t1 = timeRandomInput(alg1, n, t);
        double t2 = timeRandomInput(alg2, n, t);
        StdOut.printf("For %d random Doubles\n  %s is",n,alg1);
        StdOut.printf(" %.1f times faster than %s\n",t2 / t1,alg2);
    }
}