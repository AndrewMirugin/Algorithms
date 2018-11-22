package graph;

import com.sun.istack.internal.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Graph {
    private int dimension;
    private ArrayList<Vertex> vertexList;
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
            for (int i=0;i<anAdjacencyMatrix.length;i++) {
                anAdjacencyMatrix[i] = -1;
            }
        }

    }
    private int getDimension() {
        return dimension;
    }
    public int addEdge(int first, int second, int weight){
        if(first<dimension && second<dimension && first>=0&&second>=0&&first!=second){
            printGraph.addLine(vertexList.get(first).x,vertexList.get(first).y,vertexList.get(second).x,vertexList.get(second).y,weight);
            adjacencyMatrix[first][second]=weight;
            adjacencyMatrix[second][first]=weight;
            return 0;
        }
        else{
            System.out.println("Error in adding edge");
            return 1;
        }
    }
    public int deleteEdge(int first,int second){
        if(first<dimension && second<dimension && first>=0&&second>=0){
            adjacencyMatrix[first][second]=-1;
            adjacencyMatrix[second][first]=-1;
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
        render(printGraph);
    }
    private void render(PrintGraph printGraph){
        JOptionPane.showMessageDialog(null, printGraph);
    }
    public void render(){
        render(printGraph);
    }
    public void addVertex(){
        int length = adjacencyMatrix.length;
        int newIncidMatrix[][]=new int[length+1][length+1];
        for(int i=0;i<length;i++){
            for(int j=0;j<length;j++){
                newIncidMatrix[i][j]= adjacencyMatrix[i][j];
                if(j==length-1){
                    newIncidMatrix[i][j+1]=-1;
                }
            }
        }
        for(int i=0;i<newIncidMatrix[adjacencyMatrix.length].length;i++){
            newIncidMatrix[adjacencyMatrix.length][i]=-1;
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
                            if(j+1!=dimension){
                                newIncidenceMatrix[i][j]= adjacencyMatrix[i][j+1];
                            }
                            else{
                                newIncidenceMatrix[i][j]=-1;
                            }

                        }

                    }
                }
                else if(i>number){
                    for(int j=0;j<dimension;j++){
                        if(j<number){
                            if(i+1!=dimension){
                                newIncidenceMatrix[i][j]= adjacencyMatrix[i+1][j];
                            }
                            else{
                                newIncidenceMatrix[i][j]=-1;
                            }
                        }
                        else if(j>number){
                            if(i+1!=dimension && j+1!=dimension){
                                newIncidenceMatrix[i][j]= adjacencyMatrix[i+1][j+1];
                            }
                            else{
                                newIncidenceMatrix[i][j]=-1;
                            }
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
    private int getWeightOfWayToMostRemoteUsingDijkstra(int from){
        int mass[] = new int[dimension];
        boolean isVisited[]=new boolean[dimension];
        for(int i=0;i<dimension;i++){
            if(i!=from){
                mass[i]=-1;
            }
            else{
                mass[i]=0;
            }
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(from);
        while(!stack.empty()){
            int temp = stack.pop();
            ArrayList<Integer> nearestVertexes = new ArrayList<>(getAdjacency(temp));
            for(int i=0;i<nearestVertexes.size();i++){
                if(!isVisited[nearestVertexes.get(i)]){
                    stack.push(nearestVertexes.get(i));
                }
            }
            for(int i=0;i<nearestVertexes.size();i++){
                if(!isVisited[nearestVertexes.get(i)]){
                    if(mass[nearestVertexes.get(i)]==-1||mass[nearestVertexes.get(i)]>mass[temp]+adjacencyMatrix[temp][nearestVertexes.get(i)]){
                        mass[nearestVertexes.get(i)]=mass[temp]+adjacencyMatrix[temp][nearestVertexes.get(i)];
                    }
                }
            }
            isVisited[temp]=true;
        }
        int max=mass[0];
        for(int i=1;i<mass.length;i++){
            if(max<mass[i]){
                max=mass[i];
            }
        }
        return max;

    }
    public void getEcentricity(){
        int max;
        int weights[]=new int[dimension];
        weights[0]=getWeightOfWayToMostRemoteUsingDijkstra(0);
        max=0;
        for(int i=1;i<dimension;i++){
            weights[i]=getWeightOfWayToMostRemoteUsingDijkstra(i);
            if(weights[max]>weights[i]){
                max=i;
            }
        }
        System.out.println("Лучший перекрёсток под номером "+max);
    }
    private ArrayList<Edge>  createEdgeList(){
        ArrayList<Edge> ar = new ArrayList<>();
        for(Integer i=0;i<dimension;i++){
            for(int j=0;j<dimension;j++){
                if(adjacencyMatrix[i][j]!=-1&&i<j){
                    ar.add(new Edge(i,j,adjacencyMatrix[i][j]));
                }
            }
        }
        return ar;
    }
    private ArrayList<Integer> getAdjacency(int elem){
            ArrayList<Integer> mass = new ArrayList<>();
            for(int i=0;i<dimension;i++){
                if(adjacencyMatrix[elem][i]!=-1){
                    mass.add(i);
                }
            }
            return mass;
    }
}
