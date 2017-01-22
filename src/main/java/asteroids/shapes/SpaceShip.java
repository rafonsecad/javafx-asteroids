/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.shapes;

import asteroids.elements.Element;
import asteroids.elements.ShipElement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class SpaceShip extends Shape{
    
    public SpaceShip(){
        List<Element> elements = new ArrayList<>();
        setElements(elements);
        init();
    }
    
    public void moveForward(){
        for(Element element : getElements()){
            ShipElement shipElement = (ShipElement) element;
            shipElement.moveForward();
        }
    }
    
    public void init(){
        ShipElement boundary = new ShipElement();
        boundary.getPoints().clear();
        Double [] boundaryPoints = new Double []{
            100.0, 2.0,
            102.0, 4.0,
            104.0, 8.0,
            104.0, 12.0,
            106.0, 20.0,
            108.0, 22.0,
            109.0, 24.0,
            110.0, 25.0,
            116.0, 28.0,
            116.0, 34.0,
            108.0, 34.0,
            108.0, 36.0,
            92.0, 36.0,
            92.0, 34.0,
            84.0, 34.0,
            84.0, 28.0,
            90.0, 25.0,
            91.0, 24.0,
            92.0, 22.0,
            94.0, 20.0,
            96.0, 12.0,
            96.0, 8.0,
            98.0, 4.0        
        };
        boundary.initialize(boundaryPoints);
        getElements().add(boundary);
    }
}
