package model;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Ball extends Circle {
    ConnectLine connectLine;
    public Ball(double v, double v1, double v2, Paint paint) {
        super(v, v1, v2, paint);
        connectLine=null;
    }

}
