package view;

import controller.ApplicationManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.File;

import java.net.URL;

public class LoginMenu extends Application {
    public static Stage stage;
    public static MediaPlayer mediaPlayer;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.getIcons().add(new Image(LoginMenu.class.getResource("/images/icons/Sensibleworld-Starwars-Stormtrooper.512.png").toString()));
        ApplicationManager applicationManager=new ApplicationManager();
        ApplicationManager.loadUsers();
        ApplicationManager.sortUsers();
      Media media1=new Media(LoginMenu.class.getResource("/media/01. The Strength to Make a Stand.mp3").toString());
      mediaPlayer=new MediaPlayer(media1);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
        LoginMenu.stage = stage;
        URL url = LoginMenu.class.getResource("/FXML/loginMenu.fxml");
        BorderPane borderPane = FXMLLoader.load(url);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }
}