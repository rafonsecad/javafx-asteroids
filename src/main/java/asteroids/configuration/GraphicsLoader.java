/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.configuration;

import java.util.ArrayList;
import org.apache.batik.parser.PathHandler;
import org.apache.batik.parser.PathParser;
import org.apache.batik.parser.PathArrayProducer;

/**
 *
 * @author rafael
 */
public class GraphicsLoader {
    
    private String filename;
    
    public GraphicsLoader(){
        
    }
    
    public ArrayList<Double> parsePathString (String path){
        ArrayList<Double> coordinates = new ArrayList<>();
        PathParser parser = new PathParser();
        PathArrayProducer handler = new PathArrayProducer();
        parser.setPathHandler(handler);
        parser.parse(path);
        float [] params = handler.getPathParameters();
        coordinates.add((double) params[0]);
        coordinates.add((double) params[1]);
        for (int index = 2; index < params.length; index+=2){
            double lastX = coordinates.get(index-2);
            coordinates.add((double)params[index] + lastX);
            
            double lastY = coordinates.get(index-1);
            coordinates.add((double)params[index+1] + lastY);
        }
        return coordinates;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
   
}
