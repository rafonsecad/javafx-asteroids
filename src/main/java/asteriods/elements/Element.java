/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteriods.elements;

import asteriods.rockengine.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.shape.Polygon;

/**
 *
 * @author rafael
 */
public class Element extends Polygon {
    
    public int[] getMaxValues() {

        List<Point> points = Point.buildList(getPoints());
        List<Double> xPoints = new ArrayList<>();
        List<Double> yPoints = new ArrayList<>();

        for (Point p : points) {
            xPoints.add(p.getX());
            yPoints.add(p.getY());
        }
        int[] values = new int[4];

        values[0] = Collections.min(xPoints).intValue();
        values[1] = Collections.max(xPoints).intValue();
        values[2] = Collections.min(yPoints).intValue();
        values[3] = Collections.max(yPoints).intValue();

        return values;
    }

}
