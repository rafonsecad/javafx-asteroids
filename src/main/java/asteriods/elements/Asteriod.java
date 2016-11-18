/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteriods.elements;

import asteriods.rockengine.LineEq;
import asteriods.rockengine.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class Asteriod extends Element{
   
    private Double speed;
    private Point speedVector;
    private Point origin;
    private Point endPoint;
    
    public Asteriod (){
        this.getPoints().addAll(new Double []{
            -8.0, 0.0,
            -4.0, 4.0,
             0.0, 8.0,
             4.0, 4.0,
             8.0, 0.0,
             4.0,-4.0,
             0.0,-8.0,
            -4.0,-4.0
        });
    }

    protected void calculateSpeedVector(){
        List<Point> points = new ArrayList<>();
        points.add(getEndPoint());
        List<Point> endPointList = getOrigin().changeOrigin(points);
        Point pathVector = endPointList.get(0);
        double angle = Math.atan(pathVector.getY()/pathVector.getX());
        
        double xSpeed = speed*Math.sin(angle);
        double ySpeed = speed*Math.cos(angle);
        speedVector = new Point (xSpeed, ySpeed);
    }
    
    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Point getOrigin() {
        return origin;
    }

    public Point getSpeedVector() {
        return speedVector;
    }
    
    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }
}
