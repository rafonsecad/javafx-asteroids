/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.shapes;

import asteroids.configuration.GraphicsLoader;
import asteroids.configuration.Properties;
import asteroids.elements.Element;
import asteroids.elements.ShipElement;
import asteroids.rockengine.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

/**
 *
 * @author rafael
 */
public class SpaceShip extends Shape {

    public SpaceShip() {
        List<Element> elements = new ArrayList<>();
        setElements(elements);
        init();
    }

    public void moveForward() {
        List<Point> vectors = this.getVectorsFromBoundary();
        ShipElement boundary = (ShipElement) this.getBoundary();
        boundary.moveForward();

        for (int index = 1; index < getElements().size(); index++) {
            ShipElement shipElement = (ShipElement) getElements().get(index);
            shipElement.moveForward(vectors.get(index - 1), boundary.getHead());
        }
    }

    public void init() {

        GraphicsLoader graphic = Shape.getGraphicsFromKey("spaceship");
        List<Color> colors = graphic.getPathColors();
        List<Double[]> pathPoints = graphic.getPathPoints();

        for (int index = 0; index < colors.size(); index++) {
            ShipElement sElement = new ShipElement();
            sElement.setPoints(pathPoints.get(index));
            sElement.setColor(colors.get(index));
            getElements().add(sElement);
        }

        List<Point> vectors = this.getVectorsFromBoundary();
        ShipElement boundary = (ShipElement) this.getBoundary();
        boundary.initialize(pathPoints.get(0));
        for (int index = 1; index < getElements().size(); index++) {
            ShipElement sElement = (ShipElement) getElements().get(index);
            sElement.initialize(pathPoints.get(index), vectors.get(index - 1), boundary.getCurrentPosition());
        }
    }

    @Override
    public void updatePosition() {
        super.updatePosition();
        this.keepPositionInScreen();
    }

    public void keepPositionInScreen() {
        List<Point> vectors = this.getVectorsFromBoundary();
        ShipElement boundary = (ShipElement) getBoundary();
        int[] coordinates = boundary.getMaxValues();
        Point startingPoint = boundary.getCurrentPosition();
        Properties properties = boundary.getPropertiesImpl();
        int offset = 40;

        if (coordinates[0] > properties.getWidth() + offset) {
            startingPoint = new Point(-10, startingPoint.getY());
        }
        if (coordinates[1] < (-1 * offset)) {
            startingPoint = new Point(properties.getWidth(), startingPoint.getY());
        }
        if (coordinates[2] > properties.getHeight() + offset) {
            startingPoint = new Point(startingPoint.getX(), -10);
        }
        if (coordinates[3] < (-1 * offset)) {
            startingPoint = new Point(startingPoint.getX(), properties.getHeight());
        }
        boundary.setCurrentPosition(startingPoint);
        boundary.moveToCurrentPosition();
        this.moveShipElements(vectors, startingPoint);
    }

    private void moveShipElements(List<Point> vectors, Point origin) {
        for (int index = 1; index < getElements().size(); index++) {
            double x = vectors.get(index - 1).getX() + origin.getX();
            double y = vectors.get(index - 1).getY() + origin.getY();
            getElements().get(index).setCurrentPosition(new Point(x, y));
            getElements().get(index).moveToCurrentPosition();
        }
    }
}
