package graph;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class PrintOrGraph extends JComponent {
    private ArrayList<Line2D.Double> lines;
    private ArrayList<Vertex> vertexes;
    private ArrayList<Integer> weights;
    private Color color = Color.black;
    PrintOrGraph(int width, int height){
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
                    lines.get(i).getX2()==x2&&lines.get(i).getY2()==y2){
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
        int x1 = vertexes.get(edge.from).x;
        int y1 = vertexes.get(edge.from).y;
        int x2 = vertexes.get(edge.to).x;
        int y2 = vertexes.get(edge.to).y;
        int weight = edge.weight;
        for(int i=0;i<lines.size();i++){
            if(lines.get(i).getX1()==x1 && lines.get(i).getY1()==y1&&
                    lines.get(i).getX2()==x2&&lines.get(i).getY2()==y2){
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
                    lines.get(i).getX2()==x2&&lines.get(i).getY2()==y2){
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
            double length=Math.sqrt((lines.get(i).getX2()-lines.get(i).getX1())*(lines.get(i).getX2()-lines.get(i).getX1())+
                    (lines.get(i).getY2()-lines.get(i).getY1())*(lines.get(i).getY2()-lines.get(i).getY1()));
            double arrowLen=length/10;
            double dx=(lines.get(i).getX1()-lines.get(i).getX2());
            double dy=(lines.get(i).getY1()-lines.get(i).getY2());
            double norm=Math.sqrt(dx*dx+dy*dy);
            double uDx=dx/norm;
            double uDy=dy/norm;
            double ax= uDx*(Math.sqrt(6)+Math.sqrt(2))/4-uDy*(Math.sqrt(6)-Math.sqrt(2))/4;
            double ay= uDy*(Math.sqrt(6)+Math.sqrt(2))/4+uDx*(Math.sqrt(6)-Math.sqrt(2))/4;
            double bx= uDx*(Math.sqrt(6)+Math.sqrt(2))/4+uDy*(Math.sqrt(6)-Math.sqrt(2))/4;
            double by= uDy*(Math.sqrt(6)+Math.sqrt(2))/4-uDx*(Math.sqrt(6)-Math.sqrt(2))/4;
            g.setColor(color);
            g.drawLine((int)lines.get(i).getX1(),(int)lines.get(i).getY1(), (int)lines.get(i).getX2(),(int)lines.get(i).getY2());
            g.drawLine((int)lines.get(i).getX2(),(int)lines.get(i).getY2(),(int)(lines.get(i).getX2()+arrowLen*ax),(int)(lines.get(i).getY2()+arrowLen*ay));
            g.drawLine((int)lines.get(i).getX2(),(int)lines.get(i).getY2(),(int)(lines.get(i).getX2()+arrowLen*bx),(int)(lines.get(i).getY2()+arrowLen*by));
            g.setColor(Color.red);
            g.drawString(weights.get(i).toString(),(int)(lines.get(i).getX2()+arrowLen*2*ax),(int)(lines.get(i).getY2()+arrowLen*2*ay));

        }
    }
}
