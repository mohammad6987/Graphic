package view;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Ball;

public class BallShoot extends Transition {
    Pane pane;
    Ball ball;

    public BallShoot( Pane pane, Ball ball) {
        this.setCycleDuration(Duration.millis(250));
        this.setCycleCount(-1);
        this.pane = pane;
        this.ball = ball;
    }

    @Override
    protected void interpolate(double v) {
        v=630;
    }
}
