package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Ball;
import model.MainBall;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Game extends Application {
    int i=0;
    public static int spin=10;
    public static double realSpin=((double)spin/200-0.02);
    public static double wind=1.5;
    public static int freeze=5;
    public  int BallsCount=10;
    public int clockwise =1;
    public int BallsCountAtFirst=BallsCount;
    public MainBall mainBall;
    public Circle unknownBorder;
    public IntegerProperty ballsCount;
    Pane gamepane=new Pane();
    ArrayList<Ball> RoundingBalls=new ArrayList<>();
    Timeline Linermove;
    public double freezecount=0;
    Timeline Freeze;
    Timeline Visibility;
    Timeline RealTime;
    Timeline Reverse;
    Timeline Wind;
    int speed=150;
    public int Totaltime=120;
    public double time=0;

    ProgressBar progressBar = new ProgressBar();

    public boolean YouLost=false;
    public Timeline Condition;
    Ball[] balls=new Ball[BallsCount+5];
    public double a=1;
    public double frame=0.016;
    public double realFrame=frame;
    public boolean FreezeInPlay=false;
    public boolean ReverseInPlay=false;

    public static void setter(int spin,double wind,int freeze){
        Game.spin=spin;
        Game.wind=wind;
        Game.freeze=freeze;
    }

    @Override
    public void start(Stage stage) throws Exception {


        //progressBar.progressProperty().bind(freezeProgress);
        progressBar.setStyle("-fx-accent: green;");
        progressBar.setLayoutY(15);
        progressBar.setLayoutX(10);
        //scene desiagn
        stage.setMinHeight(700);
        stage.setMinWidth(500);
        mainBall=new MainBall(240,190,95, Color.BLACK);//0
        unknownBorder=new Circle(240,190,150,Color.TRANSPARENT);//1
        gamepane.getChildren().addAll(unknownBorder,mainBall);

        Label count =new Label(""+BallsCount);
        Ball currentBall=balls[BallsCount-1];


        count.setLayoutX(224);
        count.setLayoutY(174);
        count.setTextFill(Color.WHITE);
        count.setFont(Font.font("Ariel", FontWeight.BOLD, 30));
        gamepane.getChildren().add(count);
        for(int i=1;i<BallsCount+6;i++){
            balls[i-1]=new Ball(240,600,10,Color.BLACK);
        }
        Ball ball1=new Ball(240,340,10,Color.BLACK);
        ball1.inMove=true;
        RoundingBalls.add(ball1);
        balls[BallsCount+4].time=4;
        balls[BallsCount+4].inMove=true;
        balls[BallsCount+3].time=3;
        balls[BallsCount+3].inMove=true;
        balls[BallsCount+2].time=2;
        balls[BallsCount+2].inMove=true;
        balls[BallsCount+1].time=1;
        balls[BallsCount+1].inMove=true;
        balls[BallsCount+0].time=0;
        balls[BallsCount+0].inMove=true;
        gamepane.getChildren().addAll(balls);
        ballsCount=new SimpleIntegerProperty(BallsCount);


        RealTime=new Timeline(new KeyFrame(Duration.millis(16),event ->{
            time+=0.016;

            progressBar.setProgress((double) freezecount*0.25);
            for(Ball bally:balls){
                if(bally.inMove){
                    bally.time+=frame;
                    CircularMovement(bally);
                   // System.out.println(clockwise);
                  //  System.out.println(time+"\n");
                   // System.out.println(bally.time);
                }
            }
        }));
        RealTime.setCycleCount(-1);
        RealTime.play();
        Timeline freezeBar=new Timeline(new KeyFrame(Duration.millis(16),event ->{
            progressBar.setProgress((double) freezecount);
        }));
        freezeBar.setCycleCount(-1);
        freezeBar.play();
        Freeze=new Timeline(new KeyFrame(Duration.millis(16),event ->{
            frame=0.008;

        }));
        Freeze.setCycleCount(312);
        Freeze.setOnFinished(actionEvent -> {
            frame=realFrame;
            FreezeInPlay=false;

        });
        Reverse=new Timeline();
        KeyFrame reverse1=new KeyFrame(Duration.seconds(4),event ->{
            clockwise=(-1);
        });
        KeyFrame reverse2=new KeyFrame(Duration.seconds(4),event -> {
            clockwise=1;

        });
        Reverse.getKeyFrames().add(0,reverse1);
        Reverse.getKeyFrames().add(1,reverse2);
        Reverse.setCycleCount(Timeline.INDEFINITE);

        Scene scene=new Scene(gamepane);
        gamepane.getChildren().add(progressBar);
        scene.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.SPACE) && BallsCount>0 && !YouLost){
                LinerMove(balls[BallsCount-1]);
                BallsCount-=1;
                ballsCount.set(BallsCount);
                count.setText(""+BallsCount);
            }
            else if(keyEvent.getCode().equals(KeyCode.TAB) && BallsCount>0 && !YouLost && !FreezeInPlay && progressBar.getProgress()>=1){
                System.out.println("dcshfga");
                FreezeInPlay=true;
                freezecount=0;
                progressBar.setProgress(0);
                Freeze.play();
            }
        });

        ballsCount.addListener((observable,oldValue,newValue)->{
            if(ballsCount.get()<=(double)BallsCountAtFirst*0.75 && !ReverseInPlay){
                Reverse.play();
                ReverseInPlay=true;
                System.out.println("hhdgfh");
            }

        });

        stage.setScene(scene);
        stage.show();
    }
    public void CircularMovement(Ball ball){
        int speed=150;

            ball.setAccelerationX(speed * Math.sin(ball.time));
            ball.setAccelerationY(speed * Math.cos(ball.time));
            ball.setCenterX(240+ ball.getAccelerationX());
            ball.setCenterY(190+ ball.getAccelerationY());

    }
    public void LinerMove(Ball ball){
        ball.setVelocityY(-8);
        AtomicInteger z= new AtomicInteger();
       Linermove=new Timeline(new KeyFrame(Duration.millis(16),event ->{

           ball.update();
          // System.out.println(Math.pow(ball.getCenterY()-190,2)+Math.pow(ball.getCenterX()-240,2));
            if(Math.pow(ball.getCenterY()-190,2)+Math.pow(ball.getCenterX()-240,2)<=22500) {
                manage(ball);
            }
        }));
        Linermove.setCycleCount(Timeline.INDEFINITE);
        Linermove.play();
        if(z.get() ==1)
        {
            Linermove.stop();

        }

    }
    public void manage(Ball ball){
        for(Ball x:balls){
            if(x.inMove && !x.equals(ball) && ball.getBoundsInParent().intersects(x.getBoundsInParent())){
                System.out.println("yopu lose");
                YouLost=true;
                gamepane.setBackground(new Background(new BackgroundFill(Color.RED,new CornerRadii(5),new Insets(5))));
                RealTime.stop();
                //Circularmove.stop();
                return;
            }
            Linermove.stop();
            ball.inMove=true;
            freezecount+=0.02;
            RoundingBalls.add(ball);

            CircularMovement(ball);
            if(ballsCount.get()==0){
                RealTime.stop();
                gamepane.setBackground(new Background(new BackgroundFill(Color.GREEN,new CornerRadii(5),new Insets(5))));
            }
        }


    }

}
