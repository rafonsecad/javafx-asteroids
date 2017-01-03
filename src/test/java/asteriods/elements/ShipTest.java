/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteriods.elements;

import asteroids.elements.Ship;
import asteroids.rockengine.Point;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author rafael
 */
public class ShipTest {
    
    Ship ship;
    
    public ShipTest() {
        ship = new Ship();
        ship.getPoints().clear();
        Double [] coordinates = new Double [] {
            0.0, 0.0,
           -5.0, 10.0,
            0.0, 8.0,
            5.0, 10.0
        };
        ship.initialize(coordinates);
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
    public void rotate(){
        Ship ship = new Ship();
        ship.rotate(20.0);
        List<Point> expectedPoints = new ArrayList<>();
        expectedPoints.add(new Point(302.0521, 294.3618));
        expectedPoints.add(new Point(293.9334, 302.0486));
        expectedPoints.add(new Point(299.3159, 301.8793));
        expectedPoints.add(new Point(303.3305, 305.4688));
        
        List<Point> points = Point.buildList(ship.getPoints());
        
        for (int i=0; i<points.size(); i++){
            assertTrue(points.get(i).equals(expectedPoints.get(i)));
        }
    }
    
}
