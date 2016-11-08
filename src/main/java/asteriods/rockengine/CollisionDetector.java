/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteriods.rockengine;

import asteriods.elements.Element;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;

/**
 *
 * @author rafael
 */
public class CollisionDetector {

    private List<Element> elements;
    private List<List<List<Integer>>> map;
    private int width;
    private int height;

    final static Logger logger = Logger.getLogger(CollisionDetector.class);
    
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<List<List<Integer>>> getMap() {
        return map;
    }

    public CollisionDetector() {
        elements = new ArrayList<>();
    }

    public CollisionDetector(int width, int height) {
        elements = new ArrayList<>();
        this.width = width;
        this.height = height;
    }

    public void addElement(Element e) {
        elements.add(e);
    }

    public void moveElements() {

    }

    public List<Element> getCrashedElements() {
        //int numberOfElements = elements.size();
        mapInitialization();
        Element e = elements.get(0);
        mapPolygon(e, 0);
        return new ArrayList<Element>();
    }

    protected void mapPolygon(Element e, int elementID) {
        List<Double> polygonPoints = e.getPoints();
        List<Double> xPoints = new ArrayList<>();
        List<Double> yPoints = new ArrayList<>();

        for (int i = 0; i < polygonPoints.size(); i++) {
            if (i == 0 || i % 2 == 0) {
                xPoints.add(polygonPoints.get(i));
            } else {
                yPoints.add(polygonPoints.get(i));
            }
        }
        int xmax = Collections.max(xPoints).intValue();
        int xmin = Collections.min(xPoints).intValue();

        int ymax = Collections.max(yPoints).intValue();
        int ymin = Collections.min(yPoints).intValue();
        System.out.println("xmin: " + xmin + " xmax: " + xmax + " ymin: " + ymin + " ymax: " + ymax);
        for (int i = xmin; i < xmax; i++) {
            for (int j = ymin; j < ymax; j++) {
                try {
                    if (isPointInPolygon(i, j, polygonPoints)) {
                        map.get(i).get(j).add(elementID);
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    logger.error("Error in points: " + i + ", " + j);
                    logger.error("Error in points", ex);
                    System.exit(0);
                }
            }
        }
    }

    protected boolean isPointInPolygon(double x, double y, List<Double> polygonPoints) {

        int[] quadrants = new int[polygonPoints.size() / 2];
        for (int i = 1; i < polygonPoints.size(); i += 2) {
            double xi = polygonPoints.get(i - 1) - x;
            double yi = polygonPoints.get(i) - y;
            
            if (xi == 0 && yi == 0) {
                return true;
            } else if (xi >= 0 && yi >= 0) {
                quadrants[i / 2] = 1;
            } else if (xi <= 0 && yi >= 0) {
                quadrants[i / 2] = 2;
            } else if (xi <= 0 && yi <= 0) {
                quadrants[i / 2] = 3;
            } else if (xi >= 0 && yi <= 0) {
                quadrants[i / 2] = 4;
            }
            
        }

        if (isPointEnclosed(quadrants)) {
            return true;
        }

        List<Integer> lquad = Arrays.stream(quadrants).boxed().collect(Collectors.toList());
        Collections.reverse(lquad);
        return isPointEnclosed(lquad.stream().mapToInt(i -> i).toArray());
    }

    protected List<Double> getPolyPointsInQuadrants (double x, double y, List<Double> polygonPoints){
        List<Point> segmentPolygonPoints = Point.buildList(polygonPoints);
        List<Point> quadrantVectors = new ArrayList<>();
        
        quadrantVectors.add(new Point(1.0, 1.0));
        quadrantVectors.add(new Point(-1.0, 1.0));
        quadrantVectors.add(new Point(-1.0, -1.0));
        quadrantVectors.add(new Point(1.0, -1.0));
        
        List<Point> linesPoint;
        
        for (int i=1; i < polygonPoints.size(); i+=2){
            linesPoint = new ArrayList<>();
            linesPoint.add(segmentPolygonPoints.get(i));
            linesPoint.add(segmentPolygonPoints.get(i-1));
            
            for (int k=0; k < 4; k++){
                LineEq line1 = LineEq.buildSegmentedLine(linesPoint);
                LineEq line2 = LineEq.buidVectorLine(quadrantVectors.get(k));
                if (LineEq.areLinesIntersected(line1, line2)){
                    Point p = LineEq.getIntersectedPoint(line1, line2);
                }
            }
        }
        return new ArrayList<Double>();
    }
    
    protected boolean isPointEnclosed(int[] quadrants) {
        boolean[] bridges = new boolean[4];
        int k;
        for (int i = 0; i < quadrants.length; i++) {

            k = (i == 0) ? quadrants.length-1 : i - 1;
            
            if (quadrants[i] == 2 && quadrants[k] == 1) {
                bridges[0] = true;
            } else if (quadrants[i] == 3 && quadrants[k] == 2) {
                bridges[1] = true;
            } else if (quadrants[i] == 4 && quadrants[k] == 3) {
                bridges[2] = true;
            } else if (quadrants[i] == 1 && quadrants[k] == 4) {
                bridges[3] = true;
            }
        }

        return bridges[0] && bridges[1] && bridges[2] && bridges[3];
    }

    
    
    private void mapInitialization() {
        map = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            map.add(new ArrayList<>());
            for (int j = 0; j < height; j++) {
                map.get(i).add(new ArrayList<>());
            }
        }
    }
}
