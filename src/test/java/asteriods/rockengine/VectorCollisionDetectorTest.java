/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteriods.rockengine;

import asteriods.elements.Ceres;
import asteriods.elements.Element;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author rafael
 */
public class VectorCollisionDetectorTest {
    
    public VectorCollisionDetectorTest() {
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
     * Test of getCrashedElements method, of class VectorCollisionDetector.
     */
    @Test
    public void areElementsIntersected_ElementsIntersected_true() {
        System.out.println("areElementsIntersected_ElementsIntersected_true");
        Element e1 = new Element();
        e1.getPoints().addAll(new Double[]{
            0.0, 0.0,
           10.0, 0.0,
           10.0, 5.0
        });
        Element e2 = new Element();
        e2.getPoints().addAll(new Double []{
            9.0, 3.0,
           14.0, 3.0,
           14.0, 7.0,
            9.0, 7.0
        });
        VectorCollisionDetector vcd = new VectorCollisionDetector();
        assertTrue(vcd.areElementsIntersected(e1, e2));
    }
    
    @Test
    public void areElementsIntersected_ElementsNoIntersected_false() {
        System.out.println("areElementsIntersected_ElementsNoIntersected_false");
        Element e1 = new Element();
        e1.getPoints().addAll(new Double[]{
            0.0, 0.0,
           10.0, 0.0,
           10.0, 5.0
        });
        Element e2 = new Element();
        e2.getPoints().addAll(new Double []{
           11.0, 3.0,
           14.0, 3.0,
           14.0, 7.0,
           11.0, 7.0
        });
        VectorCollisionDetector vcd = new VectorCollisionDetector();
        assertTrue(!vcd.areElementsIntersected(e1, e2));
    }
    
    @Test
    public void areElementsIntersected_ElementsIntersected_true2() {
        System.out.println("areElementsIntersected_ElementsIntersected_true2");
        Element e1 = new Element();
        e1.getPoints().addAll(new Double[]{
            0.0, 0.0,
           10.0, 0.0,
           10.0, 5.0
        });
        Element e2 = new Element();
        e2.getPoints().addAll(new Double []{
           10.0, 3.0,
           14.0, 3.0,
           14.0, 7.0,
           10.0, 7.0
        });
        VectorCollisionDetector vcd = new VectorCollisionDetector();
        assertTrue(vcd.areElementsIntersected(e1, e2));
    }
    
    @Test
    public void areElementsIntersected_ElementsNoIntersected_false2() {
        System.out.println("areElementsIntersected_ElementsNoIntersected_false2");
        Element e1 = new Element();
        e1.getPoints().addAll(new Double[]{
            0.0, 0.0,
           10.0, 0.0,
           10.0, 5.0
        });
        Element e2 = new Element();
        e2.getPoints().addAll(new Double []{
           10.1, 3.0,
           14.0, 3.0,
           14.0, 7.0,
           10.1, 7.0
        });
        VectorCollisionDetector vcd = new VectorCollisionDetector();
        assertTrue(!vcd.areElementsIntersected(e1, e2));
    }
    
//    @Test
//    public void getCrashedElements_AsteriodsNotCrashing_emptyList(){
//        System.out.println("getCrashedElements_AsteriodsNotCrashing_emptyList");
//        Ceres c1 = new Ceres();
//        Ceres c2 = new Ceres();
//        
//        c1.setCurrentPosition(new Point(25.0, 10.0));
//        c1.moveToCurrentPosition();
//        c2.setCurrentPosition(new Point(10.0, 25.0));
//        c2.moveToCurrentPosition();
//        
//        VectorCollisionDetector vcd = new VectorCollisionDetector();
//        vcd.addElement(c1);
//        vcd.addElement(c2);
//        Set<Integer> indexes = vcd.getCrashedElements();
//        assertEquals(0, indexes.size());
//    }
}
