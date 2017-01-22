/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.rockengine;

import asteroids.configuration.Properties;
import asteroids.elements.Element;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author rafael
 */
public class DrawableCanvasImpl implements Drawable {

    private GraphicsContext context;
    private Properties properties;

    public DrawableCanvasImpl(Properties properties){
        setProperties(properties);
    }
    
    @Override
    public void setDrawer(Object obj) {
        if (!(obj instanceof GraphicsContext)) {
            return;
        }

        context = (GraphicsContext) obj;
    }

    @Override
    public void draw(Node... nodes) {
        for (Node node: nodes){
            if (node instanceof Element){
                drawElement((Element) node);
            }
        }
    }

    @Override
    public void draw(List<Element> elements) {
        for (Element element : elements) {
            drawElement(element);
        }
    }

    @Override
    public void clear() {
        context.clearRect(0, 0, properties.getWidth(), properties.getHeight());
    }

    private void drawElement(Element element) {
        List<Point> points = Point.buildList(element.getPoints());
        double[] xCoordinates = new double[points.size()];
        double[] yCoordinates = new double[points.size()];
        for (int index = 0; index < points.size(); index++) {
            xCoordinates[index] = points.get(index).getX();
            yCoordinates[index] = points.get(index).getY();
        }
        context.setFill(Color.GREY);
        context.fillPolygon(xCoordinates, yCoordinates, points.size());
    }
    
    public void setProperties (Properties properties){
        this.properties = properties;
    }
}
