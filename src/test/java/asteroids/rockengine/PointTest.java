/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.rockengine;

import asteroids.rockengine.Point;
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
public class PointTest {
    
    public PointTest() {
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
    public void build_doubleArray_pointsArray() {
        System.out.println("build_doubleArray_pointsArray");
        Double [] raw = new Double[]{0.0, 2.0, 1.0, 3.0 };
        
        Point [] points = Point.build (raw);
        
        Point [] ePoints = new Point [2];
        ePoints [0] = new Point (0.0, 2.0);
        ePoints [1] = new Point (1.0, 3.0);
        
        for (int i=0; i<2; i++){
            assertTrue(ePoints[i].equals(points[i]));
        }
    }
    
    @Test
    public void build_doubleList_pointsList() {
        System.out.println("build_doubleList_pointsList");
        List<Double> raw = new ArrayList<>();
        
        raw.add(0.00);
        raw.add(2.00);
        raw.add(1.00);
        raw.add(3.00);
        
        List<Point> pointList = Point.buildList(raw);
        List<Point> ePoint = new ArrayList<>();
        
        ePoint.add(new Point (0.00,2.00));
        ePoint.add(new Point (1.00,3.00));
        
        for (int i=0; i<2; i++){
            assertTrue(ePoint.get(i).equals(pointList.get(i)));
        }
    }
    
    @Test
    public void toDoubleArray_PointsArray_DoubleArray(){
        Point [] points = new Point [2];
        points [0] = new Point (0.0, 2.0);
        points [1] = new Point (1.0, 3.0);
        
        Double [] ePoints = new Double[]{0.0, 2.0, 1.0, 3.0 };
        
        Double [] dPoints = Point.toDoubleArray(points);
        
        for (int i=0; i<2; i++){
            assertEquals(ePoints[i], dPoints[i], 0.01);
        }
    }
    
    @Test
    public void toDoubleList_PointsList_Double_List(){
        System.out.println("toDoubleList_PointsList_Double_List");
        List<Double> raw = new ArrayList<>();
        
        raw.add(0.00);
        raw.add(2.00);
        raw.add(1.00);
        raw.add(3.00);
        
        List<Point> lPoint = new ArrayList<>();
        
        lPoint.add(new Point (0.00,2.00));
        lPoint.add(new Point (1.00,3.00));
        
        List<Double> dList = Point.toDoubleList(lPoint);
        
        for (int i=0; i<2; i++){
            assertEquals(raw.get(i), dList.get(i), 0.01);
        }
    }
    
    @Test
    public void changeOrigin_vectorsList_getList(){
        List<Point> points = new ArrayList<>();
        points.add(new Point(-1.0, 0.0));
        points.add(new Point(1.0, 0.0));
        points.add(new Point(1.0, -1.0));
        points.add(new Point(-1.0, -1.0));
        
        Point p = new Point (0.5, -0.5);
        
        List<Point> expectedPoints = new ArrayList<>();
        expectedPoints.add(new Point(-1.5, 0.5));
        expectedPoints.add(new Point(0.5, 0.5));
        expectedPoints.add(new Point(0.5, -0.5));
        expectedPoints.add(new Point(-1.5, -0.5));
        
        List<Point> result = p.changeOrigin(points);
        
        for (int i=0; i<expectedPoints.size(); i++){
            assertTrue(expectedPoints.get(i).equals(result.get(i)));
        }
    }
}
