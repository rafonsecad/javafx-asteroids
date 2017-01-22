/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.shapes;

import asteroids.elements.BulletElement;
import asteroids.elements.Element;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class Bullet extends Shape{
    
    public Bullet(){
        List<Element> elements = new ArrayList<>();
        setElements(elements);
        //elements.add(new BulletElement());
    }
    
    public void addBulletElement(BulletElement be){
        getElements().add(be);
    }
}
