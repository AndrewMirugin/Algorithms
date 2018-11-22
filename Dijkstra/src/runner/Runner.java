package runner;

import graph.OrGraph;

import java.util.Scanner;

public class Runner {
    public void start(int dimension){
        OrGraph graph = new OrGraph(dimension);
        graph.print();
        startMenu(graph);
    }
    private void startMenu(OrGraph graph){
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println();
            System.out.println("1) Добавить вершину");
            System.out.println("2) Добавить ребро");
            System.out.println("3) Удалить вершину");
            System.out.println("4) Удалить ребро");
            System.out.println("5) Вес кратчайшего пути из одной вершины в другую");
            System.out.println("6) Нарисовать граф");
            System.out.println("7) Выход");
            int choice = scan.nextInt();
            switch(choice){
                case 1:{
                    graph.addVertex();
                    graph.print();

                    break;
                }
                case 2:{
                    int first;
                    int second;
                    int weight;
                    System.out.println("Введите вершины, между которыми должно быть ребро");
                    first=scan.nextInt();
                    second=scan.nextInt();
                    System.out.println("Введите веc ребра");
                    weight=scan.nextInt();
                    graph.addEdge(first,second,weight);
                    graph.print();
                    break;
                }
                case 3:{
                    int number;
                    System.out.println("Введите номер вершины, которую необходимо удалить: ");
                    number = scan.nextInt();
                    graph.deleteVertex(number);
                    graph.print();
                    break;
                }
                case 4:{
                    int first;
                    int second;
                    System.out.println("Введите вершины, между которыми должно быть удалено ребро");
                    first=scan.nextInt();
                    second=scan.nextInt();
                    graph.deleteEdge(first,second);
                    graph.print();
                    break;
                }
                case 5:{
                    int first;
                    int second;
                    System.out.println("Введите вершину из которой искать");
                    first=scan.nextInt();
                    System.out.println("Введите вершину в которою искать");
                    second=scan.nextInt();
                    graph.Dijkstra(first,second);
                    break;
                }
                case 6:{
                    graph.render();
                    break;
                }
                case 7:{
                    return;
                }
                default:{
                    System.out.println("Введён неправильный пункт меню. Повторите ввод");
                }
            }
        }

    }
}
