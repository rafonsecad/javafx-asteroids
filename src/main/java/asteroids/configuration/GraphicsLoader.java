/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.paint.Color;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.batik.parser.PathParser;
import org.apache.batik.parser.PathArrayProducer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author rafael
 */
public class GraphicsLoader {

    private String filename;
    private List<Color> colors;
    private List<String> paths;
    private List<Double[]> pathPoints;

    public GraphicsLoader() {

    }

    public GraphicsLoader(String filename){
        this.filename = filename;
    }
    
    public void parse() {
        String fileContent = "";
        try{
            fileContent = readFile(this.filename, StandardCharsets.UTF_8);
        }
        catch(Exception e){
            
        }
        process(fileContent);
        parseGraphicsPoints();
    }

    public String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public void process(String fileContent) {
        InputStream stream = new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8));
        this.paths = new ArrayList<>();
        this.colors = new ArrayList<>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            processNodes(dbf, stream);

        } catch (Exception e) {
            this.colors = new ArrayList<>();
            this.paths = new ArrayList<>();
        }
    }

    private void processNodes(DocumentBuilderFactory dbf, InputStream stream) throws Exception {

        DocumentBuilder docBuilder = dbf.newDocumentBuilder();
        Document doc = docBuilder.parse(stream);
        doc.getDocumentElement().normalize();
        NodeList pathNodes = doc.getElementsByTagName("path");
        for (int index = 0; index < pathNodes.getLength(); index++) {
            processPath(pathNodes.item(index));
        }
    }

    private void processPath(Node node) {
        Element path = (Element) node;
        paths.add(path.getAttribute("d"));
        String style = path.getAttribute("style");
        Pattern pattern = Pattern.compile("fill:.{7}");
        Matcher matcher = pattern.matcher(style);
        if (!matcher.find()) {
            colors.add(Color.BLACK);
            return;
        }
        String fill = matcher.group(0).replace("fill:", "");
        if (fill.contains("none")) {
            colors.add(Color.WHITE);
            return;
        }
        colors.add(Color.web(fill));
    }

    public List<String> getPathStrings() {
        List<String> copyPaths= new ArrayList<>();
        for (String path: this.paths){
            copyPaths.add(path);
        }
        return copyPaths;
    }

    public List<Color> getPathColors() {
        List<Color> copyColors = new ArrayList<>();
        for (Color color: this.colors){
            Color c = Color.rgb((int)(256*color.getRed()),
                                (int)(256*color.getGreen()),
                                (int)(256*color.getBlue()));
            copyColors.add(c);
        }
        return copyColors;
    }

    public List<Double[]> getPathPoints() {
        List<Double []> copyPathPoints = new ArrayList<>();
        for (Double[] points : this.pathPoints){
            Double [] copyPoints = new Double [points.length];
            for(int index=0; index < points.length; index++){
                copyPoints [index] = new Double(points[index]);
            }
            copyPathPoints.add(copyPoints);
        }
        return copyPathPoints;
    }

    public void parseGraphicsPoints() {
        List<Double[]> graphicsPoints = new ArrayList<>();
        for (String path : this.paths) {
            Double[] pathPoints = parsePathString(path).toArray(new Double[0]);
            graphicsPoints.add(pathPoints);
        }
        this.pathPoints = graphicsPoints;
    }

    public List<Double> parsePathString(String path) {
        ArrayList<Double> coordinates = new ArrayList<>();
        PathParser parser = new PathParser();
        PathArrayProducer handler = new PathArrayProducer();
        parser.setPathHandler(handler);
        parser.parse(path);
        float[] params = handler.getPathParameters();
        coordinates.add((double) params[0]);
        coordinates.add((double) params[1]);
        for (int index = 2; index < params.length; index += 2) {
            double lastX = coordinates.get(index - 2);
            coordinates.add((double) params[index] + lastX);

            double lastY = coordinates.get(index - 1);
            coordinates.add((double) params[index + 1] + lastY);
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
