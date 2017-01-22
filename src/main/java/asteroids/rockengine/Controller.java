/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.rockengine;

import asteroids.elements.ShipElement;
import asteroids.shapes.Bullet;
import asteroids.shapes.SpaceShip;
import javafx.scene.paint.Color;
import java.util.Timer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author rafael
 */
public class Controller implements EventHandler<KeyEvent> {

    private SpaceShip ship;
    private RockEngine engine;
    private Timer timer;
    private boolean isPaused;

    public Controller(RockEngine engine) {
        this.ship = engine.getShip();
        this.engine = engine;
        isPaused = false;
        timer = new Timer();
        timer.schedule(this.engine, 50, 50);
    }

    @Override
    public void handle(KeyEvent event) {
        if (this.engine.isGameOver()){
            timer.cancel();
            return;
        }
        if (event.getCode() == KeyCode.P) {
            pauseGame();
        }
        if (isPaused) {
            return;
        }
        if (event.getCode() == KeyCode.LEFT) {
            this.ship.rotate(-15.0);
        }
        if (event.getCode() == KeyCode.RIGHT) {
            this.ship.rotate(15.0);
        }
        if (event.getCode() == KeyCode.UP) {
            this.ship.moveForward();
        }
        if (event.getCode() == KeyCode.SPACE) {
            ShipElement shipBoundary = (ShipElement) this.ship.getBoundary();
            Bullet bullet = new Bullet();
            bullet.addBulletElement(shipBoundary.shoot());
            engine.addShape(bullet);
        }
    }

    public void pauseGame() {
        if (isPaused) {
            timer = new Timer();
            this.engine = engine.getEngine();
            timer.schedule(this.engine, 50, 50);
            this.engine.resetRoot();
            isPaused = false;
            return;
        }
        timer.cancel();
        displayPausedMessage();
        isPaused = true;
    }
    
    private void displayPausedMessage(){
        Text text = new Text();
        text.setText("Game Paused");
        text.setFont(Font.font ("Verdana", FontWeight.BOLD, 20));
        text.setFill(Color.RED);
        text.setX(230);
        text.setY(300);
        text.setManaged(false);
        this.engine.getRoot().draw(text);
    }
}
