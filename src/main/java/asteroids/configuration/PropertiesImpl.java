/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.configuration;

/**
 *
 * @author rafael
 */
public class PropertiesImpl implements Properties{
    final private int width = 600;
    final private int height = 600;
    final private int asteroids = 2;
    final private int asteroidFrames = 40;
    
    public int getWidth (){
        return width;
    }
    
    public int getHeight (){
        return height;
    }
    
    public int getAdditionalAsteroids(){
        return asteroids;
    }
    
    public int getAsteroidFrames(){
        return asteroidFrames;
    }
}
