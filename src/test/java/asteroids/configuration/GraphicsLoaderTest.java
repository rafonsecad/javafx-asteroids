/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.configuration;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author rafael
 */
public class GraphicsLoaderTest {
    
    public GraphicsLoaderTest() {
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
     * Test of getFilename method, of class GraphicsLoader.
     */
    @Test
    public void parsePathString_onlyMPoints_array (){
        GraphicsLoader loader = new GraphicsLoader();
        String svgPath = "m 200,300 10,5 20,10";
        List<Double> expectedCoordinates = new ArrayList<>();
        expectedCoordinates.add(200.0);
        expectedCoordinates.add(300.0);
        expectedCoordinates.add(210.0);
        expectedCoordinates.add(305.0);
        expectedCoordinates.add(230.0);
        expectedCoordinates.add(315.0);
        List<Double> points = loader.parsePathString(svgPath);
        for (int index=0; index < points.size(); index++){
            assertEquals(expectedCoordinates.get(index), points.get(index), 0.01);
        }
    }
    
}
