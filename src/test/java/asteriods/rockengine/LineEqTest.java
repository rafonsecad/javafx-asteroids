/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteriods.rockengine;

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
public class LineEqTest extends LineEq{
    
    public LineEqTest() {
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
    public void buildLine_newLine_getMB (){
        List<Point> points = new ArrayList<>();
        points.add (new Point(1.0, 3.0));
        points.add (new Point(-2.0, 5.0));
        LineEq line = LineEq.buildLine(points);
        assertEquals(line.getM(), ((double) -2/3), 0.001);
        assertEquals(line.getB(), ((double) 11/3), 0.001);
        
    }
    
    @Test
    public void buildLine_inftySlope_getB (){
        List<Point> points = new ArrayList<>();
        points.add(new Point(2.0, 1.0));
        points.add(new Point(2.0,-2.0));
        LineEq line = LineEq.buildLine(points);
        assertEquals(2.0, line.getB(), 0.001);
        
    }
    
    @Test
    public void areLineIntersected_parallelLines_false(){
        List<Point> pointsLine1 = new ArrayList<>();
        List<Point> pointsLine2 = new ArrayList<>();

        pointsLine1.add(new Point(0.0, 0.0));
        pointsLine1.add(new Point(1.0, 0.0));
 
        pointsLine2.add(new Point(0.0, 1.0));
        pointsLine2.add(new Point(1.0, 1.0));

        LineEq line1 = LineEq.buildLine(pointsLine1);
        LineEq line2 = LineEq.buildLine(pointsLine2);
        boolean result = line1.areLinesIntersected(line2);
        assertEquals(false, result);
    }
    
    @Test
    public void areLineIntersected_linesWithDifferentSlopes_true(){
        List<Point> pointsLine1 = new ArrayList<>();
        List<Point> pointsLine2 = new ArrayList<>();
        
        pointsLine1.add(new Point(0.0, 0.0));
        pointsLine1.add(new Point(1.0, 1.1));
        
        pointsLine2.add(new Point(0.0, 1.0));
        pointsLine2.add(new Point(1.0, 0.0));
        
        LineEq line1 = LineEq.buildLine(pointsLine1);
        LineEq line2 = LineEq.buildLine(pointsLine2);
        boolean result = line1.areLinesIntersected(line2);
        assertEquals(true, result);
    }
    
    @Test
    public void isPointOutside_pointInside_false(){
        List<Point> pointsLine1 = new ArrayList<>();
        
        pointsLine1.add(new Point(0.0, 0.0));
        pointsLine1.add(new Point(1.0, 1.1));
        
        LineEq line1 = LineEq.buildSegmentedLine(pointsLine1);
        boolean result = line1.isPointOutside(new Point (0.5,0.5), line1.getSegmentsPoints());
        assertEquals(false, result);
    }
    
    @Test
    public void isPointOutside_pointOutside_true(){
        List<Point> pointsLine1 = new ArrayList<>();
        
        pointsLine1.add(new Point(0.0, 0.0));
        pointsLine1.add(new Point(-1.0, -1.0));
        
        LineEq line1 = LineEq.buildSegmentedLine(pointsLine1);
        boolean result = line1.isPointOutside(new Point (0.5,0.5), line1.getSegmentsPoints());
        assertEquals(true, result);
    }
    
    @Test
    public void areLinesIntersected_intersectionOutsideSegmentation_false(){
        List<Point> pointsLine1 = new ArrayList<>();
        List<Point> pointsLine2 = new ArrayList<>();
        
        pointsLine1.add(new Point(0.0, 0.0));
        pointsLine1.add(new Point(-1.0, -1.0));
        
        pointsLine2.add(new Point(0.0, 1.0));
        pointsLine2.add(new Point(1.0, 0.0));
        
        LineEq line1 = LineEq.buildSegmentedLine(pointsLine1);
        LineEq line2 = LineEq.buildLine(pointsLine2);
        boolean result = line1.areLinesIntersected(line2);
        assertEquals(false, result);
    }
    
    @Test
    public void getIntersectedPoints_TwoLines_getPoints(){
        List<Point> pointsLine1 = new ArrayList<>();
        List<Point> pointsLine2 = new ArrayList<>();
        
        pointsLine1.add(new Point(0.0, 0.0));
        pointsLine1.add(new Point(-1.0, -1.0));
        
        pointsLine2.add(new Point(0.0, 1.0));
        pointsLine2.add(new Point(1.0, 0.0));
        
        LineEq line1 = LineEq.buildLine(pointsLine1);
        LineEq line2 = LineEq.buildLine(pointsLine2);
        
        Point intersectedPoint = line1.getIntersectedPoint(line2);
        assertEquals(0.5, intersectedPoint.getX(), 0.1);
        assertEquals(0.5, intersectedPoint.getY(), 0.1);
    }
    
    @Test
    public void getIntersectedPoints_LineWithInftySlope_getPoints(){
        List<Point> pointsLine1 = new ArrayList<>();
        List<Point> pointsLine2 = new ArrayList<>();
        
        pointsLine1.add(new Point(0.0, 0.0));
        pointsLine1.add(new Point(-1.0, -1.0));
        
        pointsLine2.add(new Point(2.0, 1.0));
        pointsLine2.add(new Point(2.0, -2.0));
        
        LineEq line1 = LineEq.buildLine(pointsLine1);
        LineEq line2 = LineEq.buildLine(pointsLine2);
        
        Point intersectedPoint = line1.getIntersectedPoint(line2);
        assertEquals(2.0, intersectedPoint.getX(), 0.1);
        assertEquals(2.0, intersectedPoint.getY(), 0.1);
    }
    
    @Test
    public void getIntersectedPoints_LineWithInftySlope_getPoints2(){
        List<Point> pointsLine1 = new ArrayList<>();
        List<Point> pointsLine2 = new ArrayList<>();
        
        pointsLine1.add(new Point(2.0, 1.0));
        pointsLine1.add(new Point(2.0, -2.0));
        
        pointsLine2.add(new Point(0.0, 0.0));
        pointsLine2.add(new Point(-1.0, -1.0));
        
        LineEq line1 = LineEq.buildLine(pointsLine1);
        LineEq line2 = LineEq.buildLine(pointsLine2);
        
        Point intersectedPoint = line1.getIntersectedPoint(line2);
        assertEquals(2.0, intersectedPoint.getX(), 0.1);
        assertEquals(2.0, intersectedPoint.getY(), 0.1);
    }
    
    @Test
    public void areLinesIntersected_OutsideVectorLine_false(){
        List<Point> pointsLine2 = new ArrayList<>();
        
        Point vectorPoint = new Point(-1.0, -1.0);
        
        pointsLine2.add(new Point(0.0, 1.0));
        pointsLine2.add(new Point(2.0, 0.0));
        
        LineEq line1 = LineEq.buidVectorLine(vectorPoint);
        LineEq line2 = LineEq.buildSegmentedLine(pointsLine2);
        boolean result = line1.areLinesIntersected(line2);
        assertEquals(false, result);
    }
    
    @Test
    public void areLinesIntersected_InsideVectorLine_false(){
        List<Point> pointsLine2 = new ArrayList<>();
        
        Point vectorPoint = new Point(1.0, 1.0);
        
        pointsLine2.add(new Point(0.0, 1.0));
        pointsLine2.add(new Point(3.0, 1.0));
        
        LineEq line1 = LineEq.buidVectorLine(vectorPoint);
        LineEq line2 = LineEq.buildSegmentedLine(pointsLine2);
        boolean result = line1.areLinesIntersected(line2);
        assertEquals(true, result);
    }
    
}
