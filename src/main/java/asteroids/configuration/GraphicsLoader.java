/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.configuration;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.batik.parser.PathParser;
import org.apache.batik.parser.PathArrayProducer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author rafael
 */
public class GraphicsLoader {
    
    private String filename;
    
    public GraphicsLoader(){
        
    }
    
    public List<String> getPathStrings (String fileContent){
        InputStream stream = new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8));
        List<String> paths = new ArrayList<>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder docBuilder = dbf.newDocumentBuilder();
            Document doc = docBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            NodeList pathNodes = doc.getElementsByTagName("path");
            for (int index=0; index<pathNodes.getLength(); index++){
                Element path = (Element) pathNodes.item(index);
                paths.add(path.getAttribute("d"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return paths;
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
