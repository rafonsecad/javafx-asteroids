/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.configuration;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rafael
 */
public class GraphicsStorage {
    private Map<String, GraphicsLoader> library;

    public GraphicsStorage (){
        this.library = new HashMap<>();
    }
    
    public void init(){
        String key = "asteroid";
        GraphicsLoader value = new GraphicsLoader();
        try{
        value = new GraphicsLoader("/images/asteroid-icon.svg");
        }catch(Exception e){
            e.printStackTrace();
        }
        value.parse();
        this.library.put(key, value);
        
        value = new GraphicsLoader();
        try{
        value = new GraphicsLoader("/images/spaceship-icon.svg");
        }catch(Exception e){
            e.printStackTrace();
        }
        value.parse();
        this.library.put("spaceship", value);
        
    }
    
    public Map<String, GraphicsLoader> getLibrary() {
        return library;
    }
}
