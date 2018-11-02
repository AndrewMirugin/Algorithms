package graph;

public class Edge {
    public int first;
    public int second;
    public boolean passed;
    public Edge(int first, int second) {
        passed = false;
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        if(passed){
            return first+"-/-"+second;
        }
        return first+"--"+second;
    }
}
