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
        
        double [] expectedPoints = new double [] {
            -4.0, 1.0,
             1.0, 1.0,
             2.0, 1.0,
             2.0, -2.0,
            -(2.0/3.0), -(2.0/3.0)
        };
        
        List<Double> points = getPolyPointsInQuadrants(0.0, 0.0, e.getPoints());
        double [] pointsArray = points.stream().mapToDouble(Double::doubleValue).toArray();
        for (int i=0; i<expectedPoints.length; i++){
            assertEquals (expectedPoints[i], pointsArray[i], 0.01);
        }
    }
    
}
