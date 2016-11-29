package asteriods.asteriods;

import asteriods.rockengine.RockEngine;
import java.util.Timer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        VBox root = new VBox();
        Scene scene = new Scene(root, 600, 600);
        RockEngine rockEngine = new RockEngine(root, 10);
        rockEngine.initializeAsteriods();
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();

        Timer timer = new Timer();
        timer.schedule(rockEngine, 50, 50);
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                Platform.runLater(()-> {
//                    a.updatePosition();
//                    b.updatePosition();
//                    c.updatePosition();
//                    d.updatePosition();
//                    
//                    CollisionDetector collisionDetector = new CollisionDetector(600, 600);
//                    collisionDetector.addElement(a);
//                    collisionDetector.addElement(b);
//                    collisionDetector.addElement(c);
//                    collisionDetector.addElement(d);
//                    
//                    Set<Integer> index = collisionDetector.getCrashedElements();
//                    List<Integer> indexOfCrashedElements = new ArrayList<>(index);
//                    if (!indexOfCrashedElements.isEmpty()){
//                        timer.cancel();
//                    }
//                });
//            }
//        }, 50, 50);

    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void stop(){
        System.exit(0);
    }
}
