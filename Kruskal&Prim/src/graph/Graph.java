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
    public void uniteOfKruskalAndPrim(){
        PrintGraph pg = new PrintGraph(410,410);
        for(Vertex vr:vertexList){
            pg.addVertex(vr);
        }
        ArrayList<Edge> prim = new ArrayList<>(prim(null,false));
        ArrayList<Edge> kruskal = new ArrayList<>(kruskal(null,false));
        for (Edge aPrim : prim) {
            pg.addLine(aPrim);
        }
        for (Edge aKruskal : kruskal) {
            pg.addLine(aKruskal);
        }
        render(pg);
    }
    public ArrayList<Edge> kruskal(@Nullable Color color,boolean isPrint){
        if(getDimension()<=1){
            render();
            return createEdgeList();
        }
        PrintGraph pg = new PrintGraph(410,410);
        if(color!=null){
            pg.setColor(color);
        }
        for(Vertex vr:vertexList){
            pg.addVertex(vr);
        }
        int vertexes[]=new int[dimension];
        for(int i=0;i<dimension;i++){
            vertexes[i]=i;
        }
        ArrayList<Edge> ostovTree = new ArrayList<>();
        ArrayList<Edge> edgeList = new ArrayList<>(createEdgeList());
        edgeList.sort(Comparator.comparing(Edge::getWeight));
        int maxSize = edgeList.size();
        ostovTree.add(edgeList.get(0));
        pg.addLine(edgeList.get(0));
        int firstVertex=edgeList.get(0).first;
        int secondVertex=edgeList.get(0).second;
        vertexes[secondVertex]=vertexes[firstVertex];
        edgeList.remove(0);
        for(int i=0;ostovTree.size()!=dimension-1||ostovTree.size()==maxSize;i++){
            Edge tek = new Edge(edgeList.get(0).first,edgeList.get(0).second,edgeList.get(0).weight);
            if(vertexes[tek.first]==vertexes[tek.second]){
                edgeList.remove(0);
            }
            else{
                pg.addLine(vertexList.get(tek.first).x,vertexList.get(tek.first).y,vertexList.get(tek.second).x,vertexList.get(tek.second).y,tek.weight);
                render(pg);
                ostovTree.add(tek);
                int findAndReplaceElem = vertexes[tek.second];
                for(int j=0;j<vertexes.length;j++){
                    if(vertexes[j]==findAndReplaceElem){
                        vertexes[j]=vertexes[tek.first];
                    }
                }
            }
        }
        if(isPrint){
            render(pg);
        }
        return ostovTree;
    }
    public ArrayList<Edge> prim(@Nullable Color color,boolean isPrint){
        if(getDimension()<=1){
            render();
            return createEdgeList();
        }
        ArrayList<Integer> notAddedVertexes = new ArrayList<>();
        for(int i=0;i<getDimension();i++){
            notAddedVertexes.add(i);
        }
        PrintGraph pg = new PrintGraph(410,410);
        if(color!=null){
            pg.setColor(color);
        }
        for(Vertex vr:vertexList){
            pg.addVertex(vr);
        }
        ArrayList<Edge> ostovTree = new ArrayList<>();
        ArrayList<Edge> edgeList = new ArrayList<>(createEdgeList());
        edgeList.sort(Comparator.comparing(Edge::getWeight));
        ostovTree.add(edgeList.get(0));
        pg.addLine(edgeList.get(0));
        notAddedVertexes.remove((Integer)edgeList.get(0).first);
        notAddedVertexes.remove((Integer)edgeList.get(0).second);
        edgeList.remove(0);
        while(!notAddedVertexes.isEmpty()){
            for(int i=0;i<edgeList.size();i++){
                int indexOfFirstVertex = notAddedVertexes.indexOf(edgeList.get(i).first);
                int indexOfSecondVertex = notAddedVertexes.indexOf(edgeList.get(i).second);
                if(indexOfFirstVertex==-1 &&indexOfSecondVertex!=-1){
                    pg.addLine(edgeList.get(i));
                    render(pg);
                    ostovTree.add(edgeList.get(i));
                    notAddedVertexes.remove((Integer)edgeList.get(i).second);
                    edgeList.remove(i);
                    break;
                } else if(indexOfFirstVertex!=-1 && indexOfSecondVertex==-1){
                    pg.addLine(edgeList.get(i));
                    ostovTree.add(edgeList.get(i));
                    notAddedVertexes.remove((Integer)edgeList.get(i).first);
                    edgeList.remove(i);
                    break;
                }
            }
        }
        if(isPrint){
            render(pg);
        }
        return ostovTree;
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
