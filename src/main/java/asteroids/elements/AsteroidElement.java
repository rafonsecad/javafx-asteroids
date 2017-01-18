/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.elements;

import asteroids.rockengine.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.paint.Color;

/**
 *
 * @author rafael
 */
public class AsteroidElement extends Element{

    public AsteroidElement copy(){
        AsteroidElement asteriod = new AsteroidElement();
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
                pathPoints = setPathFromTopOrigin(getRandomWidth(), getRandomWidth());
                break;
            case 2:
                pathPoints = setPathFromRightOrigin(getRandomHeight(), getRandomHeight());
                break;
            case 3:
                pathPoints = setPathFromBottomOrigin(getRandomWidth(), getRandomWidth());
                break;
            case 4:
                pathPoints = setPathFromLeftOrigin(getRandomHeight(), getRandomHeight());
                break;
        }
        
        setOrigin(pathPoints[0]);
        setEndPoint (pathPoints[1]);
        setCurrentPosition(pathPoints[0]);
        moveToCurrentPosition();
        int speed = ThreadLocalRandom.current().nextInt(1, 32);
        setSpeed((double)speed);
        calculateSpeedVector();
    }
    
    protected Point [] setPathFromTopOrigin (int randomXOrigin, int randomXEndPoint){
        Point origin = new Point (randomXOrigin, 0);
        Point end = new Point (randomXEndPoint, getMaxHeight());
        return new Point [] {origin, end};
    }
    
    protected Point [] setPathFromRightOrigin (int randomYOrigin, int randomYEndPoint){
        Point origin = new Point (getMaxWidth(), randomYOrigin);
        Point end = new Point (0, randomYEndPoint);
        return new Point [] {origin, end};
    }
    
    protected Point [] setPathFromBottomOrigin (int randomXOrigin, int randomXEndPoint){
        Point origin = new Point (randomXOrigin, getMaxHeight());
        Point end = new Point (randomXEndPoint, 0);
        return new Point [] {origin, end};
    }
    
    protected Point [] setPathFromLeftOrigin (int randomYOrigin, int randomYEndPoint){
        Point origin = new Point (0, randomYOrigin);
        Point end = new Point (getMaxWidth(), randomYEndPoint);
        return new Point [] {origin, end};
    }
    
    public int getRandomWidth(){
        return ThreadLocalRandom.current().nextInt(0, getMaxWidth());
    }
    
    public int getRandomHeight(){
        return ThreadLocalRandom.current().nextInt(0, getMaxHeight());
    }
    
    public int getMaxWidth(){
        return getPropertiesImpl().getWidth();
    }
    
    public int getMaxHeight(){
        return getPropertiesImpl().getHeight();
    }
}
