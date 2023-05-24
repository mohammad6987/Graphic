package view;

import controller.ApplicationManager;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;

public class PauseMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Pane pause = new Pane();
        pause.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(5),new Insets(10))));
        Button resume=new Button();
        resume.setFont(Font.font("Ariel", FontWeight.BOLD, 20));
        resume.setOnMouseClicked(mouseEvent -> {
            stage.close();
            if(Game.RealTime != null)
                Game.RealTime.play();
        });
        Button save=new Button();
        save.setText("Save");
        save.setFont(Font.font("Ariel", FontWeight.BOLD, 20));
        save.setTextFill(Color.WHITE);
       save.setOnMouseClicked(mouseEvent -> {
            stage.close();
            ApplicationManager.saveGame();
        });
        stage.setTitle("Pause");
        resume.setBackground(new Background(new BackgroundFill(Color.BLACK,new CornerRadii(10),new Insets(5))));
        save.setBackground(new Background(new BackgroundFill(Color.BLACK,new CornerRadii(10),new Insets(5))));
        resume.setLayoutX(10);
        resume.setLayoutY(50);
        resume.setText("Resume");
        resume.setTextFill(Color.WHITE);
        pause.getChildren().add(resume);
        save.setLayoutX(222);
        save.setLayoutY(50);
        pause.setMinWidth(380);
        pause.setMinHeight(280);
        resume.setLayoutX(50);
        save.setMinWidth(110);
        pause.getChildren().add(save);
        ImageView nextTrack=new ImageView(new Image(PauseMenu.class.getResource("/images/Icons8-Windows-8-Media-Controls-Last.512.png").toString()));
        nextTrack.setFitWidth(50);
        nextTrack.setFitHeight(50);
        pause.getChildren().add(nextTrack);
        nextTrack.setY(140);
        nextTrack.setX(110);
        ImageView stopOrPlay=new ImageView(new Image(PauseMenu.class.getResource("/images/Ionic-Ionicons-Caret-back-circle-sharp.512.png").toString()));
        stopOrPlay.setFitHeight(65);
        stopOrPlay.setFitWidth(65);
        stopOrPlay.setY(135);
        stopOrPlay.setX(200);
        pause.getChildren().add(stopOrPlay);
        stopOrPlay.setOnMouseClicked(mouseEvent -> {
            if (Game.mediaPlayer.isMute()) {
                Game.mediaPlayer.play();
            }
            else {
                Game.mediaPlayer.stop();
            }
        });
        nextTrack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(Game.mediaPlayer.getMedia().getSource().equals(PauseMenu.class.getResource("/media/Caitlin De Ville - TRUSTFALL.mp3").toString())){
                    Game.mediaPlayer = new MediaPlayer(new Media(PauseMenu.class.getResource("/media/Thomas Bergersen - Immortal - musicgeek.ir.mp3").toString()));
                    Game.mediaPlayer.play();
                }
                else if(Game.mediaPlayer.getMedia().getSource().equals(PauseMenu.class.getResource("/media/Thomas Bergersen - Immortal - musicgeek.ir.mp3").toString())){
                    Game.mediaPlayer = new MediaPlayer(new Media(PauseMenu.class.getResource("/media/Thomas Bergersen - Starvation - musicgeek.ir.mp3").toString()));
                    Game.mediaPlayer.play();
                }
                else if(Game.mediaPlayer.getMedia().getSource().equals(PauseMenu.class.getResource("/media/Thomas Bergersen - Starvation - musicgeek.ir.mp3").toString())){
                    Game.mediaPlayer = new MediaPlayer(new Media(PauseMenu.class.getResource("/media/Caitlin De Ville - TRUSTFALL.mp3").toString()));
                    Game.mediaPlayer.play();
                }
            }
        });
        
        
        
        
        
        
        Scene scene=new Scene(pause);
        stage.setScene(scene);
        stage.setOnCloseRequest(windowEvent -> {
            Game.RealTime.play();
            Game.Wind.play();
        });
        stage.show();

    }
}
