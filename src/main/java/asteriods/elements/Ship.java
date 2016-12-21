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
        int centerX = getPropertiesImpl().getWidth() / 2;
        int centerY = getPropertiesImpl().getHeight() / 2;
        Point currentPosition = new Point(centerX, centerY);
        setCurrentPosition(currentPosition);
        moveToCurrentPosition();
        setSpeed(0.0);
        setOrigin(getCurrentPosition());
        setEndPoint(getHead());
        calculateSpeedVector();
    }
    
    public void moveForward(){
        setOrigin(getCurrentPosition());
        setEndPoint(getHead());
        setSpeed(10.0);
        calculateSpeedVector();
        updatePosition();
    }
    
    public Point getHead(){
        return new Point(getPoints().get(0), getPoints().get(1));
    }
    
    @Override
    public void updatePosition(){
        super.updatePosition();
        keepPositionInScreen();
    }
    
    public void keepPositionInScreen(){
        int [] coordinates = getMaxValues();
        Point startingPoint = getCurrentPosition();
        int offset = 15;
        
        if (coordinates[0] > getPropertiesImpl().getWidth() + offset){
            startingPoint = new Point(-10, getCurrentPosition().getY());
        }
        if (coordinates[1] < (-1*offset)){
            startingPoint = new Point(getPropertiesImpl().getWidth(), getCurrentPosition().getY());
        }
        if (coordinates[2] > getPropertiesImpl().getHeight() + offset){
            startingPoint = new Point (getCurrentPosition().getX(), -10);
        }
        if (coordinates[3] < (-1*offset)){
            startingPoint = new Point (getCurrentPosition().getX(), getPropertiesImpl().getHeight());
        }
        setCurrentPosition(startingPoint);
        moveToCurrentPosition();
    }
    
    public Bullet shoot(){
        Bullet bullet = new Bullet();
        double bulletHalfLength = bullet.getHalfLength();
        Point bulletPosition = getBulletOrigin(bulletHalfLength);
        bullet.initialize(this.getAngle(), bulletPosition, getCurrentPosition());
        return bullet;
    }
    
    private Point getBulletOrigin(double length){
        List<Point> corners = new ArrayList<>();
        corners.add(getHead());
        List<Point> vectors = getCurrentPosition().changeOrigin(corners);
        Point vector = vectors.get(0);
        double sqrX = Math.pow(vector.getX(), 2);
        double sqrY = Math.pow(vector.getY(), 2);
        double radians = Math.toRadians(getAngle() - 90.0);
        double magnitude = Math.sqrt(sqrX+sqrY) + length + 10;
        double x = magnitude * Math.cos(radians) + getCurrentPosition().getX();
        double y = magnitude * Math.sin(radians) + getCurrentPosition().getY();
        return new Point(x, y);
    }
}
