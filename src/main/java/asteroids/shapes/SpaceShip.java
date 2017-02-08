/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.shapes;

import asteroids.configuration.GraphicsLoader;
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
        List<Point> positions = new ArrayList<>();
        for (int index=1; index < getElements().size(); index++){
            positions.add(getElements().get(index).getCurrentPosition());
        }
        ShipElement boundary = (ShipElement) this.getBoundary();
        List<Point> vectors = boundary.getCurrentPosition().changeOrigin(positions);
        boundary.moveForward();
        
        for (int index = 1; index < getElements().size(); index++) {
            ShipElement shipElement = (ShipElement) getElements().get(index);
            shipElement.moveForward(vectors.get(index-1), boundary.getHead());
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
        
        List<Point> positions = new ArrayList<>();
        
        for (int index=1; index < getElements().size(); index++){
            positions.add(getElements().get(index).getCurrentPosition());
        }
        ShipElement boundary = (ShipElement) this.getBoundary();
        List<Point> vectors = boundary.getCurrentPosition().changeOrigin(positions);
        boundary.initialize(pathPoints.get(0));
        for (int index = 1; index < getElements().size(); index++){
            ShipElement sElement = (ShipElement) getElements().get(index);
            sElement.initialize(pathPoints.get(index), vectors.get(index-1), boundary.getCurrentPosition());
        }
    }
    
    @Override
    public void updatePosition(){
        super.updatePosition();
        this.keepPositionInScreen();
    }
    
    public void keepPositionInScreen(){
        List<Point> positions = new ArrayList<>();
        for (int index=1; index < getElements().size(); index++){
            positions.add(getElements().get(index).getCurrentPosition());
        }
        ShipElement boundary = (ShipElement) getBoundary();
        List<Point> vectors = boundary.getCurrentPosition().changeOrigin(positions);
        int [] coordinates = boundary.getMaxValues();
        Point startingPoint = boundary.getCurrentPosition();
        int offset = 40;
        
        if (coordinates[0] > boundary.getPropertiesImpl().getWidth() + offset){
            startingPoint = new Point(-10, boundary.getCurrentPosition().getY());
        }
        if (coordinates[1] < (-1*offset)){
            startingPoint = new Point(boundary.getPropertiesImpl().getWidth(), boundary.getCurrentPosition().getY());
        }
        if (coordinates[2] > boundary.getPropertiesImpl().getHeight() + offset){
            startingPoint = new Point (boundary.getCurrentPosition().getX(), -10);
        }
        if (coordinates[3] < (-1*offset)){
            startingPoint = new Point (boundary.getCurrentPosition().getX(), boundary.getPropertiesImpl().getHeight());
        }
        boundary.setCurrentPosition(startingPoint);
        boundary.moveToCurrentPosition();
        for (int index=1; index < getElements().size(); index++){
            double x = vectors.get(index-1).getX() + startingPoint.getX();
            double y = vectors.get(index-1).getY() + startingPoint.getY();
            getElements().get(index).setCurrentPosition(new Point(x,y));
            getElements().get(index).moveToCurrentPosition();
        }
        
    }
}
