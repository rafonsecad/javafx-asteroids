/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteriods.configuration;

/**
 *
 * @author rafael
 */
public class PropertiesImpl implements Properties{
    final private int width = 600;
    final private int height = 600;
    
    public int getWidth (){
        return width;
    }
    
    public int getHeight (){
        return height;
    }
}
