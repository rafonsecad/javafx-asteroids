package asteriods.asteriods;

import asteriods.configuration.Properties;
import asteriods.configuration.PropertiesImpl;
import asteriods.elements.Ship;
import asteriods.rockengine.Point;
import asteriods.rockengine.RockEngine;
import java.util.Timer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        Properties properties = new PropertiesImpl();
        VBox root = new VBox();
        Scene scene = new Scene(root, properties.getWidth(), properties.getHeight());
        scene.setFill(Color.BLACK);
        RockEngine rockEngine = new RockEngine(root);
        rockEngine.initializeAsteriods(30);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("Asteriods");
        stage.setScene(scene);
        stage.show();

        Timer timer = new Timer();
        timer.schedule(rockEngine, 1000, 50);
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event){
                if (event.getCode() == KeyCode.LEFT){
                    Ship ship = rockEngine.getShip();
                    ship.setCurrentPosition(new Point(200, 200));
                    ship.moveToOrigin();
                }
            }
        });

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
