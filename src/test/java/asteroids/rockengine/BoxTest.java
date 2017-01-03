/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.rockengine;

import asteroids.rockengine.Box;
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
public class BoxTest {
    
    public BoxTest() {
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
     * Test of isBoxOverlapped method, of class Box.
     */
    @Test
    public void testIsBoxOverlapped() {
        System.out.println("isBoxOverlapped");
        Box a = new Box(new int [] {9, 14, 3, 7});
        Box b = new Box(new int [] {13, 19, 1, 4});
        
        assertTrue(a.isBoxOverlapped(b));
    }
    
    @Test
    public void testIsBoxOverlapped_false() {
        System.out.println("isBoxOverlapped");
        Box a = new Box(new int [] {9, 14, 3, 7});
        Box b = new Box(new int [] {15, 19, 1, 4});
        
        assertTrue(!a.isBoxOverlapped(b));
    }
    
    @Test
    public void testIsBoxOverlapped_edge_true() {
        System.out.println("isBoxOverlapped");
        Box a = new Box(new int [] {9, 14, 3, 7});
        Box b = new Box(new int [] {14, 19, 1, 4});
        
        assertTrue(a.isBoxOverlapped(b));
    }
}
