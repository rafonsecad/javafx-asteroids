/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteriods;

import asteriods.rockengine.Container;
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
public class RockEngineTest {
    
    Container container;
    List<Double> square;
    double [] insidePoint;
    double [] outsidePoint;
    double [] edgePoint;
    
    public RockEngineTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        System.out.println("Setting up variables");
        container = new Container();
        square = new ArrayList<Double>();
        
        square.add(34.87);
        square.add(100.2);
        
        square.add(34.87);
        square.add(145.0);
        
        square.add(20.1);
        square.add(145.0);
        
        square.add(20.1); //x
        square.add(100.2); //y

        insidePoint = new double []{30.72, 123.39};
        outsidePoint = new double []{18.2, 90.6};
        edgePoint =  new double []{20.1, 100.2};
    }
    
    @After
    public void tearDown() {
        System.out.println("Tests Finished");
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void insidePointTest(){
        boolean r = container.isPointInPolygon(insidePoint[0], insidePoint[1], square);
        assertEquals(true, r);
    }
    
    @Test
    public void outsidePointTest(){
        boolean r = container.isPointInPolygon(outsidePoint[0], outsidePoint[1], square);
        assertEquals(false, r);
    }
    
    @Test
    public void edgePointTest(){
        boolean r = container.isPointInPolygon(edgePoint[0], edgePoint[1], square);
        assertEquals(true, r);
    }
}
