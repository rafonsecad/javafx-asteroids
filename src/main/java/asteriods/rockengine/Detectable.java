/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteriods.rockengine;

import asteriods.elements.Element;
import java.util.Set;

/**
 *
 * @author rafael
 */
public interface Detectable {
    public void addElement(Element e);
    public void clearElements();
    public Set<Integer> getCrashedElements();
}
