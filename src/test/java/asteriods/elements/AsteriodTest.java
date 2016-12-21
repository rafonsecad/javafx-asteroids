/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteriods.elements;

import asteriods.rockengine.Point;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rafael
 */
public class AsteriodTest extends Asteriod{
    
    public AsteriodTest() {
        this.getPoints().addAll(new Double []{
            -8.0, 0.0,
            -4.0, 4.0,
             0.0, 8.0,
             4.0, 4.0,
             8.0, 0.0,
             4.0,-4.0,
             0.0,-8.0,
            -4.0,-4.0
        });
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void calculateSpeedVector_setOriginEndPointSpeed_getSpeedVector(){
        System.out.println("calculateSpeedVector_setOriginEndPointSpeed_getSpeedVector");

        setOrigin(new Point(0.0, 0.0));
        setEndPoint(new Point (600.0, 600.0));
        setSpeed(5.0);
        calculateSpeedVector();
        Point speedVector = getSpeedVector();
        assertTrue(speedVector.equals(new Point(3.535, 3.535)));
    }

    @Test
    public void calculateSpeedVector_setOriginEndPointSpeed_getSpeedVector2(){
        System.out.println("calculateSpeedVector_setOriginEndPointSpeed_getSpeedVector2");

        setOrigin(new Point(100.0, 200.0));
        setEndPoint(new Point (600.0, 600.0));
        setSpeed(4.0);
        calculateSpeedVector();
        Point speedVector = getSpeedVector();
        System.out.println(speedVector.getX() + " " + speedVector.getY());
        assertTrue(speedVector.equals(new Point(3.1234, 2.498)));
    }
    
    @Test
    public void updatePosition_nextPoint_getPoints(){
        System.out.println("updateAsteriodPosition_nextPoint_getPoints");
        setOrigin(new Point(100.0, 200.0));
        setEndPoint(new Point (600.0, 600.0));
        setSpeed(4.0);
        setCurrentPosition(new Point(6.2, 10.2));
        calculateSpeedVector();
        updatePosition();
        List<Double> asteriodDoublePoints = getPoints();
        List<Point> asteriodPoints = Point.buildList(asteriodDoublePoints);
        List<Point> expectedPoints = new ArrayList<>();        
        expectedPoints.add(new Point(-7.688, 0.249));
        expectedPoints.add(new Point(-3.688, 4.249));
        expectedPoints.add(new Point(0.312, 8.249));
        expectedPoints.add(new Point(4.312, 4.249));
        expectedPoints.add(new Point(8.312, 0.249));
        expectedPoints.add(new Point(4.312,-3.751));
        expectedPoints.add(new Point(0.312,-7.751));
        expectedPoints.add(new Point(-3.688,-3.751));
        
        for (int i=0; i<expectedPoints.size(); i++){
            assertTrue(expectedPoints.get(i).equals(asteriodPoints.get(i)));
        }
    }
    
    @Test
    public void getCentroid_asteriodPoints_getPoint(){
        Point centroid = getCentroid();
        assertTrue(centroid.equals(new Point(0.0, 0.0)));
    }
    
    @Test
    public void moveFromOrigin_currentPositionFix_moveAsteriod(){
        setCurrentPosition(new Point(100.0, 100.0));
        moveToCurrentPosition();
        List<Point> expectedPoints = new ArrayList<>();
        expectedPoints.add(new Point(92.0, 100.0));
        expectedPoints.add(new Point(96.0, 104.0));
        expectedPoints.add(new Point(100.0, 108.0));
        expectedPoints.add(new Point(104.0, 104.0));
        expectedPoints.add(new Point(108.0, 100.0));
        expectedPoints.add(new Point(104.0, 96.0));
        expectedPoints.add(new Point(100.0, 92.0));
        expectedPoints.add(new Point(96.0, 96.0));
        List<Point> pointsOfAsteriod = Point.buildList(getPoints());
        for (int i=0; i<expectedPoints.size(); i++){
            assertTrue(expectedPoints.get(i).equals(pointsOfAsteriod.get(i)));
        }
    }
}
