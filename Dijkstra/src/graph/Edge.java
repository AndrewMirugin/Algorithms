package graph;

public class Edge {
    public int from;
    public int to;
    public int weight;
    public boolean passed;
    public Edge(int from, int second, int weight) {
        passed = false;
        this.from = from;
        this.to = second;
        this.weight = weight;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
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
            return from +"-/-"+to;
        }
        return from +"--"+to;
    }
}
