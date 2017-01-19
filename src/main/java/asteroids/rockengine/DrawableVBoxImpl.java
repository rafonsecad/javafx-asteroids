/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.rockengine;

import asteroids.elements.Element;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 *
 * @author rafael
 */
public class DrawableVBoxImpl implements Drawable{
    
    private VBox root;
    
    public void setDrawer(Object obj){
        if (!(obj instanceof VBox)){
            return;
        }
        
        root = (VBox) obj;
    }
    
    public void draw (Node ... nodes){
        
        for ( Node node : nodes){
            root.getChildren().add(node);
        }
    }
    
    public void draw (List<Element> elements){
        
        for(Element element : elements){
            root.getChildren().add(element);
        }
    }
    
    public void clear(){
        root.getChildren().clear();
    }
}
