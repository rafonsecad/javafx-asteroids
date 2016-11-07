/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteriods.rockengine;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class Point {
    private double x;
    private double y;
    
    public Point (){
        this.x = 0.0;
        this.y = 0.0;
    }
    
    public Point (double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public static Point [] build (Double [] rawPoints){
        Point [] points = new Point [rawPoints.length/2];
        for (int i=0; i<points.length; i++){
            points[i] = new Point(rawPoints[(2*i)], rawPoints[(2*i)+1]);
        }
        return points;
    }
    
    public static List<Point> buildList (List<Double> rawPoints){
        List<Point> points = new ArrayList<>();
        
        for (int i=0; i<(rawPoints.size()/2); i++){
            points.add(new Point(rawPoints.get(2*i), rawPoints.get((2*i)+1)));
        }
        
        return points;
    }
    
    public static Double [] toDoubleArray (Point [] points){
        Double [] rawPoints = new Double [points.length*2];
        
        for (int i=0; i<points.length; i++){
            rawPoints [i*2] = points[i].getX();
            rawPoints [(i*2)+1] = points[i].getY();
        }
        
        return rawPoints;
    }
    
    public static List<Double> toDoubleList (List<Point> points){
        List<Double> raw = new ArrayList<>();
        
        for (int i=0; i<points.size(); i++){
            raw.add(points.get(i).getX());
            raw.add(points.get(i).getY());
        }
        
        return raw;
    }
    
    @Override
    public boolean equals(Object obj){
        if (obj == null){
            return false;
        }
        
        final Point point = (Point) obj;
        
        if (this.getX() != point.getX()){
            return false;
        }
        
        if (this.getY() != point.getY()){
            return false;
        }
        
        return true;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
}