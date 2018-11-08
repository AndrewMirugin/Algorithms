package runner;

import graph.Graph;

import java.util.Scanner;

public class Runner {
    public void start(int dimension){
        Graph graph = new Graph(dimension);
        graph.print();
        startMenu(graph);
    }
    private void startMenu(Graph graph){
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println();
            System.out.println("1) Добавить вершину");
            System.out.println("2) Добавить ребро");
            System.out.println("3) Удалить вершину");
            System.out.println("4) Удалить ребро");
            System.out.println("5) Получить остов (Краскал)");
            System.out.println("6) Получить остов (Прим)");
            System.out.println("7) Объединение результатов алгоритмов Краскала и Прима");
            System.out.println("8) Нарисовать граф");
            System.out.println("9) Выход");
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
                    graph.kruskal(null,true);
                    break;
                }

                case 6:{
                    graph.prim(null,true);
                    break;
                }
                case 7:{
                    graph.uniteOfKruskalAndPrim();
                    break;
                }
                case 8:{
                    graph.render();
                    break;
                }
                case 9:{
                    return;
                }
                default:{
                    System.out.println("Введён неправильный пункт меню. Повторите ввод");
                }
            }
        }

    }
}
