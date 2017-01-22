/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteriods.elements;

import asteroids.elements.AsteroidElement;
import javafx.scene.paint.Color;

/**
 *
 * @author rafael
 */
public class Ceres extends AsteroidElement{
    public Ceres(){
        getPoints().addAll(new Double [] {
            -10.0, 0.0,
            -5.0,  8.6,
             5.0,  8.6,
             10.0, 0.0,
             5.0, -8.6,
            -5.0, -8.6 
        });
        Color color = Color.rgb(180, 180, 180);
        setColor(color);
    }
}
