/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteriods.rockengine;

import asteriods.elements.Asteriod;
import asteriods.elements.AsteriodUtil;
import asteriods.elements.Element;
import asteriods.elements.Ship;
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

    final static Logger logger = Logger.getLogger(RockEngine.class);

    public RockEngine(VBox root) {
        this.root = root;
        this.collisionDetector = new VectorCollisionDetector();
    }

    public void initializeAsteriods(int numberOfInitialAsteriods) {
        elements = new ArrayList<>();
        for (int i = 0; i < numberOfInitialAsteriods; i++) {
            Asteriod asteriod = AsteriodUtil.getRandomAsteriod();
            elements.add(asteriod);
            root.getChildren().add(asteriod);
            asteriod.setRandomPath();
        }
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

    public void removeAsteriods(List<Integer> indexes) {
        Collections.sort(indexes);
        Collections.reverse(indexes);
        List<Asteriod> removedAsteriods = new ArrayList<>();
        for (int i = 0; i < indexes.size(); i++) {
            int index = (int) indexes.get(i);
            if (this.elements.get(index) instanceof Asteriod) {
                Asteriod asteriod = (Asteriod) this.elements.get(index);
                removedAsteriods.add(asteriod.copy());
                removedAsteriods.add(asteriod.copy());
            }
            this.elements.remove(index);
        }
        this.root.getChildren().clear();
        this.root.getChildren().addAll(this.elements);
        fadeRemovedAsteriods(removedAsteriods);
    }

    private void fadeRemovedAsteriods(List<Asteriod> removedAsteriods) {
        for (int i = 0; i < removedAsteriods.size(); i++) {
            setTransitionOnAsteriod(removedAsteriods, i);
        }
    }

    private void setTransitionOnAsteriod(List<Asteriod> removedAsteriods, int index) {
        Asteriod asteriod = removedAsteriods.get(index);
        this.root.getChildren().add(asteriod);
        if (index % 2 != 1 && index != 1) {
            return;
        }
        FillTransition ft = getFillTransition(asteriod);
        ft.play();
        if (index == removedAsteriods.size() - 1) {
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
                removeAsteriods(indexOfCrashedElements);
            }
            getCollisionDetector().clearElements();
        });
    }

    private Set<Integer> getCleanSet(List<Set<Integer>> indexes){
        Set<Integer> indexesToRemove = new HashSet<>();
        for (Set<Integer> indexesSet : indexes){
            if (!isFullOfAsteriods(indexesSet)){
                for (Integer index: indexesSet){
                    indexesToRemove.add(index);
                }
            }
        }
        return indexesToRemove;
    }
    
    private boolean isFullOfAsteriods(Set<Integer> indexesSet){
        for(Integer index : indexesSet){
            if (!(elements.get(index) instanceof Asteriod)){
                return false;
            }
        }
        return true;
    }
    
    public void addElement(Element element) {
        elements.add(element);
        root.getChildren().add(element);
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

    protected FillTransition getFillTransition(Asteriod asteriod) {
        return new FillTransition(Duration.millis(800), asteriod, Color.rgb(180, 180, 180), Color.BLACK);
    }

}
