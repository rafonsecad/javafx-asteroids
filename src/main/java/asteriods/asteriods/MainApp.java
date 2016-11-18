package asteriods.asteriods;

import asteriods.elements.Asteriod;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainApp extends Application {

    
    double posX = 10.0;
    
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

        Timeline timeline = new Timeline();
        Duration time = new Duration(500);
	KeyValue keyValue = new KeyValue(a.translateXProperty(), 300);
	KeyFrame keyFrame = new KeyFrame(time, keyValue);
        KeyValue keyValueb = new KeyValue(b.translateXProperty(), posX);
        KeyValue keyValueb2 = new KeyValue(b.translateYProperty(), 60);
	KeyFrame keyFrameb = new KeyFrame(time, keyValueb, keyValueb2);
	timeline.getKeyFrames().add(keyFrame);
        timeline.getKeyFrames().add(keyFrameb);
	timeline.setCycleCount(1);
	//timeline.setAutoReverse(true);
	timeline.play();
        timeline.setOnFinished((ActionEvent event) -> {
            timeline.getKeyFrames().clear();
            posX+=0.8;
            timeline.getKeyFrames().add(new KeyFrame(time, new KeyValue(b.translateXProperty(), posX)));
            timeline.play();
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

}
