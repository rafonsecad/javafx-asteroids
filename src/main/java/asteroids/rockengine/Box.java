/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.rockengine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author rafael
 */
public class Box {

    private List<Point> corners;
    private int[] coordinates;

    public Box(int[] coordinates) {
        corners = new ArrayList<>();
        corners.add(new Point(coordinates[0], coordinates[2]));
        corners.add(new Point(coordinates[1], coordinates[2]));
        corners.add(new Point(coordinates[1], coordinates[3]));
        corners.add(new Point(coordinates[0], coordinates[3]));
        this.coordinates = coordinates;
    }

    public boolean isBoxOverlapped(Box box) {

        for (int i = 0; i < box.getCorners().size(); i++) {
            Point boxCorner = box.getCorners().get(i);
            if (isPointInsideBox(boxCorner)) {
                return true;
            }
        }

        return false;
    }

    public boolean isPointInsideBox(Point point) {

        if (this.coordinates[0] > point.getX()) {
            return false;
        }

        if (this.coordinates[1] < point.getX()) {
            return false;
        }

        if (this.coordinates[2] > point.getY()) {
            return false;
        }

        if (this.coordinates[3] < point.getY()) {
            return false;
        }

        return true;
    }

    public List<Point> getCorners() {
        return corners;
    }
}
