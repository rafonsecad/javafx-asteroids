/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteriods.elements;

import asteriods.configuration.Properties;
import asteriods.configuration.PropertiesImpl;
import asteriods.rockengine.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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
    private Properties properties;
    
    public Asteriod (){
        properties = new PropertiesImpl();
    }

    public Asteriod copy(){
        Asteriod asteriod = new Asteriod();
        List<Double> rawPoints = getPoints();
        List<Double> asteriodRawPoints = new ArrayList<>();
        for (int i=0; i<rawPoints.size(); i++){
            double coordinate = rawPoints.get(i);
            asteriodRawPoints.add(coordinate);
        }
        asteriod.getPoints().addAll(asteriodRawPoints);
        asteriod.setFill(Color.rgb(180, 180, 180));
        return asteriod;
    }
    
    public void setRandomPath(){
        int direction = ThreadLocalRandom.current().nextInt(1, 5);
        Point [] pathPoints = new Point [2];
        switch (direction){
            case 1:
                pathPoints = setPathFromTopOrigin();
                break;
            case 2:
                pathPoints = setPathFromRightOrigin();
                break;
            case 3:
                pathPoints = setPathFromBottomOrigin();
                break;
            case 4:
                pathPoints = setPathFromLeftOrigin();
                break;
        }
        
        setOrigin(pathPoints[0]);
        setEndPoint (pathPoints[1]);
        setCurrentPosition(pathPoints[0]);
        moveToOrigin();
        int speed = ThreadLocalRandom.current().nextInt(1, 32);
        setSpeed((double)speed);
        calculateSpeedVector();
    }
    
    protected Point [] setPathFromTopOrigin (){
        int x = ThreadLocalRandom.current().nextInt(0,properties.getWidth());
        Point origin = new Point (x, 0);
        
        x = ThreadLocalRandom.current().nextInt(0,properties.getWidth());
        Point end = new Point (x, properties.getHeight());
        return new Point [] {origin, end};
    }
    
    protected Point [] setPathFromRightOrigin (){
        int y = ThreadLocalRandom.current().nextInt(0,properties.getHeight());
        Point origin = new Point (properties.getWidth(), y);
        
        y = ThreadLocalRandom.current().nextInt(0,properties.getHeight());
        Point end = new Point (0, y);
        return new Point [] {origin, end};
    }
    
    protected Point [] setPathFromBottomOrigin (){
        int x = ThreadLocalRandom.current().nextInt(0,properties.getWidth());
        Point origin = new Point (x, properties.getHeight());
        
        x = ThreadLocalRandom.current().nextInt(0,properties.getWidth());
        Point end = new Point (x, 0);
        return new Point [] {origin, end};
    }
    
    protected Point [] setPathFromLeftOrigin (){
        int y = ThreadLocalRandom.current().nextInt(0, properties.getHeight());
        Point origin = new Point (0, y);
        
        y = ThreadLocalRandom.current().nextInt(0, properties.getHeight());
        Point end = new Point (properties.getWidth(), y);
        return new Point [] {origin, end};
    }
    
    protected void calculateSpeedVector(){
        List<Point> points = new ArrayList<>();
        points.add(getEndPoint());
        List<Point> endPointList = getOrigin().changeOrigin(points);
        Point pathVector = endPointList.get(0);
        double angle = Math.atan2(pathVector.getY(),pathVector.getX());
        
        double xSpeed = speed*Math.cos(angle);
        double ySpeed = speed*Math.sin(angle);
        speedVector = new Point (xSpeed, ySpeed);
    }
    
    public void updatePosition (){

        double deltaX = speedVector.getX()*TIME_FRAME;
        double deltaY = speedVector.getY()*TIME_FRAME;
        
        double nx = deltaX + currentPosition.getX();
        double ny = deltaY + currentPosition.getY();
        
        currentPosition = new Point(nx, ny);
        move(deltaX, deltaY);
    }
    
    protected Point getCentroid(){
        List <Point> points = Point.buildList(getPoints());
        if (points.isEmpty()){
            return new Point(0,0);
        }
        points.add(points.get(0));
        
        double A = 0;
        double Cx = 0;
        double Cy = 0;
        
        for (int i=0; i<points.size()-1; i++){
            Point pi = points.get(i);
            Point pj = points.get(i+1);
            double secondFactor = (pi.getX()*pj.getY()) - (pj.getX()*pi.getY());
            double numX = (pi.getX() + pj.getX())*secondFactor;
            double numY = (pi.getY() + pj.getY())*secondFactor;
            
            Cx += numX;
            Cy += numY;
            A += secondFactor;
        }
        A *= 0.5;
        Cx *= 1/(6*A);
        Cy *= 1/(6*A);
        return new Point (Cx, Cy);
    }
    
    public void moveToOrigin(){
        Point centroid = getCentroid();
        double deltaX = this.currentPosition.getX() - centroid.getX();
        double deltaY = this.currentPosition.getY() - centroid.getY();
        move (deltaX, deltaY);
    }
    
    private void move (double deltaX, double deltaY){
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
