/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteriods.elements;

import asteriods.rockengine.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.paint.Color;

/**
 *
 * @author rafael
 */
public class Asteriod extends Element{

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
        asteriod.setManaged(false);
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
        int x = ThreadLocalRandom.current().nextInt(0,getPropertiesImpl().getWidth());
        Point origin = new Point (x, 0);
        
        x = ThreadLocalRandom.current().nextInt(0,getPropertiesImpl().getWidth());
        Point end = new Point (x, getPropertiesImpl().getHeight());
        return new Point [] {origin, end};
    }
    
    protected Point [] setPathFromRightOrigin (){
        int y = ThreadLocalRandom.current().nextInt(0,getPropertiesImpl().getHeight());
        Point origin = new Point (getPropertiesImpl().getWidth(), y);
        
        y = ThreadLocalRandom.current().nextInt(0,getPropertiesImpl().getHeight());
        Point end = new Point (0, y);
        return new Point [] {origin, end};
    }
    
    protected Point [] setPathFromBottomOrigin (){
        int x = ThreadLocalRandom.current().nextInt(0,getPropertiesImpl().getWidth());
        Point origin = new Point (x, getPropertiesImpl().getHeight());
        
        x = ThreadLocalRandom.current().nextInt(0,getPropertiesImpl().getWidth());
        Point end = new Point (x, 0);
        return new Point [] {origin, end};
    }
    
    protected Point [] setPathFromLeftOrigin (){
        int y = ThreadLocalRandom.current().nextInt(0, getPropertiesImpl().getHeight());
        Point origin = new Point (0, y);
        
        y = ThreadLocalRandom.current().nextInt(0, getPropertiesImpl().getHeight());
        Point end = new Point (getPropertiesImpl().getWidth(), y);
        return new Point [] {origin, end};
    }
    
}
