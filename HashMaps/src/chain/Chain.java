package chain;

public class Chain{
    Node first;
    Node last;
    int length=0;

    public void insertion(double value){
        length+=1;
        if(first==null){
            first=new Node(value);
            last=first;
        }
        else{
            Node newElem = new Node(value);
            last.next = newElem;
            last = newElem;
        }
    }
    public Node search(double value){
        Node temp = search(first,value);
        if(temp!=null){
            return temp;
        }
        else{
            return null;
        }
    }
    public void print(){
        if(first!=null){
            print(first);
            System.out.println();
            System.out.println("Length: " + length);
        }
        else{
            System.out.println("No elements");
        }

    }
    public int delete(double value){//Return 0 if ok, return 1 if not found
        if(first!=null&&first.getElem()!=value){
            return delete(first,value);
        }
        else{
            if(first==null){
                return 1;
            }
            else{
                if(last.equals(first)){
                    last=null;
                    first=null;
                    length--;
                    return 0;
                }
                else{
                    Node nd = first;
                    first=nd.getNext();
                    length--;
                    return 0;
                }
            }
        }
    }

    private int delete(Node nd, double value){
        if(nd.getNext()==null){
           return 1;
        }
        if(nd.getNext().getElem()!=value){
            return delete(nd.getNext(),value);
        }
        else{
            if(last.equals(nd.getNext())){
                last=nd;
                nd.setNext(null);
                length--;
                return 0;
            }
            else{
                nd.setNext(nd.getNext().getNext());
                length--;
                return 0;
            }
        }
    }
    private Node search(Node nd,double value){
        if(nd==null){
            return null;
        }
        if(nd.getElem()==value){
            return nd;
        }
        else{
            return search(nd.getNext(),value);
        }
    }
    private void print(Node nd){
        System.out.print(nd.getElem());
        if(nd.getNext()!=null){
            System.out.print(" -> ");
            print(nd.getNext());
        }
    }


}

