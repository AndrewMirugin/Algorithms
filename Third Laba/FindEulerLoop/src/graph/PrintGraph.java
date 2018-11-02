package graph;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class PrintGraph extends JComponent {
    private ArrayList<Line2D.Double> lines;
    private ArrayList<Vertex> vertexes;
    PrintGraph(int width,int height){
        super();
        setPreferredSize(new Dimension(width,height));
        lines = new ArrayList<>();
        vertexes = new ArrayList<>();
    }
    public void addLine(int x1,int y1,int x2,int y2){
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
        g.setColor(Color.black);
        for(int i=0;i<vertexes.size();i++){
            g.drawString(Integer.toString(i),vertexes.get(i).x,vertexes.get(i).y-10);
        }
        for(Line2D.Double line:lines){
            g.drawLine((int)line.getX1(),(int)line.getY1(), (int)line.getX2(),(int)line.getY2());
        }
    }
}
