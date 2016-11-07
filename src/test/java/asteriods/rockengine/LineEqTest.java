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
        List<Double> points = new ArrayList<>();
        points.add(1.0);
        points.add(3.0);
        points.add(-2.0);
        points.add(5.0);
        LineEq line = LineEq.buildLine(points);
        assertEquals(line.getM(), ((double) -2/3), 0.001);
        assertEquals(line.getB(), ((double) 11/3), 0.001);
        
    }
    
    @Test
    public void areLineIntersected_parallelLines_false(){
        List<Double> pointsLine1 = new ArrayList<>();
        List<Double> pointsLine2 = new ArrayList<>();
        
        pointsLine1.add(0.0);
        pointsLine1.add(0.0);
        pointsLine1.add(1.0);
        pointsLine1.add(0.0);
        
        pointsLine2.add(0.0);
        pointsLine2.add(1.0);
        pointsLine2.add(1.0);
        pointsLine2.add(1.0);
        
        LineEq line1 = LineEq.buildLine(pointsLine1);
        LineEq line2 = LineEq.buildLine(pointsLine2);
        boolean result = LineEq.areLinesIntersected(line1, line2);
        assertEquals(false, result);
    }
    
    @Test
    public void areLineIntersected_linesWithDifferentSlopes_true(){
        List<Double> pointsLine1 = new ArrayList<>();
        List<Double> pointsLine2 = new ArrayList<>();
        
        pointsLine1.add(0.0);
        pointsLine1.add(0.0);
        pointsLine1.add(1.0);
        pointsLine1.add(1.1);
        
        pointsLine2.add(0.0);
        pointsLine2.add(1.0);
        pointsLine2.add(1.0);
        pointsLine2.add(0.0);
        
        LineEq line1 = LineEq.buildLine(pointsLine1);
        LineEq line2 = LineEq.buildLine(pointsLine2);
        boolean result = LineEq.areLinesIntersected(line1, line2);
        assertEquals(true, result);
    }
    
    @Test
    public void isPointOutside_pointInside_false(){
        List<Double> pointsLine1 = new ArrayList<>();
        
        pointsLine1.add(0.0);
        pointsLine1.add(0.0);
        pointsLine1.add(1.0);
        pointsLine1.add(1.1);
        
        LineEq line1 = LineEq.buildSegmentedLine(pointsLine1);
        boolean result = LineEq.isPointOutside(0.5,0.5,line1.getSegmentsPoints());
        assertEquals(false, result);
    }
    
    @Test
    public void isPointOutside_pointOutside_true(){
        List<Double> pointsLine1 = new ArrayList<>();
        
        pointsLine1.add(0.0);
        pointsLine1.add(0.0);
        pointsLine1.add(-1.0);
        pointsLine1.add(-1.0);
        
        LineEq line1 = LineEq.buildSegmentedLine(pointsLine1);
        boolean result = LineEq.isPointOutside(0.5,0.5,line1.getSegmentsPoints());
        assertEquals(true, result);
    }
    
    @Test
    public void areLinesIntersected_intersectionOutsideSegmentation_false(){
        List<Double> pointsLine1 = new ArrayList<>();
        List<Double> pointsLine2 = new ArrayList<>();
        
        pointsLine1.add(0.0);
        pointsLine1.add(0.0);
        pointsLine1.add(-1.0);
        pointsLine1.add(-1.0);
        
        pointsLine2.add(0.0);
        pointsLine2.add(1.0);
        pointsLine2.add(1.0);
        pointsLine2.add(0.0);
        
        LineEq line1 = LineEq.buildSegmentedLine(pointsLine1);
        LineEq line2 = LineEq.buildLine(pointsLine2);
        boolean result = LineEq.areLinesIntersected(line1, line2);
        assertEquals(false, result);
    }
    
    @Test
    public void getIntersectedPoints_TwoLines_getPoints(){
        List<Double> pointsLine1 = new ArrayList<>();
        List<Double> pointsLine2 = new ArrayList<>();
        
        pointsLine1.add(0.0);
        pointsLine1.add(0.0);
        pointsLine1.add(-1.0);
        pointsLine1.add(-1.0);
        
        pointsLine2.add(0.0);
        pointsLine2.add(1.0);
        pointsLine2.add(1.0);
        pointsLine2.add(0.0);
        
        LineEq line1 = LineEq.buildLine(pointsLine1);
        LineEq line2 = LineEq.buildLine(pointsLine2);
        
        double [] intersectedPoints = LineEq.getIntersectedPoints(line1, line2);
        assertEquals(0.5, intersectedPoints[0], 0.1);
        assertEquals(0.5, intersectedPoints[1], 0.1);
    }
}
