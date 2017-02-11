/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.rockengine;

import asteroids.configuration.Properties;
import asteroids.effects.Effect;
import asteroids.effects.ShapeState;
import asteroids.elements.Element;
import asteroids.shapes.Shape;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author rafael
 */
public class DrawableCanvasImpl implements Drawable {

    private GraphicsContext context;
    private Properties properties;

    public DrawableCanvasImpl(Properties properties) {
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
        for (Node node : nodes) {
            if (node instanceof Element) {
                drawElement((Element) node);
            }
        }
    }

    @Override
    public void draw(Shape shape) {
        List<Element> elements = shape.getElements();
        context.setGlobalAlpha(1.0);
        if (shape.isExploding()) {
            explodeShape(shape);
        }

        if (shape.isDestroyed()) {
            context.setGlobalAlpha(0);
        }

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
        context.setFill(element.getColor());
        context.fillPolygon(xCoordinates, yCoordinates, points.size());
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    private void explodeShape(Shape shape) {
        int frames = shape.getState().getFrames();
        if (frames >= 10) {
            shape.destroy();
        }
        shape.getState().setFrames(frames + 1);
        context.setGlobalAlpha(0.6 - (frames * 0.6 / 10));
    }
}
