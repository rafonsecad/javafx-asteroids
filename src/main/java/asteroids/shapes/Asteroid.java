/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.shapes;

import asteroids.configuration.GraphicsLoader;
import asteroids.elements.AsteroidElement;
import asteroids.elements.Element;
import asteroids.rockengine.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.paint.Color;

/**
 *
 * @author rafael
 */
public class Asteroid extends Shape{
    
    public Asteroid(){
        List<Element> elements = new ArrayList();
        setElements(elements);
        init();
    }
    
    public void setRandomPath(){
        List<Element> elements = getElements();
        AsteroidElement boundary = (AsteroidElement) elements.get(0);
        
        List<Point> positions = new ArrayList<>();
        
        for (int index=1; index < elements.size(); index++){
            positions.add(elements.get(index).getCentroid());
        }
        
        List<Point> vectors = boundary.getCentroid().changeOrigin(positions);
        
        boundary.setRandomPath();
        Point [] boundaryPath = new Point [] {boundary.getCurrentPosition(), boundary.getEndPoint()};
        
        try{
        
            for (int index=1; index<elements.size(); index++){
                AsteroidElement asteroidElement = (AsteroidElement) elements.get(index);
                Point [] path = boundary.getPathFromOrigin(boundaryPath, vectors.get(index-1));
                asteroidElement.setOrigin(path[0]);
                asteroidElement.setEndPoint(path[1]);
                asteroidElement.setCurrentPosition(path[0]);
                asteroidElement.moveToCurrentPosition();
                asteroidElement.setSpeed(boundary.getSpeed());
                asteroidElement.calculateSpeedVector();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void scale (double factor){
        List<Element> elements = getElements();
        AsteroidElement boundary = (AsteroidElement) elements.get(0);
        
        List<Point> positions = new ArrayList<>();
        
        for (int index=1; index < elements.size(); index++){
            positions.add(elements.get(index).getCentroid());
        }
        
        Point origin = boundary.getCentroid();
        boundary.scale(factor);
        for (int index=1; index<elements.size(); index++){
            AsteroidElement asteroidElement = (AsteroidElement) elements.get(index);
            asteroidElement.scale(factor, origin);
        }
    }
    
    public void init(){
        
        GraphicsLoader graphic = Shape.getGraphicsFromKey("asteroid");
        List<Color> colors = graphic.getPathColors();
        List<Double []> pathPoints = graphic.getPathPoints();
        
        for(int index=0; index<colors.size(); index++){
            AsteroidElement aElement = new AsteroidElement();
            aElement.getPoints().clear();
            aElement.getPoints().addAll(pathPoints.get(index));
            aElement.setColor(colors.get(index));
            getElements().add(aElement);
        }
        
    }
    
    public Asteroid getRandomAsteroid(){
        double randomNumber = ThreadLocalRandom.current().nextDouble() + 0.3;
        int randomAngle = ThreadLocalRandom.current().nextInt(0, 365);
        Asteroid asteroid = new Asteroid();
        asteroid.scale(randomNumber);
        asteroid.setRandomPath();
        asteroid.rotate(randomAngle);
        return asteroid;
    }
}
