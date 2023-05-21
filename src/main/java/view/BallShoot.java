package view;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import model.Ball;

public class BallShoot extends Transition {
    Pane pane;
    Ball ball;
    Circle circle;
    Ball[] attachedBalls;

    public BallShoot(Pane pane, Ball ball, Circle circle) {
        this.setCycleDuration(Duration.millis(250));
        this.setCycleCount(-1);
        this.pane = pane;
        this.ball = ball;
        this.circle=circle;
    }

    @Override
    protected void interpolate(double v) {
        if(attachedBalls.length>0){
            for(int i=0;i<attachedBalls.length;i++){
                if(ball.getBoundsInParent().intersects((attachedBalls[i].getBoundsInParent()))){
                    pane.setBackground(new Background(new BackgroundFill(Color.RED,new CornerRadii(10), new Insets(5))));
                }
            }
        }
        if(ball.getBoundsInParent().intersects(circle.getBoundsInParent())){

        }
    }
}
