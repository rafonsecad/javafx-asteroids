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
        Set<Integer> indexes = getOverlappedBoxes();
        List<Integer> indexesArray = new ArrayList<>(indexes);
        
        Set<Integer> indexesOfCrashedElements = new HashSet<>();
        for (int i=0; i<indexesArray.size(); i++){
            for (int j=0; j<indexesArray.size(); j++){
                if (i==j){
                    break;
                }
                if (this.areElementsIntersected(elements.get(indexesArray.get(i)),
                                                elements.get(indexesArray.get(j)))){
                    indexesOfCrashedElements.add(j);
                    indexesOfCrashedElements.add(i);
                }
                
            }
        }
        
        return indexes;
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
    
    public Set<Integer> getOverlappedBoxes(){
        Set<Integer> indexes = new HashSet<>();
        List<Box> elementsBoxed = new ArrayList<>();
        for (int i=0; i<elements.size(); i++){
            Box box = new Box(elements.get(i).getMaxValues());
            elementsBoxed.add(box);
        }
        
        for (int i=0; i<elementsBoxed.size(); i++){
            for (int j=0; j<elementsBoxed.size(); j++){
                if (i==j){
                    break;
                }
                if (elementsBoxed.get(i).isBoxOverlapped(elementsBoxed.get(j))){
                    indexes.add(i);
                    indexes.add(j);
                }
            }
        }
        
        return indexes;
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
