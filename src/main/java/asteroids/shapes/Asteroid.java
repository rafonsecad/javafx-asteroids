/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.shapes;

import asteroids.elements.AsteroidElement;
import asteroids.elements.Element;
import java.util.List;

/**
 *
 * @author rafael
 */
public class Asteroid extends Shape{
    
    public void setRandomPath(){
        List<Element> elements = getElements();
        for (Element element : elements){
            AsteroidElement asteroidPiece = (AsteroidElement) element;
            asteroidPiece.setRandomPath();
        }
    }
}
