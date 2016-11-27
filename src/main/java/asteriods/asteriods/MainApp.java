package asteriods.asteriods;

import asteriods.elements.Asteriod;
import asteriods.rockengine.Point;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        VBox root = new VBox();
        Scene scene = new Scene(root, 600, 600);

        Asteriod a = new Asteriod();
        a.setManaged(false);

        Asteriod b = new Asteriod();
        b.setManaged(false);

        root.getChildren().add(a);
        root.getChildren().add(b);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();

        Point aOrigin = new Point(100.0, 200.0);
        a.setOrigin(aOrigin);
        a.setEndPoint(new Point(600.0, 600.0));
        a.setCurrentPosition(aOrigin);
        a.moveFromOrigin();
        a.setSpeed(32.0);
        a.calculateSpeedVector();

        Point bOrigin = new Point(0.0, 600);
        b.setOrigin(bOrigin);
        b.setEndPoint(new Point(600.0, 0.0));
        b.setCurrentPosition(bOrigin);
        b.moveFromOrigin();
        b.setSpeed(32.0);
        b.calculateSpeedVector();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()-> {
                        Point pa = a.getNextPoint();
                        a.updateAsteriodPosition(pa);
                        Point pb = b.getNextPoint();
                        b.updateAsteriodPosition(pb);
                });

            }
        }, 50, 50);

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
