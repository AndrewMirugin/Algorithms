package main;

//const = 571 - prime number
import chain.HashMapWithChain;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public final static double Knut=0.6180339887;
    public final static double A1=0.74027946;
    public final static double A2=0.238404784;
    public final static double A3=0.89392598;
    public static void main(String[] args) {
        //generateIntIntoOutput(50,1000);
        int [][]line=new int[50][1000];
        String numLine;
        File file = new File("source/output.txt");
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
    public static void generateIntIntoOutput(int numberOfMassivs,int rangeTo){
        try {   //Генерация массивов чисел и их запись в файл output.txt
            FileWriter fw = new FileWriter("source/output.txt");
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
