package chain;

public class Node {
    double elem;
    Node next;
    public Node(double value){
        elem = value;
        next= null;
    }

    public double getElem() {
        return elem;
    }

    public void setElem(double elem) {
        this.elem = elem;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
