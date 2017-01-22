/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.rockengine;

import asteroids.configuration.Properties;
import asteroids.configuration.PropertiesImpl;
import asteroids.elements.AsteroidElement;
import asteroids.shapes.AsteroidUtil;
import asteroids.elements.Element;
import asteroids.elements.ShipElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;
import javafx.animation.FillTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author rafael
 */
public class RockEngine extends TimerTask {

    private Detectable collisionDetector;
    private List<Element> elements;
    private ShipElement ship;
    private Drawable root;
    private int score;
    private boolean gameOver;
    private Text scoreText;
    private Text endGameText;
    private int frameCounter;
    private Properties properties;

    private final int MaxNumberOfFrames = 1000;

    //final static Logger logger = Logger.getLogger(RockEngine.class);
    public RockEngine(Drawable root) {
        this.frameCounter = 0;
        this.root = root;
        this.score = 0;
        this.gameOver = false;
        this.properties = new PropertiesImpl();
        this.collisionDetector = new VectorCollisionDetector();
    }

    public void initialize(int numberOfInitialAsteroids) {
        elements = new ArrayList<>();
        createAsteroids(numberOfInitialAsteroids);
        ship = new ShipElement();
        elements.add(ship);
        root.draw(ship);
    }

    private void updateElementsPositions() {
        for (int i = 0; i < elements.size(); i++) {
            elements.get(i).updatePosition();
        }
        resetRoot();
    }

    public void processCollisionDetector() {

        for (int i = 0; i < this.elements.size(); i++) {
            this.collisionDetector.addElement(elements.get(i));
        }
    }

    public void removeAsteroids(List<Integer> indexes) {
        Collections.sort(indexes);
        Collections.reverse(indexes);
        List<AsteroidElement> removedAsteroids = new ArrayList<>();
        for (int i = 0; i < indexes.size(); i++) {
            int index = (int) indexes.get(i);
            checkIfGameIsOver(index);
            if (this.elements.get(index) instanceof AsteroidElement) {
                AsteroidElement asteroid = (AsteroidElement) this.elements.get(index);
                score++;
                removedAsteroids.add(asteroid.copy());
                removedAsteroids.add(asteroid.copy());
            }
            this.elements.remove(index);
        }
        resetRoot();
        //fadeRemovedAsteroids(removedAsteroids);
    }

    private void fadeRemovedAsteroids(List<AsteroidElement> removedAsteroids) {
        for (int i = 0; i < removedAsteroids.size(); i++) {
            setTransitionOnAsteroid(removedAsteroids, i);
        }
    }

    private void setTransitionOnAsteroid(List<AsteroidElement> removedAsteroids, int index) {
        AsteroidElement asteroid = removedAsteroids.get(index);
        this.root.draw(asteroid);
        if (index % 2 != 1 && index != 1) {
            return;
        }
        FillTransition ft = getFillTransition(asteroid);
        ft.play();
        if (index == removedAsteroids.size() - 1) {
            ft.setOnFinished((ActionEvent event) -> {
                resetRoot();
            });
        }
    }

    @Override
    public void run() {
        Platform.runLater(() -> {
            if (isGameOver()){
                showEndGame();
                return;
            }
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
            if (!(elements.get(index) instanceof AsteroidElement)) {
                return false;
            }
        }
        return true;
    }

    public void addElement(Element element) {
        elements.add(element);
        root.draw(element);
    }

    private void addAsteroids(int numberOfAsteroids) {
        if (this.frameCounter % properties.getAsteroidFrames() != 0) {
            return;
        }
        createAsteroids(numberOfAsteroids);
    }

    private void createAsteroids(int numberOfAsteroids) {
        for (int i = 0; i < numberOfAsteroids; i++) {
            AsteroidElement asteroid = AsteroidUtil.getRandomAsteroid();
            elements.add(asteroid);
            root.draw(asteroid);
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

    private void showScore(){
        scoreText = new Text();
        scoreText.setText("Score: " + score);
        scoreText.setFont(Font.font ("Verdana", FontWeight.BOLD, 20));
        scoreText.setFill(Color.RED);
        root.draw(scoreText);
    }
    
    private void checkIfGameIsOver(int index){
        if (!(this.elements.get(index) instanceof ShipElement)){
            return;
        }
        this.gameOver = true;
    }
    
    private void showEndGame(){
        if (endGameText != null){
            return;
        }
        endGameText = new Text();
        endGameText.setManaged(false);
        endGameText.setText("Game Over");
        endGameText.setFont(Font.font ("Verdana", FontWeight.BOLD, 20));
        endGameText.setFill(Color.RED);
        endGameText.setX(250);
        endGameText.setY(300);
        root.draw(endGameText);
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

    public ShipElement getShip() {
        return ship;
    }

    public void setShip(ShipElement ship) {
        this.ship = ship;
    }

    public void setFrameCounter(int frameCounter) {
        this.frameCounter = frameCounter;
    }

    public Drawable getRoot() {
        return root;
    }

    public void setScore(int score){
        this.score = score;
    }
    
    public void resetRoot() {
        root.clear();
        root.draw(elements);
        showScore();
        if (endGameText != null){
            root.draw(endGameText);
        }
    }

    public boolean isGameOver(){
        return this.gameOver;
    }
    
    protected FillTransition getFillTransition(AsteroidElement asteroid) {
        return new FillTransition(Duration.millis(800), asteroid, Color.rgb(180, 180, 180), Color.BLACK);
    }

    public RockEngine getEngine() {
        RockEngine engine = new RockEngine(root);
        engine.setElements(getElements());
        engine.setShip(getShip());
        engine.setFrameCounter(frameCounter);
        engine.setScore(score);
        return engine;
    }
}
