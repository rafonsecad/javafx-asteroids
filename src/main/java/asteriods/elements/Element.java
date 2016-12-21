/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteriods.elements;

import asteriods.configuration.Properties;
import asteriods.configuration.PropertiesImpl;
import asteriods.rockengine.LineEq;
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

    private final double TIME_FRAME = 0.1;

    private Double speed;
    private Point speedVector;
    private Point origin;
    private Point endPoint;
    private Point currentPosition;
    private double angle;

    private Properties properties;

    public Element() {

        properties = new PropertiesImpl();
        angle = 0;
        setManaged(false);
    }

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

    protected void calculateSpeedVector() {
        List<Point> points = new ArrayList<>();
        points.add(getEndPoint());
        List<Point> endPointList = getOrigin().changeOrigin(points);
        Point pathVector = endPointList.get(0);
        double angle = Math.atan2(pathVector.getY(), pathVector.getX());

        double xSpeed = speed * Math.cos(angle);
        double ySpeed = speed * Math.sin(angle);
        speedVector = new Point(xSpeed, ySpeed);
    }

    public void updatePosition() {

        double deltaX = speedVector.getX() * TIME_FRAME;
        double deltaY = speedVector.getY() * TIME_FRAME;

        double nx = deltaX + currentPosition.getX();
        double ny = deltaY + currentPosition.getY();

        currentPosition = new Point(nx, ny);
        move(deltaX, deltaY);
    }

    protected Point getCentroid() {
        List<Point> points = Point.buildList(getPoints());
        if (points.isEmpty()) {
            return new Point(0, 0);
        }
        points.add(points.get(0));

        double A = 0;
        double Cx = 0;
        double Cy = 0;

        for (int i = 0; i < points.size() - 1; i++) {
            Point pi = points.get(i);
            Point pj = points.get(i + 1);
            double secondFactor = (pi.getX() * pj.getY()) - (pj.getX() * pi.getY());
            double numX = (pi.getX() + pj.getX()) * secondFactor;
            double numY = (pi.getY() + pj.getY()) * secondFactor;

            Cx += numX;
            Cy += numY;
            A += secondFactor;
        }
        A *= 0.5;
        Cx *= 1 / (6 * A);
        Cy *= 1 / (6 * A);
        return new Point(Cx, Cy);
    }

    public void moveToCurrentPosition() {
        Point centroid = getCentroid();
        double deltaX = this.currentPosition.getX() - centroid.getX();
        double deltaY = this.currentPosition.getY() - centroid.getY();
        move(deltaX, deltaY);
    }

    private void move(double deltaX, double deltaY) {
        List<Point> asteriodPoints = Point.buildList(getPoints());

        for (int i = 0; i < asteriodPoints.size(); i++) {
            Point p = asteriodPoints.get(i);
            p.setX(p.getX() + deltaX);
            p.setY(p.getY() + deltaY);
        }

        getPoints().removeAll(getPoints());
        getPoints().addAll(Point.toDoubleList(asteriodPoints));
    }

    public List<LineEq> toLines() {
        List<Point> points = Point.buildList(getPoints());
        Point lastPoint = new Point(points.get(0).getX(), points.get(0).getY());
        points.add(lastPoint);

        List<LineEq> lines = new ArrayList<>();
        for (int i = 1; i < points.size(); i++) {
            LineEq line = LineEq.buildSegmentedLine(points.subList(i - 1, i + 1));
            lines.add(line);
        }

        return lines;
    }

    public boolean isOutsideMargin(){
        int [] coordinates = getMaxValues();
        
        if (coordinates[0] > properties.getWidth() + 50){
            return true;
        }
        if (coordinates[1] < -50){
            return true;
        }
        if (coordinates[2] > properties.getHeight() + 50){
            return true;
        }
        if (coordinates[3] < -50){
            return true;
        }
        return false;
    }
    
    public void rotate(double degrees) {
        double radians = Math.toRadians(degrees);
        List<Point> corners = Point.buildList(getPoints());
        List<Point> vectors = getCurrentPosition().changeOrigin(corners);

        List<Point> pointsUpdated = new ArrayList<>();
        for (Point vector : vectors) {
            double sqrX = Math.pow(vector.getX(), 2);
            double sqrY = Math.pow(vector.getY(), 2);

            double magnitude = Math.sqrt(sqrX + sqrY);
            double angle = Math.atan2(vector.getY(), vector.getX()) + radians;

            double x = magnitude * Math.cos(angle) + getCurrentPosition().getX();
            double y = magnitude * Math.sin(angle) + getCurrentPosition().getY();
            pointsUpdated.add(new Point(x, y));
        }

        getPoints().clear();
        getPoints().addAll(Point.toDoubleList(pointsUpdated));
    }
    
    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public void setCurrentPosition(Point currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Point getCurrentPosition() {
        return new Point (currentPosition.getX(), currentPosition.getY());
    }
    
    public Point getOrigin() {
        return origin;
    }

    public Point getSpeedVector() {
        return speedVector;
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public Properties getPropertiesImpl() {
        return properties;
    }

    public double getAngle(){
        return angle;
    }
}
