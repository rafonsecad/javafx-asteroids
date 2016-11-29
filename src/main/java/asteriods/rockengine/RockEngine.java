/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteriods.rockengine;

import asteriods.configuration.Properties;
import asteriods.configuration.PropertiesImpl;
import asteriods.elements.Asteriod;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.layout.VBox;

/**
 *
 * @author rafael
 */
public class RockEngine extends TimerTask {

    private CollisionDetector collisionDetector;
    private List<Asteriod> asteriods;
    private int numberOfAsteriods;
    private VBox root;

    public RockEngine(VBox root, int numberOfAsteriods) {
        this.numberOfAsteriods = numberOfAsteriods;
        this.root = root;
    }

    public void initializeAsteriods() {
        asteriods = new ArrayList<>();
        for (int i = 0; i < this.numberOfAsteriods; i++) {
            Asteriod asteriod = new Asteriod();
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

    private void processCollisionDetector() {
        this.collisionDetector = new CollisionDetector();
        for (int i = 0; i < this.numberOfAsteriods; i++) {
            this.collisionDetector.addElement(asteriods.get(i));
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
                
            }
        });
    }
}
