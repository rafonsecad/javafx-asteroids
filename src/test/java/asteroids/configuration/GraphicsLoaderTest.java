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
    
    @Test
    public void getPathStrings_svgFile_threeNodes(){
        String file = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                        "<svg\n" +
                        "   xmlns:dc=\"http://purl.org/dc/elements/1.1/\"\n" +
                        "   xmlns:cc=\"http://creativecommons.org/ns#\"\n" +
                        "   xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"\n" +
                        "   xmlns:svg=\"http://www.w3.org/2000/svg\"\n" +
                        "   xmlns=\"http://www.w3.org/2000/svg\"\n" +
                        "   xmlns:sodipodi=\"http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd\"\n" +
                        "   xmlns:inkscape=\"http://www.inkscape.org/namespaces/inkscape\"\n" +
                        "   version=\"1.1\"\n" +
                        "   id=\"svg2\"\n" +
                        "   viewBox=\"0 0 744.09448819 1052.3622047\"\n" +
                        "   width=\"210mm\"\n" +
                        "   height=\"297mm\"\n" +
                        "   inkscape:version=\"0.91 r13725\"\n" +
                        "   sodipodi:docname=\"drawing.svg\">\n" +
                        "  <sodipodi:namedview\n" +
                        "     pagecolor=\"#ffffff\"\n" +
                        "     bordercolor=\"#666666\"\n" +
                        "     borderopacity=\"1\"\n" +
                        "     objecttolerance=\"10\"\n" +
                        "     gridtolerance=\"10\"\n" +
                        "     guidetolerance=\"10\"\n" +
                        "     inkscape:pageopacity=\"0\"\n" +
                        "     inkscape:pageshadow=\"2\"\n" +
                        "     inkscape:window-width=\"1366\"\n" +
                        "     inkscape:window-height=\"703\"\n" +
                        "     id=\"namedview3194\"\n" +
                        "     showgrid=\"false\"\n" +
                        "     inkscape:zoom=\"7.85\"\n" +
                        "     inkscape:cx=\"179.25578\"\n" +
                        "     inkscape:cy=\"811.22965\"\n" +
                        "     inkscape:window-x=\"0\"\n" +
                        "     inkscape:window-y=\"0\"\n" +
                        "     inkscape:window-maximized=\"1\"\n" +
                        "     inkscape:current-layer=\"svg2\" />\n" +
                        "  <defs\n" +
                        "     id=\"defs8\">\n" +
                        "    <inkscape:perspective\n" +
                        "       sodipodi:type=\"inkscape:persp3d\"\n" +
                        "       inkscape:vp_x=\"0 : 526.1811 : 1\"\n" +
                        "       inkscape:vp_y=\"0 : 1000 : 0\"\n" +
                        "       inkscape:vp_z=\"744.09449 : 526.1811 : 1\"\n" +
                        "       inkscape:persp3d-origin=\"372.04724 : 350.7874 : 1\"\n" +
                        "       id=\"perspective3196\" />\n" +
                        "  </defs>\n" +
                        "  <metadata\n" +
                        "     id=\"metadata4\">\n" +
                        "    <rdf:RDF>\n" +
                        "      <cc:Work\n" +
                        "         rdf:about=\"\">\n" +
                        "        <dc:format>image/svg+xml</dc:format>\n" +
                        "        <dc:type\n" +
                        "           rdf:resource=\"http://purl.org/dc/dcmitype/StillImage\" />\n" +
                        "        <dc:title />\n" +
                        "      </cc:Work>\n" +
                        "    </rdf:RDF>\n" +
                        "  </metadata>\n" +
                        "  <path\n" +
                        "     id=\"path3288\"\n" +
                        "     d=\"m 194.33981,234.64456 -0.64012,2.74363 -0.85348,2.00496 0.32005,1.79392 0.85349,3.37678 -0.42674,1.58286 -2.98719,2.5326 -1.06686,1.05523 -2.13371,1.05525 -3.09388,0.73867 -1.81365,1.16076 -1.92034,0.73868 -4.26742,-0.31657 -1.92034,-0.73868 -1.28022,-1.26629 -1.28023,-2.00496 -0.96017,-0.94972 -1.92034,-3.79888 -3.41393,-2.95468 -0.64012,-0.94972 0,-1.37182 2.45377,-4.00992 2.02702,-4.43202 1.17355,-1.05525 1.49359,-0.42209 3.41393,0.31657 1.28023,-0.52762 2.45377,-3.48231 0.96017,-0.63314 4.69416,0.21104 2.24039,0.63315 2.77383,2.84916 2.56045,2.74363 z\"\n" +
                        "     style=\"fill:#8b4513;fill-rule:evenodd;stroke:#000000;stroke-width:0.6525355;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1;fill-opacity:1\"\n" +
                        "     inkscape:connector-curvature=\"0\" />\n" +
                        "  <path\n" +
                        "     id=\"path3290\"\n" +
                        "     d=\"m 182.39104,233.48379 -0.38162,1.32669 0.0615,1.2059 0.64012,1.05524 0.96017,0.8442 0.96017,0.52762 1.28022,0.10552 1.28023,-0.63314 0.53343,-0.8442 0.64011,-1.26629 -0.21338,-1.05524 -0.42673,-1.2663 -0.85348,-0.8442 -1.17355,-0.42209 -1.17354,0 -0.85348,0.21105 z\"\n" +
                        "     style=\"fill:#3c1900;fill-rule:evenodd;stroke:#000000;stroke-width:0.6525355;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1;fill-opacity:1\"\n" +
                        "     inkscape:connector-curvature=\"0\"\n" +
                        "     sodipodi:nodetypes=\"ccccccccccccccccc\" />\n" +
                        "  <path\n" +
                        "     id=\"path3292\"\n" +
                        "     d=\"m 183.24452,244.24728 1.28023,-0.31657 0.96016,0.31657 0.64012,0.73866 0,0.63315 -0.21338,0.73867 -0.85348,0.63315 -1.81365,-0.10552 -0.64012,-0.52763 -0.21336,-0.84419 0.32005,-0.42209 z\"\n" +
                        "     style=\"fill:#3c1900;fill-rule:evenodd;stroke:#000000;stroke-width:0.67428672;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1;fill-opacity:1\"\n" +
                        "     inkscape:connector-curvature=\"0\" />\n" +
                        "  <path\n" +
                        "     id=\"path3294\"\n" +
                        "     d=\"m 171.93585,236.43848 -0.73036,0.87473 -0.2298,0.81366 0.0903,0.94902 0.2298,0.95041 0.7468,0.84419 0.64012,0 0.74679,-0.21104 0.64011,-0.94972 0.42675,-1.05524 -0.21337,-0.99416 -0.21338,-0.48318 -0.42673,-0.63315 -0.64012,-0.31657 z\"\n" +
                        "     style=\"fill:#3c1900;fill-rule:evenodd;stroke:#000000;stroke-width:0.6525355;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1;fill-opacity:1\"\n" +
                        "     inkscape:connector-curvature=\"0\"\n" +
                        "     sodipodi:nodetypes=\"ccccccccccccccc\" />\n" +
                        "</svg>";
        
        GraphicsLoader gl = new GraphicsLoader();
        List<String> paths = gl.getPathStrings(file);
        List<String> expectedPaths = new ArrayList<>();
        expectedPaths.add("m 194.33981,234.64456 -0.64012,2.74363 -0.85348,2.00496 0.32005,1.79392 0.85349,3.37678 -0.42674,1.58286 -2.98719,2.5326 -1.06686,1.05523 -2.13371,1.05525 -3.09388,0.73867 -1.81365,1.16076 -1.92034,0.73868 -4.26742,-0.31657 -1.92034,-0.73868 -1.28022,-1.26629 -1.28023,-2.00496 -0.96017,-0.94972 -1.92034,-3.79888 -3.41393,-2.95468 -0.64012,-0.94972 0,-1.37182 2.45377,-4.00992 2.02702,-4.43202 1.17355,-1.05525 1.49359,-0.42209 3.41393,0.31657 1.28023,-0.52762 2.45377,-3.48231 0.96017,-0.63314 4.69416,0.21104 2.24039,0.63315 2.77383,2.84916 2.56045,2.74363 z");
        expectedPaths.add("m 182.39104,233.48379 -0.38162,1.32669 0.0615,1.2059 0.64012,1.05524 0.96017,0.8442 0.96017,0.52762 1.28022,0.10552 1.28023,-0.63314 0.53343,-0.8442 0.64011,-1.26629 -0.21338,-1.05524 -0.42673,-1.2663 -0.85348,-0.8442 -1.17355,-0.42209 -1.17354,0 -0.85348,0.21105 z");
        expectedPaths.add("m 183.24452,244.24728 1.28023,-0.31657 0.96016,0.31657 0.64012,0.73866 0,0.63315 -0.21338,0.73867 -0.85348,0.63315 -1.81365,-0.10552 -0.64012,-0.52763 -0.21336,-0.84419 0.32005,-0.42209 z");
        expectedPaths.add("m 171.93585,236.43848 -0.73036,0.87473 -0.2298,0.81366 0.0903,0.94902 0.2298,0.95041 0.7468,0.84419 0.64012,0 0.74679,-0.21104 0.64011,-0.94972 0.42675,-1.05524 -0.21337,-0.99416 -0.21338,-0.48318 -0.42673,-0.63315 -0.64012,-0.31657 z");
        
        for (int index=0; index<expectedPaths.size(); index++){
            assertEquals(expectedPaths.get(index), paths.get(index));
        }
    }
}
