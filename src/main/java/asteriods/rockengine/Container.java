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

/**
 *
 * @author rafael
 */
public class Container {

    private List<Element> elements;
    private boolean[][] map;

    public void addElement(Element e) {
        elements.add(e);
    }

    public void moveElements() {

    }

    public List<Element> getCrashedElements() {
        //int numberOfElements = elements.size();
        map = new boolean[600][600];
        Element e = elements.get(0);
        mapPolygon(e);
        return new ArrayList<Element>();
    }

    private void mapPolygon(Element e) {

    }

    public boolean isPointInPolygon(double x, double y, List<Double> polygonPoints) {

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
        
        if (isPointEnclosed(quadrants)){
            return true;
        }
        
        List<Integer> lquad = Arrays.stream(quadrants).boxed().collect(Collectors.toList());
        Collections.reverse(lquad);
        return isPointEnclosed(lquad.stream().mapToInt(i->i).toArray());
    }

    private boolean isPointEnclosed(int[] quadrants) {
        boolean[] bridges = new boolean[4];
        int k;
        for (int i = 0; i < quadrants.length; i++) {
            
            k = (i==0) ? 3 : i-1;
            
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
}
