/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteriods.elements;

import asteriods.configuration.Properties;
import asteriods.configuration.PropertiesImpl;
import asteriods.rockengine.Point;
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
    
}
