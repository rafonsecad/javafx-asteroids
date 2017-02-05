/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.elements;

import asteroids.rockengine.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.paint.Color;

/**
 *
 * @author rafael
 */
public class AsteroidElement extends Element {

    public AsteroidElement copy() {
        AsteroidElement asteriod = new AsteroidElement();
        List<Double> rawPoints = getPoints();
        List<Double> asteriodRawPoints = new ArrayList<>();
        for (int i = 0; i < rawPoints.size(); i++) {
            double coordinate = rawPoints.get(i);
            asteriodRawPoints.add(coordinate);
        }
        asteriod.getPoints().addAll(asteriodRawPoints);
        asteriod.setFill(Color.rgb(180, 180, 180));
        asteriod.setManaged(false);
        return asteriod;
    }

    public void scale(double factor) {
        List<Point> points = Point.buildList(getPoints());
        Point centroid = this.getCentroid();
        List<Point> centeredPoints = centroid.changeOrigin(points);
        Point[] scaledPoints = new Point[centeredPoints.size()];
        for (int i = 0; i < centeredPoints.size(); i++) {
            Point point = centeredPoints.get(i);
            scaledPoints[i] = new Point(point.getX() * factor + centroid.getX(), point.getY() * factor + centroid.getY());
        }
        Double[] doublePoints = Point.toDoubleArray(scaledPoints);
        getPoints().clear();
        getPoints().addAll(doublePoints);
    }

    public void scale(double factor, Point origin) {
        this.scale(factor);
        Point centroid = this.getCentroid();
        double xScaleVector = factor * (centroid.getX() - origin.getX());
        double yScaleVector = factor * (centroid.getY() - origin.getY());
        Point scaleVector = new Point(xScaleVector + origin.getX(), yScaleVector + origin.getY());
        setCurrentPosition(scaleVector);
        moveToCurrentPosition();
    }

    public void setRandomPath() {
        int direction = ThreadLocalRandom.current().nextInt(1, 5);
        Point[] pathPoints = new Point[2];
        switch (direction) {
            case 1:
                pathPoints = setPathFromTopOrigin(getRandomWidth(), getRandomWidth());
                break;
            case 2:
                pathPoints = setPathFromRightOrigin(getRandomHeight(), getRandomHeight());
                break;
            case 3:
                pathPoints = setPathFromBottomOrigin(getRandomWidth(), getRandomWidth());
                break;
            case 4:
                pathPoints = setPathFromLeftOrigin(getRandomHeight(), getRandomHeight());
                break;
        }

        setOrigin(pathPoints[0]);
        setEndPoint(pathPoints[1]);
        setCurrentPosition(pathPoints[0]);
        moveToCurrentPosition();
        int speed = ThreadLocalRandom.current().nextInt(1, 32);
        setSpeed((double) speed);
        calculateSpeedVector();
    }

    protected Point[] setPathFromTopOrigin(int randomXOrigin, int randomXEndPoint) {
        Point origin = new Point(randomXOrigin, 0);
        Point end = new Point(randomXEndPoint, getMaxHeight());
        return new Point[]{origin, end};
    }

    protected Point[] setPathFromRightOrigin(int randomYOrigin, int randomYEndPoint) {
        Point origin = new Point(getMaxWidth(), randomYOrigin);
        Point end = new Point(0, randomYEndPoint);
        return new Point[]{origin, end};
    }

    protected Point[] setPathFromBottomOrigin(int randomXOrigin, int randomXEndPoint) {
        Point origin = new Point(randomXOrigin, getMaxHeight());
        Point end = new Point(randomXEndPoint, 0);
        return new Point[]{origin, end};
    }

    protected Point[] setPathFromLeftOrigin(int randomYOrigin, int randomYEndPoint) {
        Point origin = new Point(0, randomYOrigin);
        Point end = new Point(getMaxWidth(), randomYEndPoint);
        return new Point[]{origin, end};
    }

    public int getRandomWidth() {
        return ThreadLocalRandom.current().nextInt(0, getMaxWidth());
    }

    public int getRandomHeight() {
        return ThreadLocalRandom.current().nextInt(0, getMaxHeight());
    }

    public int getMaxWidth() {
        return getPropertiesImpl().getWidth();
    }

    public int getMaxHeight() {
        return getPropertiesImpl().getHeight();
    }

    public Point[] getPathFromOrigin(Point[] path, Point vectorSecondElement) {
        double xOrigin = path[0].getX() + vectorSecondElement.getX();
        double yOrigin = path[0].getY() + vectorSecondElement.getY();
        Point origin = new Point(xOrigin, yOrigin);

        double xEndPoint = path[1].getX() + vectorSecondElement.getX();
        double yEndPoint = path[1].getY() + vectorSecondElement.getY();
        Point endPoint = new Point(xEndPoint, yEndPoint);

        return new Point[]{origin, endPoint};
    }

    public void setPathFromMainElement(AsteroidElement main, Point vectorDistance) {
        Point[] mainPath = new Point[]{main.getCurrentPosition(), main.getEndPoint()};
        Point[] path = main.getPathFromOrigin(mainPath, vectorDistance);
        setOrigin(path[0]);
        setEndPoint(path[1]);
        setCurrentPosition(path[0]);
        moveToCurrentPosition();
        setSpeed(main.getSpeed());
        calculateSpeedVector();
    }
    
    public void setPoints (Double [] coordinates){
        getPoints().clear();
        getPoints().addAll(coordinates);
        setCurrentPosition(getCentroid());
    }
}
