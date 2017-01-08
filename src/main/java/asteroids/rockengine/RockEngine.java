/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.rockengine;

import asteroids.configuration.Properties;
import asteroids.configuration.PropertiesImpl;
import asteroids.elements.Asteroid;
import asteroids.elements.AsteroidUtil;
import asteroids.elements.Element;
import asteroids.elements.Ship;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;
import javafx.animation.FillTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.apache.log4j.Logger;

/**
 *
 * @author rafael
 */
public class RockEngine extends TimerTask {

    private Detectable collisionDetector;
    private List<Element> elements;
    private Ship ship;
    private VBox root;
    private int frameCounter;
    private Properties properties;

    private final int MaxNumberOfFrames = 1000;

    //final static Logger logger = Logger.getLogger(RockEngine.class);
    public RockEngine(VBox root) {
        this.frameCounter = 0;
        this.root = root;
        this.properties = new PropertiesImpl();
        this.collisionDetector = new VectorCollisionDetector();
    }

    public void initialize(int numberOfInitialAsteroids) {
        elements = new ArrayList<>();
        createAsteroids(numberOfInitialAsteroids);
        ship = new Ship();
        elements.add(ship);
        root.getChildren().add(ship);
    }

    private void updateElementsPositions() {
        for (int i = 0; i < elements.size(); i++) {
            elements.get(i).updatePosition();
        }
    }

    public void processCollisionDetector() {

        for (int i = 0; i < this.elements.size(); i++) {
            this.collisionDetector.addElement(elements.get(i));
        }
    }

    public void removeAsteroids(List<Integer> indexes) {
        Collections.sort(indexes);
        Collections.reverse(indexes);
        List<Asteroid> removedAsteroids = new ArrayList<>();
        for (int i = 0; i < indexes.size(); i++) {
            int index = (int) indexes.get(i);
            if (this.elements.get(index) instanceof Asteroid) {
                Asteroid asteroid = (Asteroid) this.elements.get(index);
                removedAsteroids.add(asteroid.copy());
                removedAsteroids.add(asteroid.copy());
            }
            this.elements.remove(index);
        }
        this.root.getChildren().clear();
        this.root.getChildren().addAll(this.elements);
        fadeRemovedAsteroids(removedAsteroids);
    }

    private void fadeRemovedAsteroids(List<Asteroid> removedAsteroids) {
        for (int i = 0; i < removedAsteroids.size(); i++) {
            setTransitionOnAsteroid(removedAsteroids, i);
        }
    }

    private void setTransitionOnAsteroid(List<Asteroid> removedAsteroids, int index) {
        Asteroid asteroid = removedAsteroids.get(index);
        this.root.getChildren().add(asteroid);
        if (index % 2 != 1 && index != 1) {
            return;
        }
        FillTransition ft = getFillTransition(asteroid);
        ft.play();
        if (index == removedAsteroids.size() - 1) {
            ft.setOnFinished((ActionEvent event) -> {
                root.getChildren().clear();
                root.getChildren().addAll(elements);
            });
        }
    }

    @Override
    public void run() {
        Platform.runLater(() -> {
            updateElementsPositions();
            processCollisionDetector();
            List<Set<Integer>> indexes = collisionDetector.getCrashedElements();
            List<Integer> indexOfCrashedElements = new ArrayList<>(getCleanSet(indexes));
            if (!indexOfCrashedElements.isEmpty()) {
                removeAsteroids(indexOfCrashedElements);
            }
            getCollisionDetector().clearElements();
            resetFrameCounter();
            removeElementsOutsideBoundaries();
            addAsteroids(properties.getAdditionalAsteroids());
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
            if (!(elements.get(index) instanceof Asteroid)) {
                return false;
            }
        }
        return true;
    }

    public void addElement(Element element) {
        elements.add(element);
        root.getChildren().add(element);
    }

    private void addAsteroids(int numberOfAsteroids) {
        if (this.frameCounter % properties.getAsteroidFrames() != 0) {
            return;
        }
        createAsteroids(numberOfAsteroids);
    }

    private void createAsteroids(int numberOfAsteroids) {
        for (int i = 0; i < numberOfAsteroids; i++) {
            Asteroid asteroid = AsteroidUtil.getRandomAsteroid();
            elements.add(asteroid);
            root.getChildren().add(asteroid);
            asteroid.setRandomPath();
        }
    }

    private void removeElementsOutsideBoundaries() {
        if (this.frameCounter % 100 != 0) {
            return;
        }
        List<Integer> indexesOfRemovedElements = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).isOutsideMargin()) {
                indexesOfRemovedElements.add(i);
            }
        }
        Collections.sort(indexesOfRemovedElements);
        Collections.reverse(indexesOfRemovedElements);
        for (int i = 0; i < indexesOfRemovedElements.size(); i++) {
            int index = (int) indexesOfRemovedElements.get(i);
            this.elements.remove(index);
        }
    }

    private void resetFrameCounter() {
        this.frameCounter++;
        if (this.frameCounter == MaxNumberOfFrames) {
            this.frameCounter = 0;
        }
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public Detectable getCollisionDetector() {
        return collisionDetector;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public void setFrameCounter(int frameCounter) {
        this.frameCounter = frameCounter;
    }

    public VBox getRoot() {
        return root;
    }

    public void resetRoot() {
        root.getChildren().clear();
        root.getChildren().addAll(elements);
    }

    protected FillTransition getFillTransition(Asteroid asteroid) {
        return new FillTransition(Duration.millis(800), asteroid, Color.rgb(180, 180, 180), Color.BLACK);
    }

    public RockEngine getEngine() {
        RockEngine engine = new RockEngine(root);
        engine.setElements(getElements());
        engine.setShip(getShip());
        engine.setFrameCounter(frameCounter);
        return engine;
    }
}
