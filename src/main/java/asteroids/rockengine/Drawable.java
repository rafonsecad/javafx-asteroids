/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.rockengine;

import asteroids.elements.Element;

/**
 *
 * @author rafael
 */
public interface Drawable {
    void setDrawer(Object obj);
    void draw(Element ... e);
    void clear();
}
