package main;

/*Эйлеров цикл|BFS
1 динамически достраивать граф (если есть список ребер 1 3 без 5 то добавить вершину 1 4
Если инцидентности, то ребро 5 9 на месте должно появиться число )
2 Добавить/удалить ребро/вершину*/

import runner.Runner;

public class Main {
    public static void main(String[] args) {
        Runner run = new Runner();
        run.start(3);
    }
}
