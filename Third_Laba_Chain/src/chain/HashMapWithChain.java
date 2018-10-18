package chain;

public class HashMapWithChain {
    private double A;
    private Chain mass[];
    public HashMapWithChain(double A,int length){
        mass=new Chain[length];
        this.A=A;
    }

    public void insert(double elem){

        int index = hashFunc(elem);
        if(mass[index]==null){
            mass[index]=new Chain();
            mass[index].insertion(elem);
        }
        else{
            mass[index].insertion(elem);
        }
    }
    public void insert(int[] mass){
        for (int mas : mass) {
            insert(mas);
        }
    }

    public Node get(double value){
        int index = hashFunc(value);
        if(mass[index]!=null){
            return mass[index].search(value);
        }
        else{
            return null;
        }
    }

    public int getMaxChainLength(){
        int maxLength=0;
        for(int i=0;i<mass.length;i++){
            if(mass[i]!=null){
                if(maxLength<mass[i].length){
                    maxLength=mass[i].length;
                }
            }
        }
        return maxLength;
    }



    private int hashFunc(double elem){
        return (int)(((elem%571*A)-(int)(elem%571*A))*mass.length);
    }
}
