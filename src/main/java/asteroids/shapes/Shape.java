/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.shapes;

import asteroids.elements.Element;
import java.util.List;

/**
 *
 * @author rafael
 */
public class Shape {
    private List<Element> elements;

    public Element getBoundary(){
        return elements.get(0);
    }
    
    public void updatePosition(){
        for (Element element: elements){
            element.updatePosition();
        }
    }
    
    public void rotate (double degrees){
        for(Element element : elements){
            element.rotate(degrees);
        }
    }
    
    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }
}
