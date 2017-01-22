/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.shapes;

import asteroids.elements.AsteroidElement;
import asteroids.shapes.Vesta;
import asteroids.shapes.Ceres;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author rafael
 */
public class AsteroidUtil {
    public static AsteroidElement getRandomAsteroid(){
        int r = ThreadLocalRandom.current().nextInt(1, 3);
        switch(r){
            case 1:
                return new Ceres();
            case 2:
                return new Vesta();
        }
            return new Ceres();
    }
    
    public static Asteroid getRandomAsteroidShape(){
        int r = ThreadLocalRandom.current().nextInt(1, 3);
        switch(r){
            case 1:
                return new CeresShape();
            case 2:
                return new VestaShape();
        }
            return new CeresShape();
    }
}
