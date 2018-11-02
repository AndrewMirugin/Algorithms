package graph;

import javax.swing.*;
import java.util.Queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class Graph {
    private int dimension;
    private int shares[];
    private ArrayList<Vertex> vertexList;
    private ArrayList<ArrayList<Integer>> connectedComponent;
    private int adjacencyMatrix[][];
    private PrintGraph printGraph;
    public Graph(int dimension){

        this.dimension=dimension;
        adjacencyMatrix = new int[getDimension()][getDimension()];
        vertexList = new ArrayList<>();
        printGraph = new PrintGraph(410,410);
        for (int[] anAdjacencyMatrix : adjacencyMatrix) {
            Vertex vt = new Vertex();
            vertexList.add(vt);
            printGraph.addVertex(vt);
            for (int ain : anAdjacencyMatrix) {
                ain = 0;
            }
        }

    }
    public int getDimension() {
        return dimension;
    }
    public int addEdge(int first, int second){
        if(first<dimension && second<dimension && first>=0&&second>=0&&first!=second){
            printGraph.addLine(vertexList.get(first).x,vertexList.get(first).y,vertexList.get(second).x,vertexList.get(second).y);
            adjacencyMatrix[first][second]=1;
            adjacencyMatrix[second][first]=1;
            return 0;
        }
        else{
            System.out.println("Error in adding edge");
            return 1;
        }
    }
    public int deleteEdge(int first,int second){
        if(first<dimension && second<dimension && first>=0&&second>=0){
            adjacencyMatrix[first][second]=0;
            adjacencyMatrix[second][first]=0;
            printGraph.deleteLine(vertexList.get(first).x,vertexList.get(first).y,vertexList.get(second).x,vertexList.get(second).y);
            return 0;
        }
        else{
            System.out.println("Error in deleting edge");
            return 1;
        }
    }
    public void print(){
        for(int i=0;i<dimension;i++){
            System.out.print("(");
            for(int j=0;j<dimension;j++){
                if(j!=dimension-1){
                    System.out.print(adjacencyMatrix[i][j]+", ");
                }
                else{
                    System.out.print(adjacencyMatrix[i][j]+")");
                }
            }
            System.out.println();
        }
        System.out.println();
        render();
    }
    public void render(){
        JOptionPane.showMessageDialog(null, printGraph);
    }
    public void addVertex(){
        int length = adjacencyMatrix.length;
        int newIncidMatrix[][]=new int[length+1][length+1];
        for(int i=0;i<length;i++){
            for(int j=0;j<length;j++){
                newIncidMatrix[i][j]= adjacencyMatrix[i][j];
                if(j==length-1){
                    newIncidMatrix[i][j+1]=0;
                }
            }
        }
        for(int nim:newIncidMatrix[adjacencyMatrix.length]){
            nim=0;
        }
        Vertex vt = new Vertex();
        vertexList.add(vt);
        printGraph.addVertex(vt);
        adjacencyMatrix = new int[length+1][length+1];
        adjacencyMatrix = newIncidMatrix;
        dimension++;
    }
    public int deleteVertex(int number){
        if(number<dimension && number>=0){
            dimension--;
            int newIncidenceMatrix[][] = new int[dimension][dimension];
            for(int i=0;i<dimension;i++){
                if(i<number){
                    for(int j=0;j<dimension;j++){
                        if(j<number){
                            newIncidenceMatrix[i][j]= adjacencyMatrix[i][j];
                        }
                        else if(j>number){
                            newIncidenceMatrix[i][j]= adjacencyMatrix[i][j+1];
                        }

                    }
                }
                else if(i>number){
                    for(int j=0;j<dimension;j++){
                        if(j<number){
                            newIncidenceMatrix[i][j]= adjacencyMatrix[i+1][j];
                        }
                        else if(j>number){
                            newIncidenceMatrix[i][j]= adjacencyMatrix[i+1][j+1];
                        }
                    }
                }
            }
            ArrayList<Integer> ar = getAdjacency(number);
            adjacencyMatrix = new int[dimension][dimension];
            adjacencyMatrix = newIncidenceMatrix;
            for(int i=0;i<ar.size();i++){
                printGraph.deleteLine(vertexList.get(number).x,vertexList.get(number).y,vertexList.get(ar.get(i)).x,vertexList.get(ar.get(i)).y);
            }
            printGraph.deleteVertex(vertexList.get(number));
            vertexList.remove(number);
            return 0;
        }
        else{
            System.out.println("Error in deleting Vertex. Illegal vertex number");
            return 1;
        }
    }
    public void bfs() {
        ArrayList<Integer> firstShare = new ArrayList<>();
        ArrayList<Integer> secondShare = new ArrayList<>();
        connectedComponent = new ArrayList<>();
        ArrayList<Integer> spisOfNotAddedVertexes = new ArrayList<>();
        boolean isBichromatic = true;
        for (int i = 0; i < dimension; i++)
            spisOfNotAddedVertexes.add(i);
        for (int i = 0; i < dimension; i++) {
            if (!spisOfNotAddedVertexes.isEmpty()) {
                connectedComponent.add(new ArrayList<>());
                int first = spisOfNotAddedVertexes.get(0);
                connectedComponent.get(connectedComponent.size() - 1).add(first);
                spisOfNotAddedVertexes.remove(0);
                Queue<Integer> queue = new LinkedList<>();
                queue.add(first);
                firstShare.add(first);
                while (!queue.isEmpty()) {
                    int temp = queue.poll();
                    ArrayList<Integer> adjacencyList = getAdjacency(temp);
                    if (adjacencyList.isEmpty()) {
                        continue;
                    }
                    for (int el : adjacencyList) {
                        if (spisOfNotAddedVertexes.indexOf(el) != -1) {
                            if(secondShare.indexOf(temp)!=-1){
                                firstShare.add(el);
                            }
                            else{
                                secondShare.add(el);
                            }
                            connectedComponent.get(connectedComponent.size() - 1).add(el);
                            spisOfNotAddedVertexes.remove(Integer.valueOf(el));
                            queue.add(el);
                        }
                        else{
                            if(secondShare.indexOf(temp)!=-1&&secondShare.indexOf(el)!=-1){
                                isBichromatic = false;
                            }
                            else if(firstShare.indexOf(temp)!=-1&&firstShare.indexOf(el)!=-1){
                                isBichromatic = false;
                            }
                        }
                    }
                }
            } else {
                break;
            }
        }
        if(connectedComponent.size()!=1){
            isBichromatic=false;
        }
        System.out.println();
        for (int i = 0; i < connectedComponent.size(); i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < connectedComponent.get(i).size(); j++) {
                System.out.print(connectedComponent.get(i).get(j) + " ");
            }
            System.out.println();
        }
        System.out.println();
        if(isBichromatic){
            System.out.print("First share:");
            for(int i:firstShare){
                System.out.print(i+" ");
            }
            System.out.println();
            System.out.print("Second share:");
            for(int i:secondShare){
                System.out.print(i+" ");
            }
            System.out.println();
        }
        else{
            System.out.println("Not bichromatic");
        }
    }

    private int getDeg(int number){
        int deg=0;
        for(int i=0;i<dimension;i++){
            if(adjacencyMatrix[number][i]==1){
                deg+=1;
            }
        }
        return deg;
    }
    private ArrayList<Edge>  createEdgeList(){
        ArrayList<Edge> ar = new ArrayList<>();
        for(Integer i=0;i<dimension;i++){
            for(int j=0;j<dimension;j++){
                if(adjacencyMatrix[i][j]==1&&i<j){
                    ar.add(new Edge(i,j));
                }
            }
        }
        return ar;
    }
    private ArrayList<Integer> getAdjacency(int elem){
            ArrayList<Integer> mass = new ArrayList<>();
            for(int i=0;i<dimension;i++){
                if(adjacencyMatrix[elem][i]==1){
                    mass.add(i);
                }
            }
            return mass;
    }
}
