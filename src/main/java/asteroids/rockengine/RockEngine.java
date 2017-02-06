/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.rockengine;

import asteroids.configuration.Properties;
import asteroids.configuration.PropertiesImpl;
import asteroids.elements.AsteroidElement;
import asteroids.elements.ShipElement;
import asteroids.shapes.Asteroid;
import asteroids.shapes.Shape;
import asteroids.shapes.SpaceShip;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author rafael
 */
public class RockEngine extends TimerTask {

    private Detectable collisionDetector;
    private List<Shape> shapes;
    private SpaceShip ship;
    private Drawable root;
    private int score;
    private boolean gameOver;
    private Text scoreText;
    private Text endGameText;
    private int frameCounter;
    private Properties properties;

    private final int MaxNumberOfFrames = 1000;

    public RockEngine(Drawable root) {
        this.frameCounter = 0;
        this.root = root;
        this.score = 0;
        this.gameOver = false;
        this.properties = new PropertiesImpl();
        this.collisionDetector = new VectorCollisionDetector();
    }

    public void initialize(int numberOfInitialAsteroids) {
        shapes = new ArrayList<>();
        createAsteroids(numberOfInitialAsteroids);
        ship = new SpaceShip();
        shapes.add(ship);
        root.draw(ship.getElements());
    }

    private void updateElementsPositions() {
        for (int i = 0; i < shapes.size(); i++) {
            shapes.get(i).updatePosition();
        }
        resetRoot();
    }

    public void processCollisionDetector() {

        for (int i = 0; i < this.shapes.size(); i++) {
            this.collisionDetector.addElement(shapes.get(i).getBoundary());
        }
    }

    public void removeShapes(List<Integer> indexes) {
        Collections.sort(indexes);
        Collections.reverse(indexes);
        for (int i = 0; i < indexes.size(); i++) {
            int index = (int) indexes.get(i);
            checkIfGameIsOver(index);
            if (this.shapes.get(index).getBoundary() instanceof AsteroidElement) {
                score++;
            }
            this.shapes.remove(index);
        }
        resetRoot();
    }

    @Override
    public void run() {
        Platform.runLater(() -> {
            try {
                if (isGameOver()) {
                    showEndGame();
                    return;
                }
                updateElementsPositions();
                processCollisionDetector();
                List<Set<Integer>> indexes = collisionDetector.getCrashedElements();
                List<Integer> indexOfCrashedElements = new ArrayList<>(getCleanSet(indexes));
                if (!indexOfCrashedElements.isEmpty()) {
                    removeShapes(indexOfCrashedElements);
                }
                getCollisionDetector().clearElements();
                resetFrameCounter();
                removeElementsOutsideBoundaries();
                addAsteroids(properties.getAdditionalAsteroids());
            }
            catch(Exception t){
                System.out.println(t.getMessage());
            }
        });
    }

    private Set<Integer> getCleanSet(List<Set<Integer>> indexes) {
        Set<Integer> indexesToRemove = new HashSet<>();
        for (Set<Integer> indexesSet : indexes) {
            if (!isFullOfAsteroids(indexesSet)) {
                for (Integer index : indexesSet) {
                    indexesToRemove.add(index);
                }
            }
        }
        return indexesToRemove;
    }

    private boolean isFullOfAsteroids(Set<Integer> indexesSet) {
        for (Integer index : indexesSet) {
            if (!(shapes.get(index).getBoundary() instanceof AsteroidElement)) {
                return false;
            }
        }
        return true;
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
        root.draw(shape.getElements());
    }

    private void addAsteroids(int numberOfAsteroids) {
        if (this.frameCounter % properties.getAsteroidFrames() != 0) {
            return;
        }
        createAsteroids(numberOfAsteroids);
    }

    private void createAsteroids(int numberOfAsteroids) {
        Asteroid asteroidFactory = new Asteroid();
        for (int i = 0; i < numberOfAsteroids; i++) {
            Asteroid asteroid = asteroidFactory.getRandomAsteroid();
            shapes.add(asteroid);
            root.draw(asteroid.getElements());
        }
    }

    private void removeElementsOutsideBoundaries() {
        if (this.frameCounter % 100 != 0) {
            return;
        }
        List<Integer> indexesOfRemovedElements = new ArrayList<>();
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).getBoundary().isOutsideMargin()) {
                indexesOfRemovedElements.add(i);
            }
        }
        Collections.sort(indexesOfRemovedElements);
        Collections.reverse(indexesOfRemovedElements);
        for (int i = 0; i < indexesOfRemovedElements.size(); i++) {
            int index = (int) indexesOfRemovedElements.get(i);
            this.shapes.remove(index);
        }
    }

    private void resetFrameCounter() {
        this.frameCounter++;
        if (this.frameCounter == MaxNumberOfFrames) {
            this.frameCounter = 0;
        }
    }

    private void showScore() {
    }

    private void checkIfGameIsOver(int index) {
        if (!(this.shapes.get(index).getBoundary() instanceof ShipElement)) {
            return;
        }
        this.gameOver = true;
    }

    private void showEndGame() {
        if (endGameText != null) {
            return;
        }
        endGameText = new Text();
        endGameText.setManaged(false);
        endGameText.setText("Game Over");
        endGameText.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        endGameText.setFill(Color.RED);
        endGameText.setX(250);
        endGameText.setY(300);
        root.draw(endGameText);
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public Detectable getCollisionDetector() {
        return collisionDetector;
    }

    public SpaceShip getShip() {
        return ship;
    }

    public void setShip(SpaceShip ship) {
        this.ship = ship;
    }

    public void setFrameCounter(int frameCounter) {
        this.frameCounter = frameCounter;
    }

    public Drawable getRoot() {
        return root;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void resetRoot() {
        root.clear();
        for (Shape shape : shapes) {
            root.draw(shape.getElements());
        }
        showScore();
        if (endGameText != null) {
            root.draw(endGameText);
        }
    }

    public boolean isGameOver() {
        return this.gameOver;
    }

    public RockEngine getEngine() {
        RockEngine engine = new RockEngine(root);
        engine.setShapes(getShapes());
        engine.setShip(getShip());
        engine.setFrameCounter(frameCounter);
        engine.setScore(score);
        return engine;
    }
}
