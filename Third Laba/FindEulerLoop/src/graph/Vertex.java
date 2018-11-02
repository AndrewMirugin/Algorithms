package graph;

import java.util.Random;

public class Vertex {
    public int x;
    public int y;

    public Vertex(){
        Random rand = new Random();
        x= rand.nextInt(390);
        y=rand.nextInt(390);
    }
}
