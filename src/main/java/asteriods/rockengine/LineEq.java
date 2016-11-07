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
    private double[] segmentsPoints;

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

    public double[] getSegmentsPoints() {
        return segmentsPoints;
    }

    public void setSegmentsPoints(double[] segmentsPoints) {
        this.segmentsPoints = segmentsPoints;
    }

    public LineEq() {
        this.segmentedLine = false;
    }

    public static LineEq buildLine(List<Double> points) {
        LineEq line = new LineEq();

        double m = (points.get(3) - points.get(1)) / (points.get(2) - points.get(0));
        double b = points.get(1) - m*points.get(0);

        line.setM(m);
        line.setB(b);
        return line;
    }

    public static LineEq buildSegmentedLine(List<Double> points) {
        LineEq line = LineEq.buildLine(points);
        double[] segmentsPoints = points.stream().mapToDouble(Double::doubleValue).toArray();

        line.setSegmentedLine(true);
        line.setSegmentsPoints(segmentsPoints);
        return line;
    }

    public static LineEq buidVectorLine(List<Double> vector) {
        List<Double> points = new ArrayList<>();
        points.add(vector.get(0));
        points.add(vector.get(1));
        points.add(0.0);
        points.add(0.0);

        return LineEq.buildLine(vector);
    }

    public static boolean areLinesIntersected(LineEq line1, LineEq line2) {

        if (line1.getM() == line2.getM()) {
            return false;
        }

        double [] intersectedPoints = getIntersectedPoints(line1, line2);
        
        if (line1.isSegmentedLine()) {
            double[] points = line1.getSegmentsPoints();
            if (LineEq.isPointOutside(intersectedPoints[0], intersectedPoints[1], points)){
                return false;
            }
        }
        
        if (line2.isSegmentedLine()) {
            double[] points = line2.getSegmentsPoints();
            if (LineEq.isPointOutside(intersectedPoints[0], intersectedPoints[1], points)){
                return false;
            }
        }

        return true;
    }

    protected static boolean isPointOutside(double x, double y, double[] points) {
        if (points[0] < points[2]) {
            if (points[0] > x || points[2] < x) {
                return true;
            }
        } else {
            if (points[0] < x || points[2] > x) {
                return true;
            }
        }

        if (points[1] < points[3]) {
            if (points[1] > y || points[3] < y) {
                return true;
            }
        } else {
            if (points[1] < y || points[3] > y) {
                return true;
            }
        }
        
        return false;
    }
    
    public static double [] getIntersectedPoints(LineEq line1, LineEq line2){
        double x = (line2.getB() - line1.getB()) / (line1.getM() - line2.getM());
        double y = line1.getM() * x + line1.getB();
        
        return new double [] {x, y};
    }
}
