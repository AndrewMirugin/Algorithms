package by.andreyMirugin.main;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
//                try{   //Генерация массивов чисел и их запись в файл output.txt
//                        FileWriter fw = new FileWriter("source/output.txt");
//                        Random rand = new Random();
//                        int temp;
//                        for(int j=0;j<50;j++){
//                            for(int i=0;i<power(10,5);i++){
//                                temp =rand.nextInt(power(10,9)+1);
//                                fw.write(Integer.toString(temp));
//                                fw.append(" ");
//                }
//                fw.append("\n");
//            }
//            fw.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Finished");

//        int b[]={7,4,5,3,7,2,5,9};
//        int a[];
//        a=MergeSort(b);
//        for(int m:a){
//            System.out.print(m);
//        }

        long []bestN=new long[2];
        int [][]line=new int[50][power(10,5)];
        int []mass=new int[power(10,5)];
        long sumTimePerN;
        String numLine;
        long avMergeTime;
        File file = new File("source/output.txt");
        Scanner scan;
        Scanner scanner;
        try {
            FileWriter fw = new FileWriter("source/result.txt");//файл для вывода результата

            scan = new Scanner(file);  //Чтение 50 массивов чисел
            for(int i=0;i<50;i++){
                numLine = scan.nextLine();
                scanner = new Scanner(numLine);
                if(i==49){
                    for(int j=0;j<power(10,5);j++){
                        line[i][j]=scanner.nextInt();
                        mass[i]=line[i][j];
                    }
                }
                else{
                    for(int j=0;j<power(10,5);j++){
                        line[i][j]=scanner.nextInt();
                    }
                }
            }



            sumTimePerN=0;
            for(int j=0;j<50;j++){
                MergeSort(mass);
            }
            for(int i=0;i<100;i++){ //Прогоняю алгоритм 100 раз для получения более точного значения времени
                long startTime = System.nanoTime();
                for(int j=0;j<50;j++){
                    MergeSort(line[j]);
                }
                long endTime = System.nanoTime();
                long totalTime = endTime-startTime;
                sumTimePerN+=totalTime;
            }
            avMergeTime = sumTimePerN/5000;
            System.out.println("Время сортировки слиянием: "+avMergeTime);
            fw.write("Время сортировки слиянием: "+ avMergeTime +";"+"\n");
            for(int n=7;n<=200;n++){
                sumTimePerN=0;
                for(int i=0;i<100;i++){ //Прогоняю алгоритм 100 раз для получения более точного значения времени
                    long startTime = System.nanoTime();
                    for(int j=0;j<50;j++){
                        HybridSort(line[j],n);
                    }
                    long endTime = System.nanoTime();
                    long totalTime = endTime-startTime;
                    sumTimePerN+=totalTime;
                }
                long avTimePerN = sumTimePerN/5000;//Получение среднего времени за сто прогонов алгоритма для отдельного N
                fw.write("\n"+"N: "+ n+"; Время: "+ avTimePerN +";");
                System.out.print("\n"+"N: "+ n+"; Время: "+ avTimePerN +";");

                if(avTimePerN<avMergeTime){
                    fw.write("Лучше, чем слиянием (" + Long.toString(avMergeTime)+ ")");
                    System.out.println("Лучше, чем слиянием (" + Long.toString(avMergeTime)+ ")");
                }
//                if(n==7){
//                    bestN[0]=n;
//                    bestN[1]=avTimePerN;
//                }
//                else{
//                    if(bestN[1]>=avTimePerN){
//                        bestN[0]=n;
//                        bestN[1]=avTimePerN;
//                    }
//                }
            }

            //fw.write("Лучшее время: " + Long.toString(bestN[1]) + " для N = " + Long.toString(bestN[0]));
            fw.close();
            //System.out.println("Лучшее время: " + bestN[1] + " для N = " + bestN[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int power(int number,int pow){
        int result=1;
        for(int i=0;i<pow;i++){
            result*=number;
        }
        return result;
    }

    private static int[] HybridSort(int[] mass,int n){
        if(mass.length>n){
            int []mass1 = new int[mass.length/2];
            System.arraycopy(mass, 0, mass1, 0, mass.length / 2);
            int[]mass2=new int[mass.length-mass.length/2];
            System.arraycopy(mass, mass.length / 2, mass2, 0, mass.length - mass.length / 2);
            return merge(HybridSort(mass1,n),HybridSort(mass2,n));
        }
        else{
            return InnerSort(mass);
        }
    }

    private static int[] MergeSort(int[] mass){
        if(mass.length < 2){
            return mass;
        }
        int []mass1 = new int[mass.length/2];
        System.arraycopy(mass, 0, mass1, 0, mass.length / 2);
        int[]mass2=new int[mass.length-mass.length/2];
        System.arraycopy(mass, mass.length / 2, mass2, 0, mass.length - mass.length / 2);
        return merge(MergeSort(mass1),MergeSort(mass2));
    }

    private static int[] merge(int[]mass1,int[]mass2){
        int length=mass1.length + mass2.length;
        int[]mass=new int[length];
        int p1=0,p2=0;
        for(int i=0;i<length;i++){
            if(p1==mass1.length){
                for(int j=i;j<length;j++){
                    mass[j]=mass2[p2++];
                }
                break;
            }
            else if(p2==mass2.length){
                for(int j=i;j<length;j++){
                    mass[j]=mass1[p1++];
                }
                break;
            }
            else{
                if(mass1[p1]<mass2[p2]){
                    mass[i]=mass2[p2];
                    p2+=1;
                }
                else{
                    mass[i]=mass1[p1];
                    p1+=1;
                }
            }
        }
        return mass;
    }

    private static int[] InnerSort(int[] mass){
        int curVal,temp;
        for(int i=1;i<mass.length;i++){
            curVal = mass[i];
            for(temp=i;temp>0 && mass[temp-1]>curVal;temp--){
                mass[temp]=mass[temp-1];
            }
            mass[temp]=curVal;
        }
        return mass;
    }
}
