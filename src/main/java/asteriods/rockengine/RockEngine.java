/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteriods.rockengine;

import asteriods.elements.Asteriod;
import asteriods.elements.AsteriodUtil;
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

    private CollisionDetector collisionDetector;
    private List<Asteriod> asteriods;
    private int numberOfAsteriods;
    private VBox root;

    final static Logger logger = Logger.getLogger(RockEngine.class);

    public RockEngine(VBox root, int numberOfAsteriods) {
        this.numberOfAsteriods = numberOfAsteriods;
        this.root = root;
    }

    public void initializeAsteriods() {
        asteriods = new ArrayList<>();
        for (int i = 0; i < this.numberOfAsteriods; i++) {
            Asteriod asteriod = AsteriodUtil.getRandomAsteriod();
            asteriods.add(asteriod);
            asteriod.setManaged(false);
            root.getChildren().add(asteriod);
            asteriod.setRandomPath();
        }
    }

    private void updateAsteriodsPositions() {
        for (int i = 0; i < this.numberOfAsteriods; i++) {
            asteriods.get(i).updatePosition();
        }
    }

    public void processCollisionDetector() {
        this.collisionDetector = new CollisionDetector();
        for (int i = 0; i < this.numberOfAsteriods; i++) {
            this.collisionDetector.addElement(asteriods.get(i));
        }
    }

    public void removeAsteriods(List<Integer> indexes) {
        Collections.sort(indexes);
        Collections.reverse(indexes);
        List<Asteriod> removedAsteriods = new ArrayList<>();
        for (int i = 0; i < indexes.size(); i++) {
            removedAsteriods.add(this.asteriods.get((int) indexes.get(i)).copy());
            removedAsteriods.add(this.asteriods.get((int) indexes.get(i)).copy());
            this.asteriods.remove((int) indexes.get(i));
        }
        this.numberOfAsteriods = this.asteriods.size();
        this.root.getChildren().clear();
        this.root.getChildren().addAll(this.asteriods);
        fadeRemovedAsteriods(removedAsteriods);
    }

    private void fadeRemovedAsteriods (List<Asteriod> removedAsteriods){
        for (int i = 0; i < removedAsteriods.size(); i++) {
            this.root.getChildren().add(removedAsteriods.get(i));
            if (i % 2 == 1 || i == 1) {
                FillTransition ft = new FillTransition( Duration.millis(500),
                                                        removedAsteriods.get(i),
                                                        Color.rgb(180, 180, 180),
                                                        Color.BLACK);
                ft.play();
                if (i == removedAsteriods.size() - 1) {
                    ft.setOnFinished((ActionEvent event) -> {
                        root.getChildren().clear();
                        root.getChildren().addAll(asteriods);
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
            if (!indexOfCrashedElements.isEmpty()) {
                removeAsteriods(indexOfCrashedElements);
            }
        });
    }

    public List<Asteriod> getAsteriods() {
        return asteriods;
    }

    public void setAsteriods(List<Asteriod> asteriods) {
        this.asteriods = asteriods;
    }

    public int getNumberOfAsteriods() {
        return numberOfAsteriods;
    }

    public void setNumberOfAsteriods(int numberOfAsteriods) {
        this.numberOfAsteriods = numberOfAsteriods;
    }

    public CollisionDetector getCollisionDetector() {
        return collisionDetector;
    }

}
