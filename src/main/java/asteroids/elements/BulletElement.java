/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.elements;

import asteroids.rockengine.Point;
import javafx.scene.paint.Color;

/**
 *
 * @author rafael
 */
public class BulletElement extends Element{
    public BulletElement (){
        getPoints().addAll(new Double [] {
            0.0, 0.0,
            3.0, 0.0,
            3.0, 5.0,
            0.0, 5.0
        });
        Color color = Color.GREEN;
        setColor(color);
    }
    
    public double getHalfLength(){
        int [] values = getMaxValues();
        return (values[3] - values[2])/2;
    }
    
    public void initialize (double angle, Point bulletPosition, Point shipPosition){
        setCurrentPosition(bulletPosition);
        moveToCurrentPosition();
        rotate(angle);
        setSpeed(50.0);
        setOrigin(shipPosition);
        setEndPoint(bulletPosition);
        calculateSpeedVector();
    }
}
