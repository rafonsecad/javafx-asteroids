/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteriods.rockengine;

import asteriods.configuration.Properties;
import asteriods.configuration.PropertiesImpl;
import asteriods.elements.Element;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author rafael
 */
public class VectorCollisionDetector implements Detectable{
    
    private List<Element> elements;
    private Properties properties;
    private int width;
    private int height;
    
    
    public VectorCollisionDetector(){
        properties = new PropertiesImpl();
        this.width = properties.getWidth();
        this.height = properties.getHeight();
        elements = new ArrayList<>();
    }
    
    public Set<Integer> getCrashedElements(){
        return new HashSet<Integer>();
    }
    
    public void addElement(Element e){
        if (e.getPoints().isEmpty()) {
            return;
        }
        elements.add(e);
    }
}
