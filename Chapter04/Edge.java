public class Edge implements Comparable<Edge>{
    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v, int w, double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight(){
        return weight;
    }

    public int either(){
        return v;
    }

    public int other(int s){
        if(s == v) return w;
        else if(s == w) return v;
        else throw new RuntimeException("Inconsistent edge");
    }

    public int compareTo(Edge that){
        if(this.weight() < that.weight()) return -1;
        else if(this.weight() > that.weight()) return 1;
        else return 0;
    }

    public String toString(){
        String s = v +"-" + w + " " + weight;
        return s;
    }
}