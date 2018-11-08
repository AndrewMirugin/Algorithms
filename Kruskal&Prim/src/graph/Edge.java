package graph;

public class Edge {
    public int first;
    public int second;
    public int weight;
    public boolean passed;
    public Edge(int first, int second, int weight) {
        passed = false;
        this.first = first;
        this.second = second;
        this.weight = weight;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isPassed() {
        return passed;
    }

    @Override
    public String toString() {
        if(passed){
            return first+"-/-"+second;
        }
        return first+"--"+second;
    }
}
