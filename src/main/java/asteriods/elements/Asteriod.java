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
   
    private final double TIME_FRAME = 0.1;
    
    private Double speed;
    private Point speedVector;
    private Point origin;
    private Point endPoint;
    private Point currentPosition;
    
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

    public void calculateSpeedVector(){
        List<Point> points = new ArrayList<>();
        points.add(getEndPoint());
        List<Point> endPointList = getOrigin().changeOrigin(points);
        Point pathVector = endPointList.get(0);
        double angle = Math.atan(pathVector.getY()/pathVector.getX());
        
        double xSpeed = speed*Math.cos(angle);
        double ySpeed = speed*Math.sin(angle);
        speedVector = new Point (xSpeed, ySpeed);
    }
    
    public Point getNextPoint(){
        double nx = (speedVector.getX()*TIME_FRAME) + currentPosition.getX();
        double ny = (speedVector.getY()*TIME_FRAME) + currentPosition.getY();
        
        return new Point(nx, ny);
    }
    
    public void updateAsteriodPosition (Point nextPoint){
        double deltaX = nextPoint.getX() - currentPosition.getX();
        double deltaY = nextPoint.getY() - currentPosition.getY();
        
        currentPosition = nextPoint;
        
        List<Point> asteriodPoints = Point.buildList(getPoints());
        
        for (int i=0; i<asteriodPoints.size(); i++){
            Point p = asteriodPoints.get(i);
            p.setX(p.getX() + deltaX);
            p.setY(p.getY() + deltaY);
        }
        getPoints().removeAll(getPoints());
        getPoints().addAll(Point.toDoubleList(asteriodPoints));
    }
    
    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public void setCurrentPosition(Point currentPosition) {
        this.currentPosition = currentPosition;
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
