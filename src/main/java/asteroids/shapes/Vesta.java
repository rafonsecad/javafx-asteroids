/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.shapes;

import asteroids.elements.AsteroidElement;
import javafx.scene.paint.Color;

/**
 *
 * @author rafael
 */
public class Vesta extends AsteroidElement{
    public Vesta (){
        getPoints().addAll(new Double [] {
            -5.0,  0.0,
            -2.5,  4.3,
             2.5,  4.3,
             5.0,  0.0,
             2.5, -4.3,
            -2.5, -4.3 
        });
        Color color = Color.rgb(180, 180, 180);
        setColor(color);
    }
}
