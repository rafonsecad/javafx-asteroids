/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.shapes;

import asteroids.configuration.GraphicsLoader;
import asteroids.configuration.GraphicsStorage;
import asteroids.effects.Effect;
import asteroids.effects.ShapeState;
import asteroids.elements.Element;
import asteroids.rockengine.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class Shape {

    private List<Element> elements;
    private static GraphicsStorage graphics;
    private ShapeState state;

    public Shape (){
        state = new ShapeState();
        state.setEffect(Effect.NORMAL);
    }
    
    public Element getBoundary() {
        return elements.get(0);
    }

    public void updatePosition() {
        for (Element element : elements) {
            element.updatePosition();
        }
    }

    public void rotate(double degrees) {
        for (Element element : elements) {
            element.rotate(degrees);
        }

        double radians = Math.toRadians(degrees);
        List<Point> vectors = getVectorsFromBoundary();
        Point origin = this.getBoundary().getCurrentPosition();
        List<Point> vectorsUpdated = origin.rotateVectors(vectors, radians);

        for (int index = 1; index < elements.size(); index++) {
            elements.get(index).setCurrentPosition(vectorsUpdated.get(index - 1));
            elements.get(index).moveToCurrentPosition();
        }
    }

    public List<Point> getVectorsFromBoundary() {
        List<Point> currentPositions = new ArrayList<>();
        for (int index = 1; index < elements.size(); index++) {
            currentPositions.add(elements.get(index).getCurrentPosition());
        }
        Point mainCurrentPosition = this.getBoundary().getCurrentPosition();
        List<Point> vectors = mainCurrentPosition.changeOrigin(currentPositions);
        return vectors;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public static void setGraphics(GraphicsStorage g) {
        graphics = g;
    }

    public static GraphicsLoader getGraphicsFromKey(String key) {
        return graphics.getLibrary().get(key);
    }

    public ShapeState getState() {
        return state;
    }

    public void setState(ShapeState state) {
        this.state = state;
    }
}
