package RunnerForAddress;

import openAdress.HashMapWithOpenAddress;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class RunnerForB {
    public void start(){
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


            HashMapWithOpenAddress hm[]=new HashMapWithOpenAddress[50];

            for(int i=0;i<50;i++){
                hm[i]=new HashMapWithOpenAddress();
                hm[i].insertion(line[i]);
            }
            System.out.println("max Try length: "+maxTryLength(hm));
            System.out.println();
            System.out.println(hm[0].search(749));
            System.out.println(hm[0].search(1004));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public int maxTryLength(HashMapWithOpenAddress[] hm){
        int maxLength=0;
        for (int i=0;i<hm.length;i++) {
            System.out.println("hm "+ i+": " + hm[i].getMaxInsertTry());
            if (maxLength < hm[i].getMaxInsertTry()) {
                maxLength = hm[i].getMaxInsertTry();
            }
        }
        return maxLength;
    }
}
