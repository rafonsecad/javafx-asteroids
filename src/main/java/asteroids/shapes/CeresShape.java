/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.shapes;

import asteroids.elements.Element;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class CeresShape extends Shape{
    public CeresShape(){
        List<Element> elements = getElements();
        elements = new ArrayList<>();
        elements.add(new Ceres());
    }
}
