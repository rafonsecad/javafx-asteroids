/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.rockengine;

import asteroids.elements.Bullet;
import asteroids.elements.Ship;
import java.util.Timer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author rafael
 */
public class Controller implements EventHandler<KeyEvent>{
    private Ship ship;
    private RockEngine engine;
    private Timer timer;
    private boolean isPaused;
    
    public Controller(RockEngine engine){
        this.ship = engine.getShip();
        this.engine = engine;
        isPaused = false;
        timer = new Timer();
        timer.schedule(this.engine, 50, 50);
    }
    
    @Override
    public void handle(KeyEvent event){
        if (event.getCode() == KeyCode.P){
            if (isPaused){
                timer = new Timer();
                this.engine = engine.getEngine();
                timer.schedule(this.engine, 50, 50);
                isPaused = false;
            }
            else{
                timer.cancel();
                isPaused = true;
            }
        }
        if (isPaused){
            return;
        }
        if (event.getCode() == KeyCode.LEFT){
            this.ship.rotate(-15.0);
        }
        if (event.getCode() == KeyCode.RIGHT){
            this.ship.rotate(15.0);
        }
        if (event.getCode() == KeyCode.UP){
            this.ship.moveForward();
        }
        if (event.getCode() == KeyCode.SPACE){
            Bullet bullet = this.ship.shoot();
            engine.addElement(bullet);
        }
    }
}
