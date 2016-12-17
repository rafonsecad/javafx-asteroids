/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteriods.rockengine;

import asteriods.elements.Ship;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author rafael
 */
public class Controller implements EventHandler<KeyEvent>{
    private Ship ship;
    
    public Controller(Ship ship){
        this.ship = ship;
    }
    
    @Override
    public void handle(KeyEvent event){
        if (event.getCode() == KeyCode.LEFT){
            this.ship.rotate(-20.0);
        }
        if (event.getCode() == KeyCode.RIGHT){
            this.ship.rotate(20.0);
        }
        if (event.getCode() == KeyCode.UP){
            this.ship.moveForward();
        }
    }
}
