import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.Group;
import java.util.concurrent.atomic.AtomicReference;

public class Sample extends Application {
    AtomicReference<Double> rotate = new AtomicReference<>(3D);

    @Override public void start(Stage stage) throws Exception {

        Circle outerCircle = new Circle(250,50,15,Color.RED);
        Circle innerCircle = new Circle(250,100,15,Color.GREEN);
        Circle circle1 = new Circle(250,150,100, Color.YELLOW);
        Circle circle2 = new Circle(250,150,50,Color.WHITE);
        Circle circle3 = new Circle(250,150,105,Color.GREEN);
        Circle circle4 = new Circle(250,150,55, Color.RED);
        Circle outerCircle1 = new Circle(250,250,15,Color.TRANSPARENT);
        Circle innerCircle1 = new Circle(250,200,15,Color.TRANSPARENT);

        Slider slider = new Slider(0,1,0.5);
        slider.setPrefSize(500,500);
        slider.setLayoutY(200);
        slider.setMajorTickUnit(0.25);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            rotate.set((Double)(newValue)*6D);
        });

        Group movingOuter = new Group(outerCircle1,outerCircle);
        Group movingInner = new Group(innerCircle1,innerCircle);
        Group root = new Group(circle3,circle1,circle4,circle2,movingOuter,movingInner, slider);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                movingOuter.setRotate(movingOuter.getRotate() + rotate.get());
                movingInner.setRotate(movingInner.getRotate() - (6 - rotate.get()));
            }
        };

        timer.start();
        Scene scene = new Scene(root,500,500);
        stage.setTitle("Spinning circles");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}