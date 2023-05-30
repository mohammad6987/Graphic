package view;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
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
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Ball;
import model.MainBall;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Double.NaN;

public class Game2 extends Application {
    public static MediaPlayer mediaPlayer;
    public int SongI;
    int i=0;
    public static int spin=10;
    public static double realSpin=((double)spin/200-0.02);
    public static double wind=1.5;
    public static int freeze=5;
    public  int BallsCount=10;
    public double clockwise =-1;
    public int BallsCountAtFirst=BallsCount;
    public MainBall mainBall;
    public Circle unknownBorder;
    public IntegerProperty ballsCount;
    Pane gamepane=new Pane();
    ArrayList<Ball> RoundingBalls=new ArrayList<>();
    Timeline Linermove;
    public double freezecount=0;
    public static Timeline Freeze;
    public static Timeline RealTime;
    public static Timeline Wind;
    int speed=150;
    public int Totaltime=60;
    public double time=0;
    public DoubleProperty timeCheck;
    public boolean Reverse=false;

    public int windSpeed=0;
    ProgressBar progressBar = new ProgressBar();

    public boolean YouLost=false;
    public Timeline Condition;
    Ball[] balls=new Ball[BallsCount+5];
    public double a=1;
    public double frame=0.016;
    public double realFrame=frame;
    public boolean FreezeInPlay=false;
    public boolean ReverseInPlay=false;
    public boolean SizeInPlay=false;
    public boolean Invisibility;
    public boolean WindInProgress=false;

    public static void setter(int spin,double wind,int freeze){
        Game.spin=spin;
        Game.wind=wind;
        Game.freeze=freeze;
    }

