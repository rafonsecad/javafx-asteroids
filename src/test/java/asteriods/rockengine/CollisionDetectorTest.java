/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteriods.rockengine;

import asteriods.elements.Element;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
            -1.0, 1.0,
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
        
        int [] maxPoints = e.getMaxValues();
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
    
    @Test
    public void mapPolygon_square_fillMap(){
        System.out.println("mapPolygon_square_fillMap");
        Element e = new Element();
        e.getPoints().addAll(new Double []{
             10.0, 20.0,
             10.0, 50.0,
             35.0, 50.0,
             35.0, 20.0
        });
        CollisionDetector col = new CollisionDetector(600, 600);
        col.mapPolygon(e, 0);
        int area = 0;
        for (int i=0; i<col.getMap().size();i++){
            for (int j=0; j<col.getMap().size();j++){
                if (col.getMap().get(i).get(j).size() > 0){
                    area++;
                }
            }
        }
        assertEquals(750, area);
    }
    
    @Test
    public void mapPolygon_triangle_fillMap(){
        System.out.println("mapPolygon_square_fillMap");
        Element e = new Element();
        e.getPoints().addAll(new Double []{
             10.0, 20.0,
             10.0, 50.0,
             35.0, 50.0
        });
        CollisionDetector col = new CollisionDetector(600, 600);
        col.mapPolygon(e, 0);
        int area = 0;
        for (int i=0; i<col.getMap().size();i++){
            for (int j=0; j<col.getMap().get(i).size();j++){
                if (col.getMap().get(i).get(j).size() > 0){
                    area++;
                }
            }
        }
        assertEquals(380, area);
    }
    
    @Test
    public void getCrashedElements_TriangleAndSquareOverlapped_getIndex(){
        System.out.println("getCrashedElements_TriangleAndSquareOverlapped_getIndex");
        Element triangle = new Element();
        triangle.getPoints().addAll(new Double []{
             10.0, 20.0,
             10.0, 50.0,
             35.0, 50.0
        });
        Element square = new Element();
        square.getPoints().addAll(new Double []{
             40.0, 50.0,
             40.0, 80.0,
             65.0, 80.0,
             65.0, 50.0
        });
        Element square2 = new Element();
        square2.getPoints().addAll(new Double []{
             10.0, 20.0,
             10.0, 50.0,
             35.0, 50.0,
             35.0, 20.0
        });
        CollisionDetector col = new CollisionDetector(600, 600);
        col.addElement(triangle);
        col.addElement(square);
        col.addElement(square2);
        Set<Integer> indexOfElementsCrashed = col.getCrashedElements();
        List<Integer> indexes = new ArrayList<Integer>(indexOfElementsCrashed);
        assertEquals(0, indexes.get(0).intValue());
        assertEquals(2, indexes.get(1).intValue());
    }
    
    @Test
    public void getCrashedElements_asteriodsWithOutCrash_getEmptyList(){
        System.out.println("getCrashedElements_asteriodsWithOutCrash_getEmptyList");
        Element a1 = new Element();
        Element a2 = new Element();
        Element a3 = new Element();
        Element a4 = new Element();
        Element a5 = new Element();
        Element a6 = new Element();
        Element a7 = new Element();
        Element a8 = new Element();
        Element a9 = new Element();
        Element a10 = new Element();
        a1.getPoints().addAll(new Double []{176.18894398321626, 196.51269203314166, 180.18894398321626, 200.51269203314163, 184.18894398321623, 204.5126920331416, 188.18894398321623, 200.51269203314163, 192.1889439832162, 196.51269203314166, 188.18894398321623, 192.5126920331417, 184.18894398321623, 188.51269203314175, 180.18894398321626, 192.5126920331417});
        a2.getPoints().addAll(new Double []{345.9615113486218, 332.4853322371158, 349.9615113486218, 336.48533223711576, 353.9615113486218, 340.4853322371157, 357.9615113486218, 336.48533223711576, 361.9615113486218, 332.4853322371158, 357.9615113486218, 328.48533223711587, 353.9615113486218, 324.4853322371159, 349.9615113486218, 328.48533223711587});
        a3.getPoints().addAll(new Double []{483.20999970833066, 125.99982500036477, 487.20999970833066, 129.99982500036478, 491.20999970833066, 133.99982500036472, 495.20999970833066, 129.99982500036478, 499.20999970833066, 125.99982500036477, 495.20999970833066, 121.99982500036475, 491.20999970833066, 117.99982500036474, 487.20999970833066, 121.99982500036475});
        a4.getPoints().addAll(new Double []{337.50061222657166, 84.12504055056102, 341.50061222657166, 88.12504055056102, 345.50061222657166, 92.12504055056101, 349.50061222657166, 88.12504055056102, 353.50061222657166, 84.12504055056102, 349.50061222657166, 80.12504055056104, 345.50061222657166, 76.12504055056104, 341.50061222657166, 80.12504055056104});
        a5.getPoints().addAll(new Double []{29.98668530775823, 539.3332667580014, 33.98668530775818, 543.3332667580014, 37.98668530775807, 547.3332667580014, 41.986685307757966, 543.3332667580014, 45.98668530775786, 539.3332667580014, 41.986685307757966, 535.3332667580014, 37.98668530775807, 531.3332667580014, 33.98668530775818, 535.3332667580014});
        a6.getPoints().addAll(new Double []{479.17056477604456, 179.47120890674813, 483.17056477604456, 183.47120890674813, 487.17056477604456, 187.47120890674807, 491.17056477604456, 183.47120890674813, 495.17056477604456, 179.47120890674813, 491.17056477604456, 175.4712089067482, 487.17056477604456, 171.47120890674822, 483.17056477604456, 175.4712089067482});
        a7.getPoints().addAll(new Double []{220.24913278350664, 325.55230950492506, 224.24913278350664, 329.5523095049251, 228.24913278350664, 333.5523095049252, 232.24913278350664, 329.5523095049251, 236.24913278350664, 325.55230950492506, 232.24913278350664, 321.55230950492506, 228.24913278350664, 317.552309504925, 224.24913278350664, 321.55230950492506});
        a8.getPoints().addAll(new Double []{208.11997355226413, 325.8668429849058, 212.11997355226413, 329.8668429849058, 216.11997355226413, 333.8668429849058, 220.11997355226413, 329.8668429849058, 224.11997355226413, 325.8668429849058, 220.11997355226413, 321.8668429849058, 216.11997355226413, 317.8668429849058, 212.11997355226413, 321.8668429849058});
        a9.getPoints().addAll(new Double []{337.2263191382861, 301.9420586965512, 341.2263191382862, 305.94205869655116, 345.2263191382863, 309.94205869655104, 349.22631913828644, 305.94205869655116, 353.22631913828656, 301.9420586965512, 349.22631913828644, 297.94205869655127, 345.2263191382863, 293.9420586965513, 341.2263191382862, 297.94205869655127});
        a10.getPoints().addAll(new Double []{423.07219373438227, 184.25778277830432, 427.07219373438227, 188.25778277830426, 431.07219373438227, 192.25778277830426, 435.07219373438227, 188.25778277830426, 439.07219373438227, 184.25778277830432, 435.07219373438227, 180.25778277830435, 431.07219373438227, 176.25778277830435, 427.07219373438227, 180.25778277830435});

        CollisionDetector col = new CollisionDetector();
//        col.addElement(a1);
//        col.addElement(a2);
//        col.addElement(a3);
//        col.addElement(a4);
//        col.addElement(a5);
//        col.addElement(a6);
        col.addElement(a7);
        col.addElement(a8);
//        col.addElement(a9);
//        col.addElement(a10);
        Set<Integer> indexOfElementsCrashed = col.getCrashedElements();
        List<Integer> indexes = new ArrayList<Integer>(indexOfElementsCrashed);
        assertEquals(2, indexes.size());
        System.out.println("Asteriod: " + indexes.get(0));
        System.out.println("Asteriod: " + indexes.get(1));
    }
}
