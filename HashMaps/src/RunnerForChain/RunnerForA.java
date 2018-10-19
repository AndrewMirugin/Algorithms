package RunnerForChain;

import chain.HashMapWithChain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class RunnerForA {
    public final static double Knut=0.6180339887;
    public final static double A1=0.74027946; // Подходит
    public final static double A2=0.3141529;  // Подходит
    public final static double A3=0.80392598;

    public void start(){
        //generateIntIntoOutput(50,1000);
        int [][]line=new int[50][1000];
        String numLine;
        File file = new File("source/input.txt");
        Scanner scan;
        Scanner scanner;
        try {
            scan = new Scanner(file);  //Чтение 50 массивов
            for(int i=0;i<50;i++){
                numLine = scan.nextLine();
                scanner = new Scanner(numLine);
                if(i==49){
                    for(int j=0;j<1000;j++){
                        line[i][j]=scanner.nextInt();
                    }
                }
                else{
                    for(int j=0;j<1000;j++){
                        line[i][j]=scanner.nextInt();
                    }
                }
            }

            System.out.println(getAvLengthOfChainForA(Knut,line));
            System.out.println(getAvLengthOfChainForA(A1,line));
            System.out.println(getAvLengthOfChainForA(A2,line));
            System.out.println(getAvLengthOfChainForA(A3,line));
            System.out.println(getMaxLengthOfChainForA(Knut,line));
            System.out.println(getMaxLengthOfChainForA(A1,line));
            System.out.println(getMaxLengthOfChainForA(A2,line));
            System.out.println(getMaxLengthOfChainForA(A3,line));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getAvLengthOfChainForA(double A,int line[][]){
        HashMapWithChain hm[]=new HashMapWithChain[50];
        for(int i=0;i<50;i++){
            hm[i]=new HashMapWithChain(A,1000);
        }
        for(int i=0;i<50;i++){
            hm[i].insert(line[i]);
        }

        int sumMaxLength=0;
        for(int i=0;i<50;i++){
            sumMaxLength+=hm[i].getMaxChainLength();
        }
        return "Average: "+sumMaxLength/50 + "; Sum: " + sumMaxLength;
    }
    public static int getMaxLengthOfChainForA(double A,int line[][]){
        HashMapWithChain hm[]=new HashMapWithChain[50];
        for(int i=0;i<50;i++){
            hm[i]=new HashMapWithChain(A,1000);
        }
        for(int i=0;i<50;i++){
            hm[i].insert(line[i]);
        }

        int MaxLength=0;
        for(int i=0;i<50;i++){
            int temp=hm[i].getMaxChainLength();
            if(MaxLength<temp){
                MaxLength=temp;
            }
        }
        return MaxLength;
    }
    public static void generateIntIntoInput(int numberOfMassivs,int rangeTo){
        try {   //Генерация массивов чисел и их запись в файл input.txt
            FileWriter fw = new FileWriter("source/input.txt");
            Random rand = new Random();
            int temp;
            for (int j = 0; j < numberOfMassivs; j++) {
                for (int i = 0; i < rangeTo; i++) {
                    temp = rand.nextInt(rangeTo + 1);
                    fw.write(Integer.toString(temp));
                    fw.append(" ");
                }
                fw.append("\n");
            }
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished");
    }
}
