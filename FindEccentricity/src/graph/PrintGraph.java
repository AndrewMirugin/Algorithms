package graph;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class PrintGraph extends JComponent {
    private ArrayList<Line2D.Double> lines;
    private ArrayList<Vertex> vertexes;
    private ArrayList<Integer> weights;
    private Color color = Color.black;
    PrintGraph(int width,int height){
        super();
        setPreferredSize(new Dimension(width,height));
        lines = new ArrayList<>();
        vertexes = new ArrayList<>();
        weights = new ArrayList<>();
    }
    public void setColor(Color c){
        color = c;
    }
    public void addLine(int x1,int y1,int x2,int y2,int weight){
        boolean isExist = false;
        for(int i=0;i<lines.size();i++){
            if(lines.get(i).getX1()==x1 && lines.get(i).getY1()==y1&&
                    lines.get(i).getX2()==x2&&lines.get(i).getY2()==y2||
                    lines.get(i).getX1()==x2 && lines.get(i).getY1()==y2&&
                            lines.get(i).getX2()==x1&&lines.get(i).getY2()==y1){
                isExist=true;
                break;
            }
        }
        if(!isExist){
            lines.add(new Line2D.Double(x1,y1,x2,y2));
            weights.add(weight);
        }
        repaint();
    }
    public void addLine(Edge edge){
        boolean isExist = false;
        int x1 = vertexes.get(edge.first).x;
        int y1 = vertexes.get(edge.first).y;
        int x2 = vertexes.get(edge.second).x;
        int y2 = vertexes.get(edge.second).y;
        int weight = edge.weight;

        for(int i=0;i<lines.size();i++){
            if(lines.get(i).getX1()==x1 && lines.get(i).getY1()==y1&&
                    lines.get(i).getX2()==x2&&lines.get(i).getY2()==y2||
                    lines.get(i).getX1()==x2 && lines.get(i).getY1()==y2&&
                            lines.get(i).getX2()==x1&&lines.get(i).getY2()==y1){
                isExist=true;
                break;
            }
        }
        if(!isExist){
            lines.add(new Line2D.Double(x1,y1,x2,y2));
            weights.add(weight);
        }
        repaint();
    }
    public void addVertex(Vertex vt){
        vertexes.add(vt);
        repaint();
    }
    public void deleteLine(double x1,double y1,double x2,double y2){
        for(int i=0;i<lines.size();i++){
            if(lines.get(i).getX1()==x1 && lines.get(i).getY1()==y1&&
                    lines.get(i).getX2()==x2&&lines.get(i).getY2()==y2||
                    lines.get(i).getX1()==x2 && lines.get(i).getY1()==y2&&
                    lines.get(i).getX2()==x1&&lines.get(i).getY2()==y1){
                lines.remove(i);
                weights.remove(i);
                break;
            }
        }
        repaint();
    }
    public void deleteVertex(Vertex vt){
        vertexes.remove(vt);
        repaint();
    }
    public void paintComponent(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0,0,getWidth(),getHeight());
//        Dimension d = getPreferredSize();
        g.setColor(color);
        for(int i=0;i<vertexes.size();i++){
            if(vertexes.get(i).y>20){
                g.drawString(Integer.toString(i),vertexes.get(i).x,vertexes.get(i).y-10);
            }
            else{
                g.drawString(Integer.toString(i),vertexes.get(i).x,vertexes.get(i).y+10);
            }

        }
        for(int i=0;i<lines.size();i++){
            g.setColor(color);
            g.drawLine((int)lines.get(i).getX1(),(int)lines.get(i).getY1(), (int)lines.get(i).getX2(),(int)lines.get(i).getY2());
            g.setColor(Color.red);
            g.drawString(weights.get(i).toString(),(int)((lines.get(i).getX1()+lines.get(i).getX2())/2),(int)((lines.get(i).getY1()+lines.get(i).getY2())/2));
        }
    }
}
