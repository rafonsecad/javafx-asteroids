/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteriods;

import asteriods.elements.Element;
import asteriods.rockengine.CollisionDetector;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author rafael
 */
public class RockEngineTest {
    
    CollisionDetector detector;
    Element square;
    Element hexagon;
    Element triangle;
    
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
        detector = new CollisionDetector(600, 600);
        triangle = new Element();
        
        triangle.getPoints().addAll(new Double []{
            34.87, 100.2,
            34.87, 145.0,
            20.1,  145.0
        });
        
        detector.addElement(triangle);
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
    public void mapPolygonTest(){
        CalculatePointsArea();
    }
    
    private void CalculatePointsArea(){
        
        int totalPoints = 0;
        detector.getCrashedElements();
        List<List<List<Integer>>> testMap = detector.getMap();
        for (List<List<Integer>> x: testMap){
            for (List<Integer> y: x){
                if (y.size()>0){
                    for (Integer e: y){
                        totalPoints++;
                    }
                }
            }
        }
        System.out.println("Total Points: " + totalPoints);
    }
}
