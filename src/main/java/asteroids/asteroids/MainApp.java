package asteroids.asteroids;

import asteroids.configuration.Properties;
import asteroids.configuration.PropertiesImpl;
import asteroids.rockengine.Controller;
import asteroids.rockengine.Drawable;
import asteroids.rockengine.DrawableCanvasImpl;
import asteroids.rockengine.RockEngine;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        Properties properties = new PropertiesImpl();
        //VBox root = new VBox();
        Group root = new Group();
        Scene scene = new Scene(root, properties.getWidth(), properties.getHeight());
        scene.setFill(Color.BLACK);
        
        Canvas canvas = new Canvas(properties.getWidth(), properties.getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        Drawable drawable = new DrawableCanvasImpl();
        drawable.setDrawer(gc);
        RockEngine rockEngine = new RockEngine(drawable);
        rockEngine.initialize(20);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("Asteroids");
        stage.setScene(scene);
        stage.show();
  
        scene.setOnKeyPressed(new Controller(rockEngine));

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
