/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.rockengine;

import asteroids.elements.Element;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author rafael
 */
public class DrawableCanvasImpl implements Drawable{
    
    private GraphicsContext context;
    
    @Override
    public void setDrawer(Object obj){
        if (!(obj instanceof GraphicsContext)){
            return;
        }
        
        context = (GraphicsContext) obj;
    }
    
    @Override
    public void draw(Node ... nodes){
        
    }
    
    @Override
    public void draw(List<Element> elements){
        for (Element element: elements){
            List<Point> points = Point.buildList(element.getPoints());
            double [] xCoordinates = new double [points.size()];
            double [] yCoordinates = new double [points.size()];
            for (int index=0; index < points.size(); index++){
                xCoordinates[index] = points.get(index).getX();
                yCoordinates[index] = points.get(index).getY();
            }
            
            context.fillPolygon(xCoordinates, yCoordinates, points.size());
        }
    }
    
    @Override
    public void clear(){
        
    }
}
