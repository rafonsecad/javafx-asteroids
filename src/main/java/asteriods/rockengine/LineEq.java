/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteriods.rockengine;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class LineEq {

    private double m;
    private double b;
    private boolean segmentedLine;
    private Point[] segmentsPoints;

    public LineEq() {
        this.segmentedLine = false;
    }

    public static LineEq buildLine(List<Point> points) {
        LineEq line = new LineEq();

        double m = (points.get(1).getY() - points.get(0).getY()) /
                   (points.get(1).getX() - points.get(0).getX());
        
        double b = points.get(0).getY() - m*points.get(0).getX();
        
        if (m == Double.POSITIVE_INFINITY || m == Double.NEGATIVE_INFINITY){
            b = points.get(0).getX();
        }
        
        line.setM(m);
        line.setB(b);
        return line;
    }

    public static LineEq buildSegmentedLine(List<Point> points) {
        LineEq line = LineEq.buildLine(points);
        Point [] segmentedPoints = new Point [points.size()];
        
        segmentedPoints[0] = new Point (points.get(0).getX(), points.get(0).getY());
        segmentedPoints[1] = new Point (points.get(1).getX(), points.get(1).getY());
        
        line.setSegmentedLine(true);
        line.setSegmentsPoints(segmentedPoints);
        return line;
    }

    public static LineEq buidVectorLine(Point vector) {
        List<Point> points = new ArrayList<>();
        Point [] segmentedPoints = new Point [2];
        Point origin = new Point (0.0, 0.0);
        
        points.add(new Point(vector.getX(), vector.getY()));
        points.add(origin);
        
        segmentedPoints [0] = origin;
        
        double xQuadrant = Math.signum(vector.getX())*Double.POSITIVE_INFINITY;
        double yQuadrant = Math.signum(vector.getY())*Double.POSITIVE_INFINITY;
        segmentedPoints [1] = new Point (xQuadrant, yQuadrant);

        LineEq line = LineEq.buildLine(points);
        line.setSegmentedLine(true);
        line.setSegmentsPoints(segmentedPoints);
        return line;
    }

    public boolean areLinesIntersected(LineEq line2) {

        if (getM() == line2.getM()) {
            return false;
        }

        Point intersectedPoint = getIntersectedPoint(line2);
        
        if (isSegmentedLine()) {
            Point[] points = getSegmentsPoints();
            if (isPointOutside(intersectedPoint, points)){
                return false;
            }
        }
        
        if (line2.isSegmentedLine()) {
            Point[] points = line2.getSegmentsPoints();
            if (isPointOutside(intersectedPoint, points)){
                return false;
            }
        }

        return true;
    }

    protected boolean isPointOutside(Point intersectedPoint, Point[] points) {
        
        double x = intersectedPoint.getX();
        double y = intersectedPoint.getY();
        
        if (points[0].getX() < points[1].getX()) {
            if (points[0].getX() > x || points[1].getX() < x) {
                return true;
            }
        } else {
            if (points[0].getX() < x || points[1].getX() > x) {
                return true;
            }
        }

        if (points[0].getY() < points[1].getY()) {
            if (points[0].getY() > y || points[1].getY() < y) {
                return true;
            }
        } else {
            if (points[0].getY() < y || points[1].getY() > y) {
                return true;
            }
        }
        
        return false;
    }
    
    public Point getIntersectedPoint(LineEq line2){
        
        double x, y;
        
        if (getM() == Double.POSITIVE_INFINITY || getM() == Double.NEGATIVE_INFINITY){
            x = getB();
            y = line2.getM()*x + line2.getB();
        }
        
        else if (line2.getM() == Double.POSITIVE_INFINITY || line2.getM() == Double.NEGATIVE_INFINITY){
            x = line2.getB();
            y = getM()*x + getB();
        }
        else{
            x = (line2.getB() - getB()) / (getM() - line2.getM());
            y = getM() * x + getB();
        }
        
        return new Point (x, y);
    }
    
    public double getM() {
        return m;
    }

    public double getB() {
        return b;
    }

    public void setM(double m) {
        this.m = m;
    }

    public void setB(double b) {
        this.b = b;
    }

    public boolean isSegmentedLine() {
        return segmentedLine;
    }

    public void setSegmentedLine(boolean segmentedLine) {
        this.segmentedLine = segmentedLine;
    }

    public Point[] getSegmentsPoints() {
        return segmentsPoints;
    }

    public void setSegmentsPoints(Point[] segmentsPoints) {
        this.segmentsPoints = segmentsPoints;
    }

}
