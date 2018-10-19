package main;

//const = 571 - prime number

import RunnerForAddress.RunnerForB;
import RunnerForChain.RunnerForA;

public class Main {

    public static void main(String[] args) {
        RunnerForA runnerForA = new RunnerForA();
        RunnerForB runnerForB = new RunnerForB();
        runnerForA.start();
        System.out.println();
        runnerForB.start();

    }

}
