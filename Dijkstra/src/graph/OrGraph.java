package graph;

import javax.swing.*;
import java.util.*;

public class OrGraph {
    private int dimension;
    private ArrayList<Vertex> vertexList;
    private int adjacencyMatrix[][];
    private PrintOrGraph printOrGraph;
    public OrGraph(int dimension){
        this.dimension=dimension;
        adjacencyMatrix = new int[getDimension()][getDimension()];
        vertexList = new ArrayList<>();
        printOrGraph = new PrintOrGraph(410,410);
        for (int[] anAdjacencyMatrix : adjacencyMatrix) {
            Vertex vt = new Vertex();
            vertexList.add(vt);
            printOrGraph.addVertex(vt);
            for (int i=0;i<anAdjacencyMatrix.length;i++) {
                anAdjacencyMatrix[i] = -1;
            }
        }

    }
    private int getDimension() {
        return dimension;
    }
    public int addEdge(int from, int to, int weight){
        if(from<dimension && to<dimension && from>=0&&to>=0&&from!=to){
            printOrGraph.addLine(vertexList.get(from).x,vertexList.get(from).y,vertexList.get(to).x,vertexList.get(to).y,weight);
            adjacencyMatrix[from][to]=weight;
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
            printOrGraph.deleteLine(vertexList.get(first).x,vertexList.get(first).y,vertexList.get(second).x,vertexList.get(second).y);
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
        render(printOrGraph);
    }
    private void render(PrintOrGraph printOrGraph){
        JOptionPane.showMessageDialog(null, printOrGraph);
    }
    public void render(){
        render(printOrGraph);
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
        printOrGraph.addVertex(vt);
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
                printOrGraph.deleteLine(vertexList.get(number).x,vertexList.get(number).y,vertexList.get(ar.get(i)).x,vertexList.get(ar.get(i)).y);
            }
            printOrGraph.deleteVertex(vertexList.get(number));
            vertexList.remove(number);
            return 0;
        }
        else{
            System.out.println("Error in deleting Vertex. Illegal vertex number");
            return 1;
        }
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
    public void Dijkstra(int from, int to){
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
        if(isVisited[to]){
            System.out.println("Вес пути из вершины "+from+" в вершину "+to+" равен: " +mass[to]);
        }
        else{
            System.out.println("Нельзя дойти из вершины "+from+" в вершину "+to);
        }
    }
}
