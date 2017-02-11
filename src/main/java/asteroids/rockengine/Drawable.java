/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.rockengine;

import asteroids.shapes.Shape;
import javafx.scene.Node;

/**
 *
 * @author rafael
 */
public interface Drawable {
    void setDrawer(Object obj);
    void draw(Node ... nodes);
    void draw(Shape shape);
    void clear();
}
