package searches;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
//        int a[]={1,2,3,4,5,6,7,8,9};
        long avIntTime=0;
        long avBinTime=0;
        try {
            FileWriter fw = new FileWriter("source/result.txt");//файл для вывода результата
            for(int i=3;i<100;i++){
                int b[]=new int[i];
                for(int j=0;j<i;j++){
                    b[j]=j;
                }
                long sumBinTime=0;
                int value = (b.length/2/2);
                for(int j=0;j<150;j++){
                    long startTime = System.nanoTime();
                    binarySearch(b,value,0,b.length);
                    long endTime = System.nanoTime();
                    sumBinTime+=endTime-startTime;
                }

                avBinTime=sumBinTime/150;
                long sumIntTime=0;
                for(int j=0;j<150;j++){
                    long startTime = System.nanoTime();
                    interpolSearch(b,value,0,b.length-1);
                    long endTime = System.nanoTime();
                    sumIntTime+=endTime-startTime;
                }
                avIntTime=sumIntTime/150;
                System.out.println("Время бинарного: "+Long.toString(avBinTime)+"; Время интерполяционного: "+Long.toString(avIntTime)+ "; Размер массива: "+i);
                fw.write("Время бинарного: "+Long.toString(avBinTime)+"; Время интерполяционного: "+Long.toString(avIntTime)+ "; Размер массива: "+i);
                if (avIntTime>avBinTime){
                    System.out.print("Бинарный лучше");
                    fw.write(". Бинарный лучше");
                }
                System.out.println();
                fw.write("\n");
            }
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int binarySearch(int [] mass,int value, int start,int end){
        if(start>=end){
            return -1;
        }
        int center = start +(end - start)/2;
        if(mass[center]==value)
            return center;
        else if(mass[center]>value){
            return binarySearch(mass,value,start,center);
        }
        else{
           return binarySearch(mass,value,center+1,end);
        }
    }

    public static int interpolSearch(int []mass,int value,int start,int end){
        if(start>=end || value>mass[end]){
            return -1;
        }
        int temp = start+(end - start)*(value-mass[start])/(mass[end]-mass[start]);
        if(value==mass[temp]){
            return temp;
        }
        else if(value>mass[temp]){
            return binarySearch(mass,value,start,temp-1);
        }
        else{
            return binarySearch(mass,value,temp+1,end);
        }
    }

}
