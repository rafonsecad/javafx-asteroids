/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.elements;

import asteroids.rockengine.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

/**
 *
 * @author rafael
 */
public class ShipElement extends Element {
    
    public ShipElement() {
    }

    public void initialize() {
        int centerX = getPropertiesImpl().getWidth() / 2;
        int centerY = getPropertiesImpl().getHeight() / 2;
        Point currentPosition = new Point(centerX, centerY);
        this.init(currentPosition);
    }
    
    public void initialize (Point vectorPosition, Point origin){
        double xPosition = vectorPosition.getX() + origin.getX();
        double yPosition = vectorPosition.getY() + origin.getY();
        Point position = new Point(xPosition, yPosition);
        this.init(position);
    }
    
    private void init(Point position){
        setCurrentPosition(position);
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
    
    public void moveForward(Point vectorPosition, Point endPoint){
        double xPosition = vectorPosition.getX() + endPoint.getX();
        double yPosition = vectorPosition.getY() + endPoint.getY();
        Point position = new Point(xPosition, yPosition);
        setOrigin(getCurrentPosition());
        setEndPoint(position);
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
    }
    
    public BulletElement shoot(){
        BulletElement bullet = new BulletElement();
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
