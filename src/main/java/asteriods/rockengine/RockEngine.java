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
        this.ship = new Ship();
        root.getChildren().add(ship);
    }

    public void initializeAsteriods(int numberOfInitialAsteriods) {
        elements = new ArrayList<>();
        for (int i = 0; i < numberOfInitialAsteriods; i++) {
            Asteriod asteriod = AsteriodUtil.getRandomAsteriod();
            elements.add(asteriod);
            asteriod.setManaged(false);
            root.getChildren().add(asteriod);
            asteriod.setRandomPath();
        }
    }

    private void updateAsteriodsPositions() {
        for (int i = 0; i < elements.size(); i++) {
            elements.get(i).updatePosition();
        }
    }

    public void processCollisionDetector() {
        this.collisionDetector = new CollisionDetector();
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
            Asteriod asteriod = (Asteriod) this.elements.get(index);
            removedAsteriods.add(asteriod.copy());
            removedAsteriods.add(asteriod.copy());
            this.elements.remove(index);
        }
        this.root.getChildren().clear();
        this.root.getChildren().addAll(this.elements);
        fadeRemovedAsteriods(removedAsteriods);
    }

    private void fadeRemovedAsteriods (List<Asteriod> removedAsteriods){
        for (int i = 0; i < removedAsteriods.size(); i++) {
            this.root.getChildren().add(removedAsteriods.get(i));
            if (i % 2 == 1 || i == 1) {
                FillTransition ft = getFillTransition( removedAsteriods.get(i));
                ft.play();
                if (i == removedAsteriods.size() - 1) {
                    ft.setOnFinished((ActionEvent event) -> {
                        root.getChildren().clear();
                        root.getChildren().addAll(elements);
                    });
                }
            }
        }
    }
    
    @Override
    public void run() {
        Platform.runLater(() -> {
            updateAsteriodsPositions();
            processCollisionDetector();
            Set<Integer> index = collisionDetector.getCrashedElements();
            List<Integer> indexOfCrashedElements = new ArrayList<>(index);
//            if (!indexOfCrashedElements.isEmpty()) {
//                //removeAsteriods(indexOfCrashedElements);
//            }
        });
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
    
    protected FillTransition getFillTransition (Asteriod asteriod){
        return new FillTransition (Duration.millis(500), asteriod, Color.rgb(180, 180, 180), Color.BLACK);
    }

}
