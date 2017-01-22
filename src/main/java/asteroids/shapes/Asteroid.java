/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.shapes;

import asteroids.elements.AsteroidElement;
import asteroids.elements.Element;
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
        for (Element element : elements){
            AsteroidElement asteroidPiece = (AsteroidElement) element;
            asteroidPiece.setRandomPath();
        }
    }
    
    public void scale (double factor){
        List<Element> elements = getElements();
        for (Element element : elements){
            AsteroidElement asteroidPiece = (AsteroidElement) element;
            asteroidPiece.scale(factor);
        }
    }
    
    public void init(){
        AsteroidElement boundary = new AsteroidElement();
        boundary.getPoints().clear();
        Double [] boundaryPoints = new Double [] {
            100.0, 100.0,
            108.0, 101.0,
            112.0, 94.0,
            120.0, 94.0,
            133.0, 106.0,
            130.0, 112.0,
            133.0, 118.0,
            127.0, 123.0,
            114.0, 127.0,
            104.0, 126.0,
            97.0, 112.0,
            95.0, 109.0
        };
        boundary.setColor(Color.GRAY);
        boundary.getPoints().addAll(boundaryPoints);
        getElements().add(boundary);
    }
    
    public Asteroid getRandomAsteroid(){
        double randomNumber = ThreadLocalRandom.current().nextDouble() + 0.1;
        int randomAngle = ThreadLocalRandom.current().nextInt(0, 365);
        Asteroid asteroid = new Asteroid();
        asteroid.scale(randomNumber);
        asteroid.setRandomPath();
        asteroid.rotate(randomAngle);
        return asteroid;
    }
}