    @Override
    public void start(Stage stage) throws Exception {
        gamepane.setMinWidth(480);
        gamepane.setMinHeight(750);
        mediaPlayer=new MediaPlayer(new Media(Game.class.getResource("/media/Caitlin De Ville - TRUSTFALL.mp3").toString()));
        //mediaPlayer.play();
        mediaPlayer.setCycleCount(-1);
        Label windCheck=new Label("wind :"+windSpeed);
        windCheck.setLayoutY(450);
        windCheck.setLayoutX(15);
        windCheck.setFont(Font.font("Ariel", FontWeight.BOLD, 20));
        gamepane.getChildren().add(windCheck);
        progressBar.setStyle("-fx-accent: green;");
        progressBar.setLayoutY(15);
        progressBar.setLayoutX(10);
        Label Timer=new Label("Time :"+(Totaltime-time));
        Timer.setFont(Font.font("Ariel", FontWeight.BOLD, 20));
        Timer.setTextFill(Color.BLACK);

        Timer.setLayoutX(10);
        Timer.setLayoutY(620);
        gamepane.getChildren().add(Timer);
        //scene desiagn
        stage.setMinHeight(700);
        stage.setMinWidth(500);
        mainBall=new MainBall(240,300,95, Color.BLACK);//0
        unknownBorder=new Circle(240,300,150,Color.TRANSPARENT);//1
        gamepane.getChildren().addAll(unknownBorder,mainBall);

        Label count =new Label(""+BallsCount);
        Ball currentBall=balls[BallsCount-1];


        count.setLayoutX(224);
        count.setLayoutY(287);
        count.setTextFill(Color.WHITE);
        count.setFont(Font.font("Ariel", FontWeight.BOLD, 30));
        gamepane.getChildren().add(count);
        for(int i=1;i<BallsCount+6;i++){
            if(i%2==0)
                balls[i-1]=new Ball(240,620,10,Color.BLUE);
            if(i%2==1)
                balls[i-1]=new Ball(240,25,10,Color.DARKVIOLET);

        }
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

        timeCheck=new SimpleDoubleProperty();
        RealTime=new Timeline(new KeyFrame(Duration.millis(16),event ->{
            time+=realFrame;
            timeCheck.set(time);
            Timer.setText("Time : "+String.format("%3.2f",Totaltime-time));
            progressBar.setProgress((double) freezecount*0.25);
            for(Ball bally:balls){
                if(bally.inMove){
                    if(!bally.fisrtRotate){
                        bally.time+=frame;
                    }
                    else {
                        bally.setCenterY(bally.IntersectY);
                        bally.setCenterX(bally.IntersectX);
                        bally.fisrtRotate=false;
                        bally.time=Math.atan((bally.IntersectX-300)/(bally.IntersectY-240));
                    }
                    if(SizeInPlay){
                        bally.SizeOverTime += frame;
                        SizeChange(bally);
                        if(Math.floor(time)%8==0  && !Reverse) {
                            clockwise = 1;
                            Reverse=true;
                            bally.time+=Math.PI;
                        }
                        if(Math.floor(time)%8==4 && Reverse) {
                            clockwise = -1;
                            Reverse=false;
                            bally.time-=Math.PI;
                        }
                    }
                    CircularMovement(bally);

                    if(Invisibility){
                        if(Math.floor(time)%8==6)
                            bally.setOpacity(0);
                        if(Math.floor(time)%8==2)
                            bally.setOpacity(1);
                    }

                }
                if(!bally.inMove)
                    bally.update();
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
            frame=realFrame/2;

        }));
        Freeze.setCycleCount(312);
        Freeze.setOnFinished(actionEvent -> {
            frame=realFrame;
            FreezeInPlay=false;

        });
        Wind=new Timeline(new KeyFrame(Duration.seconds(5),actionEvent ->{
            windSpeed= (int) (Math.random() * (31) -15);
            windCheck.setText("wind :"+windSpeed);
        }));
        Wind.setCycleCount(-1);

        timeCheck.addListener((observable,oldValue,newValue)->{
            if(newValue.doubleValue()>Totaltime || YouLost){
                RealTime.stop();
                Wind.stop();
                mediaPlayer.stop();
                Timer.setText("Time : 0.00");
                gamepane.setBackground(new Background(new BackgroundFill(Color.RED,new CornerRadii(5),new Insets(5))));

            }
        });
        Scene scene=new Scene(gamepane);
        gamepane.getChildren().add(progressBar);
        scene.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.SPACE) && BallsCount>0 && !YouLost){
                if (BallsCount%2==0)
                    LinerMove(balls[BallsCount-1],windSpeed/2,-8);
                else
                    LinerMove(balls[BallsCount-1],windSpeed/2,+8);
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
            else if(keyEvent.getCode().equals(KeyCode.ESCAPE)){
                RealTime.stop();
                Wind.stop();
                Stage current=new Stage();
                try {
                    new PauseMenu().start(current);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            else if(keyEvent.getCode().equals(KeyCode.RIGHT) && WindInProgress){
                for(Ball bell:balls){
                    if(!bell.inMove && bell.getFill().equals(Color.BLUE)){
                        bell.setVelocityX(2);
                    }
                }
            }
            else if(keyEvent.getCode().equals(KeyCode.LEFT) && WindInProgress){
                for(Ball bell:balls){
                    if(!bell.inMove && bell.getFill().equals(Color.BLUE)){
                        bell.setVelocityX(-2);
                    }
                }
            }
            else if(keyEvent.getCode().equals(KeyCode.D) && WindInProgress){
                for(Ball bell:balls){
                    if(!bell.inMove && bell.getFill().equals(Color.DARKVIOLET)){
                        bell.setVelocityX(2);
                    }
                }
            }
            else if(keyEvent.getCode().equals(KeyCode.A) && WindInProgress){
                for(Ball bell:balls){
                    if(!bell.inMove && bell.getFill().equals(Color.DARKVIOLET)){
                        bell.setVelocityX(-2);
                    }
                }
            }
        });
        scene.setOnKeyReleased(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.LEFT) && WindInProgress){
                for(Ball bell:balls){
                    if(!bell.inMove && bell.getFill().equals(Color.BLUE)){
                        bell.setVelocityX(0);
                    }
                }
            }
            if(keyEvent.getCode().equals(KeyCode.RIGHT) && WindInProgress){
                for(Ball bell:balls){
                    if(!bell.inMove && bell.getFill().equals(Color.BLUE)){
                        bell.setVelocityX(0);
                    }
                }
            }
            if(keyEvent.getCode().equals(KeyCode.A) && WindInProgress){
                for(Ball bell:balls){
                    if(!bell.inMove && bell.getFill().equals(Color.DARKVIOLET)){
                        bell.setVelocityX(0);
                    }
                }
            }
            if(keyEvent.getCode().equals(KeyCode.D) && WindInProgress){
                for(Ball bell:balls){
                    if(!bell.inMove  && bell.getFill().equals(Color.DARKVIOLET)){
                        bell.setVelocityX(0);
                    }
                }
            }
        });

        ballsCount.addListener((observable,oldValue,newValue)->{
            if(ballsCount.get()<=(double)BallsCountAtFirst*0.75 && !ReverseInPlay && !SizeInPlay){
                // Reverse.play();
                ReverseInPlay=true;
                SizeInPlay=true;
                System.out.println("Reverse & Size active");
            }
            if(ballsCount.get()<=(double)BallsCountAtFirst*0.5 && !Invisibility){
              //  Invisibility=true;
            }
            if(ballsCount.get()<=(double)BallsCountAtFirst*0.25 && !WindInProgress){
                WindInProgress=true;
                Wind.play();
            }


        });

        stage.setScene(scene);
        stage.show();
    }
    public void CircularMovement(Ball ball){
        int speed=150;

        ball.setAccelerationX(speed * Math.sin( clockwise * ball.time));
        ball.setAccelerationY(speed * Math.cos( clockwise * ball.time));
        ball.setCenterX(240+ball.getAccelerationX());
        ball.setCenterY(300+ball.getAccelerationY());


    }
    public void LinerMove(Ball ball,double x,double y){
        ball.setVelocityY(y);
        ball.setVelocityX(x);
        AtomicInteger z= new AtomicInteger();
        Linermove=new Timeline(new KeyFrame(Duration.millis(16),event ->{

            ball.update();
            // System.out.println(Math.pow(ball.getCenterY()-190,2)+Math.pow(ball.getCenterX()-240,2));
            if(Math.pow(ball.getCenterY()-240,2)+Math.pow(ball.getCenterX()-300,2)<=22500) {
                ball.IntersectX=ball.getCenterX();
                ball.IntersectY=ball.getCenterY();
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
                return;
            }
            if(ball.getCenterX()<0 || ball.getCenterY()<0 || ball.getCenterX()> 480){
                System.out.println("yopu lose");
                YouLost=true;
                gamepane.setBackground(new Background(new BackgroundFill(Color.RED,new CornerRadii(5),new Insets(5))));
                RealTime.stop();
                return;
            }
            Linermove.stop();
            ball.inMove=true;
            ball.fisrtRotate=true;
            freezecount+=0.02;
            RoundingBalls.add(ball);
            //CircularMovement(ball);
            ball.time=Math.atan2(ball.getCenterY()-190,ball.getCenterX()-190);
            if(ball.time==NaN)
                ball.time=0;
            if(ballsCount.get()==0){
                RealTime.stop();
                gamepane.setBackground(new Background(new BackgroundFill(Color.GREEN,new CornerRadii(5),new Insets(5))));
            }
        }
    }
    public void SizeChange(Ball ball){
        ball.setRadius(8+5*Math.abs(Math.sin(ball.SizeOverTime)));
    }

}
