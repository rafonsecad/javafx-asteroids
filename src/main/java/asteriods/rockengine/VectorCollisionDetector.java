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
    
    @Override
    public Set<Integer> getCrashedElements(){
        return new HashSet<Integer>();
    }
    
    @Override
    public void addElement(Element e){
        if (elements ==  null){
            elements = new ArrayList<>();
        }
        
        if (e.getPoints().isEmpty()) {
            return;
        }
        elements.add(e);
    }
    
    @Override
    public void clearElements(){
        elements.clear();
    }
    
    public boolean areElementsIntersected (Element e1, Element e2){
        List<LineEq> e1Lines = e1.toLines();
        List<LineEq> e2Lines = e2.toLines();

        for (int i=0; i<e1Lines.size(); i++){
            for (int j=0; j<e2Lines.size(); j++){
                if (e1Lines.get(i).areLinesIntersected(e2Lines.get(j))){
                    return true;
                }
            }
        }
        
        return false;
    }
}
