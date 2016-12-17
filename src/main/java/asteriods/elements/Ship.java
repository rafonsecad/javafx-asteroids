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
import javafx.scene.paint.Color;

/**
 *
 * @author rafael
 */
public class Ship extends Element{
    
    private Properties properties;
    
    public Ship() {
        properties = new PropertiesImpl();
        
        Double [] points = new Double [] {
            0.0,  0.0,
           -5.0, 10.0,
            0.0,  8.0,
            5.0, 10.0
        };
        
        getPoints().addAll(points);
        setFill(Color.rgb(211, 211, 211));
        setManaged(false);
        int centerX = properties.getWidth()/2;
        int centerY = properties.getHeight()/2;
        Point currentPosition = new Point (centerX, centerY);
        setCurrentPosition (currentPosition);
        moveToOrigin();
    }
    
    public void rotate (double degrees){
        double radians = Math.toRadians(degrees);
        List<Point> corners = Point.buildList(getPoints());
        List<Point> vectors = getCurrentPosition().changeOrigin(corners);
        
        List<Point> polarVectors = new ArrayList<>();
        for (int i=0; i<vectors.size(); i++){
            double sqrX = Math.pow(vectors.get(i).getX(), 2);
            double sqrY = Math.pow(vectors.get(i).getY(), 2);
            
            double magnitude = Math.sqrt(sqrX + sqrY);
            double angle = Math.atan2(vectors.get(i).getY(), vectors.get(i).getX());
            double rotateAngle = angle + radians;
            polarVectors.add(new Point(magnitude, rotateAngle));
        }
        
        List<Point> cartesianPoints = new ArrayList<>();
        for (int i=0; i<polarVectors.size(); i++){
            double magnitude = polarVectors.get(i).getX();
            double angle = polarVectors.get(i).getY();
            double x = magnitude*Math.cos(angle) + getCurrentPosition().getX();
            double y = magnitude*Math.sin(angle) + getCurrentPosition().getY();
            cartesianPoints.add(new Point(x, y));
        }
        
        getPoints().clear();
        getPoints().addAll(Point.toDoubleList(cartesianPoints));
    }
}
