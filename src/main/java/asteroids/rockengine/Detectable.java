/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.rockengine;

import asteroids.elements.Element;
import java.util.List;
import java.util.Set;

/**
 *
 * @author rafael
 */
public interface Detectable {
    public void addElement(Element e);
    public void clearElements();
    public List<Set<Integer>> getCrashedElements();
}
