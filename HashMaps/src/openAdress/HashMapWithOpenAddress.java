package openAdress;

public class HashMapWithOpenAddress {
    int maxInsertTry;
    public Integer []mass;
    public HashMapWithOpenAddress(){
        maxInsertTry =0;
        mass = new Integer[1024];
    }
    private int insertion(int elem){
        for(int i=0;i<mass.length;i++){
            int ind = hashFunc(elem,i);
            if(mass[ind]==null){
                mass[ind]=elem;
                return i;
            }
        }
        return 1025;

    }
    public void insertion(int [] arr){
        int maxTryLocal =0;
        for(int ar:arr){
            maxTryLocal=insertion(ar);
            if(maxInsertTry<maxTryLocal){
                maxInsertTry=maxTryLocal;
            }
        }

    }
    public Integer search(Integer elem){
        for(int i=0;i<maxInsertTry;i++){
            int ind = hashFunc(elem,i);

            if(mass[ind]==null){
                return null;
            }
            if(mass[ind].equals(elem)){
                return mass[ind];
            }
        }
        return null;
    }
//    public int nullsNumber(){
//        int numberOfNulls =0;
//        for(int i=0;i<mass.length;i++){
//            if(mass[i]==null){
//                numberOfNulls++;
//            }
//        }
//        return numberOfNulls;
//    }
    public int getMaxInsertTry(){
        return maxInsertTry;
    }
    private int hashFunc(int elem,int chance){
        double a = 0.6180339887;
//        ((elem%571* a)-(int)(elem%571* a))*mass.length koef:0.5, 0.18
        //For elem koef: 0.5, 0.182
//        h=(h+(int)(chance*0.5+chance*chance*0.5))%1000;

        return (int)(((elem%571* a)-(int)(elem%571* a))*mass.length + 0.5*chance + 0.5*chance*chance)%mass.length;
    }

}
