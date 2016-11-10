/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteriods.rockengine;

import asteriods.elements.Element;
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
public class CollisionDetectorTest extends CollisionDetector{
    
    public CollisionDetectorTest() {
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

    /**
     * Test of getWidth method, of class CollisionDetector.
     */
    @Test
    public void getPolyPointsInQuadrants_triangle_insertPoints(){
        System.out.println("getPolyPointsInQuadrants_triangle_insertPoints");
        Element e = new Element();
        e.getPoints().addAll(new Double []{
            -4.0, 1.0,
             2.0, 1.0,
             2.0, -2.0
        });
        
        Double [] expectedDoubles = new Double [] {
            -4.0, 1.0,
             1.0, 1.0,
             2.0, 1.0,
             2.0, -2.0,
            -(2.0/3.0), -(2.0/3.0)
        };
        Point [] expectedPoints = Point.build(expectedDoubles);
        List<Point> polyPoints = Point.buildList(e.getPoints());
        List<Point> points = getPolyPointsInQuadrants(polyPoints);
        for (int i=0; i<expectedPoints.length; i++){
            assertTrue (expectedPoints[i].equals(points.get(i)));
        }
    }
    
    @Test
    public void getMaxValues_Triangle_getMaxPoints(){
        System.out.println("getMaxValues_Triangle_getMaxPoints");
        Element e = new Element();
        e.getPoints().addAll(new Double []{
            -4.0, 1.0,
             2.0, 1.0,
             2.0, -2.0
        });
        
        int [] maxPoints = getMaxValues(e);
        assertEquals(-4, maxPoints[0]);
        assertEquals(2, maxPoints[1]);
        assertEquals(-2, maxPoints[2]);
        assertEquals(1, maxPoints[3]);
    }
    
    @Test
    public void isPointInPolygon_PointOutsideSquare_false(){
        System.out.println("isPointInPolygon_PointOutsideSquare_false");
        Element e = new Element();
        e.getPoints().addAll(new Double []{
             1.0, 1.0,
             -1.0, 1.0,
             -1.0, -1.0,
             1.0, -1.0
        });
        List<Point> points = Point.buildList(e.getPoints());
        boolean result = isPointInPolygon(new Point(5.0, 0.0) , points);
        assertEquals(false, result);
    }
    
    @Test
    public void isPointInPolygon_PointInsideSquare_true(){
        System.out.println("isPointInPolygon_PointInsideSquare_true");
        Element e = new Element();
        e.getPoints().addAll(new Double []{
             1.0, 1.0,
             -1.0, 1.0,
             -1.0, -1.0,
             1.0, -1.0
        });
        List<Point> points = Point.buildList(e.getPoints());
        boolean result = isPointInPolygon(new Point(0.0, 0.0), points);
        assertEquals(true, result);
    }
    
    @Test
    public void isPointInPolygon_PointInsideTriangle_true(){
        System.out.println("isPointInPolygon_PointInsideTriangle_true");
        Element e = new Element();
        e.getPoints().addAll(new Double []{
            -4.0, 1.0,
             2.0, 1.0,
             2.0, -2.0
        });

        List<Point> points = Point.buildList(e.getPoints());
        boolean result = isPointInPolygon(new Point(0.0, 0.0), points);
        assertEquals(true, result);
    }
    
    @Test
    public void isPointInPolygon_PointInsideTriangle_false(){
        System.out.println("isPointInPolygon_PointInsideTriangle_true");
        Element e = new Element();
        e.getPoints().addAll(new Double []{
            -4.0, 1.0,
             2.0, 1.0,
             2.0, -2.0
        });

        List<Point> points = Point.buildList(e.getPoints());
        boolean result = isPointInPolygon(new Point(5.0, 0.0), points);
        assertEquals(false, result);
    }
}
