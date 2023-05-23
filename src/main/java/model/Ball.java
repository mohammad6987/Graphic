package model;

import javafx.animation.Timeline;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Ball extends Circle {
    ConnectLine connectLine;
    private double velocityX;
    private double velocityY;
    private double accelerationX;
    private double accelerationY;
    public  boolean inMove;
    public boolean inLinerMove;
    public double time;
    public double SizeOverTime;
    public Line line;
    public Timeline CicularMovement;
    public Ball(double v, double v1, double v2, Paint paint) {
        super(v, v1, v2, paint);
        velocityX = 0;
        velocityY = 0;
        accelerationX = 0;
        accelerationY = 0;
        connectLine=null;
        time=0;
        SizeOverTime=0;
        line =null;
    }
    public void update() {
        // Update the velocity based on the acceleration
        velocityX += accelerationX;
        velocityY += accelerationY;
        // Update the position based on the velocity
        setCenterX(getCenterX() + velocityX);
        setCenterY(getCenterY() + velocityY);
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public double getAccelerationX() {
        return accelerationX;
    }

    public void setAccelerationX(double accelerationX) {
        this.accelerationX = accelerationX;
    }

    public double getAccelerationY() {
        return accelerationY;
    }

    public void setAccelerationY(double accelerationY) {
        this.accelerationY = accelerationY;
    }

}
