/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteriods.elements;

import asteriods.rockengine.Point;
import javafx.scene.paint.Color;

/**
 *
 * @author rafael
 */
public class Bullet extends Element{
    public Bullet (){
        getPoints().addAll(new Double [] {
            0.0, 0.0,
            3.0, 0.0,
            3.0, 5.0,
            0.0, 5.0
        });
        setFill(Color.GREEN);
    }
    
    public double getHalfLength(){
        int [] values = getMaxValues();
        return (values[3] - values[2])/2;
    }
    
    public void initialize (double angle, Point bulletPosition, Point shipPosition){
        setCurrentPosition(bulletPosition);
        moveToCurrentPosition();
        rotate(angle);
        setSpeed(40.0);
        setOrigin(shipPosition);
        setEndPoint(bulletPosition);
        calculateSpeedVector();
    }
}
