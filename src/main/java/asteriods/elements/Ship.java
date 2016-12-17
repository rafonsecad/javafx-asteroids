/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteriods.elements;

import asteriods.rockengine.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

/**
 *
 * @author rafael
 */
public class Ship extends Element {

    public Ship() {

        Double[] points = new Double[]{
            0.0, 0.0,
            -5.0, 10.0,
            0.0, 8.0,
            5.0, 10.0
        };
        initialize(points);
    }

    public void initialize(Double [] points) {
        getPoints().addAll(points);
        setFill(Color.rgb(211, 211, 211));
        setManaged(false);
        int centerX = getPropertiesImpl().getWidth() / 2;
        int centerY = getPropertiesImpl().getHeight() / 2;
        Point currentPosition = new Point(centerX, centerY);
        setCurrentPosition(currentPosition);
        moveToOrigin();
        setSpeed(20.0);
    }

    public void rotate(double degrees) {
        double radians = Math.toRadians(degrees);
        List<Point> corners = Point.buildList(getPoints());
        List<Point> vectors = getCurrentPosition().changeOrigin(corners);

        List<Point> pointsUpdated = new ArrayList<>();
        for (Point vector : vectors) {
            double sqrX = Math.pow(vector.getX(), 2);
            double sqrY = Math.pow(vector.getY(), 2);

            double magnitude = Math.sqrt(sqrX + sqrY);
            double angle = Math.atan2(vector.getY(), vector.getX()) + radians;

            double x = magnitude * Math.cos(angle) + getCurrentPosition().getX();
            double y = magnitude * Math.sin(angle) + getCurrentPosition().getY();
            pointsUpdated.add(new Point(x, y));
        }

        getPoints().clear();
        getPoints().addAll(Point.toDoubleList(pointsUpdated));
    }
    
    public void moveForward(){
        setOrigin(getCurrentPosition());
        setEndPoint(getHead());
        calculateSpeedVector();
        updatePosition();
    }
    
    public Point getHead(){
        return new Point(getPoints().get(0), getPoints().get(1));
    }
}
