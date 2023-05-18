package view;

import javafx.animation.AnimationTimer;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Ball;
import model.MainBall;

public class Game extends Application {
    int i=0;
    public static int spin=10;
    public static double wind=1.5;
    public static int freeze=5;
    public  int BallsCount=60;
    public MainBall mainBall;
    public Circle unknownBorder;
    public IntegerProperty ballsCount;
    public static void setter(int spin,double wind,int freeze){
        Game.spin=spin;
        Game.wind=wind;
        Game.freeze=freeze;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane gamepane=new Pane();
        stage.setMinHeight(700);
        stage.setMinWidth(500);
        mainBall=new MainBall(240,190,95, Color.BLACK);
        unknownBorder=new Circle(240,190,150,Color.GREEN);
        gamepane.getChildren().addAll(unknownBorder,mainBall);
        Ball[] balls=new Ball[BallsCount];
        Label count =new Label(""+BallsCount);
        count.setLayoutX(224);
        count.setLayoutY(174);
        count.setTextFill(Color.WHITE);
        count.setFont(Font.font("Ariel", FontWeight.BOLD, 30));
        gamepane.getChildren().add(count);
        for(int i=1;i<BallsCount+1;i++){
            balls[i-1]=new Ball(240,600,20,Color.BLACK);
        }
        gamepane.getChildren().addAll(balls);
        ballsCount=new SimpleIntegerProperty(BallsCount);
        ballsCount.addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() == 0) { // change the background color when BallsCount reaches 50
                gamepane.setBackground(new Background(new BackgroundFill(Color.GREEN,new CornerRadii(5),new Insets(5))));
            }
        });
        Scene scene=new Scene(gamepane);
        scene.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.SPACE) && BallsCount>0){
                BallsCount-=1;
                ballsCount.set(BallsCount);
                System.out.println(BallsCount);
                count.setText(""+BallsCount);
                Ball ball = balls[BallsCount];
                TranslateTransition shot=new TranslateTransition(Duration.millis(450),ball);
                shot.setToY(-600);
                shot.setByX(0);
                shot.play();

                System.out.println(ball.getTranslateX());
                shot.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        gamepane.getChildren().remove(ball);
                    }
                });

            }
        });

        stage.setScene(scene);
        stage.show();
    }

}
