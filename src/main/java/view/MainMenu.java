package view;

import controller.ApplicationManager;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Data;

import java.net.ResponseCache;
import java.net.URL;
import java.util.concurrent.BlockingDeque;

public class MainMenu extends Application {
    @Override
    public  void start(Stage stage) throws Exception {
        stage.setMinHeight(700);
        stage.setMinWidth(580);
        URL url = LoginMenu.class.getResource("/FXML/mainMenu.fxml");
        assert url != null;
        Pane pane= FXMLLoader.load(url);
        pane.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
        Scene scene = new Scene(pane);
        Label label0=new Label("You Haven't Logged in!");
        if(ApplicationManager.loggedUser != null)
            label0=new Label("        "+ApplicationManager.loggedUser.username+"             Level: "+ApplicationManager.loggedUser.level+"       ");
        Label label1=new Label("Profile Menu");
        Label label2=new Label("Start New Game");
        Label label3=new Label("Load Game");
        Label label4=new Label("Setting");
        Label label5=new Label("PvP");
        Label label6=new Label("\n        Exit");
        Image SettingImage=new Image(MainMenu.class.getResource("/images/pngtree-vector-settings-icon-png-image_854999.jpg").toString());
        ImageView imageView=new ImageView(SettingImage);
        Image ExitImage=new Image(MainMenu.class.getResource("/images/close-icon-white-border.png").toString());
        ImageView imageView2=new ImageView(ExitImage);
        imageView.setFitWidth(120);
        imageView.setFitHeight(120);
        imageView2.setFitWidth(70);
        imageView2.setFitHeight(70);
        imageView2.setLayoutX(60);
        imageView2.setX(60);
        Rectangle rectangle1=new Rectangle(35 , 35 , 235,150);
        Rectangle rectangle2=new Rectangle(35 , 255, 235,150);
        Rectangle rectangle3=new Rectangle(255,1,270,205);
        VBox TopPlayers=new VBox();
        VBox TopScores=new VBox();
        TopPlayers.getChildren().add(0,new Label(" Name:                                       "));
        TopScores.getChildren().add(0,new Label("Level                                     "));
        ApplicationManager.loadUsers();
       ApplicationManager.sortUsers();
        for(int i=0;i< Math.min(Data.getUsers().size(),6);i++){
            TopPlayers.getChildren().add(i+1,new Label(" "+(i+1)+")  "+Data.getUsers().get(i).username));
        }
        for(Node label:TopPlayers.getChildren()){
            if (label instanceof Label){
                ((Label) label).setFont(Font.font("Ariel",FontWeight.BOLD, 20));
                ((Label) label).setTextFill(Color.WHITE);
            }
        }
        for(int i=0;i< Math.min(Data.getUsers().size(),6);i++){
            TopScores.getChildren().add(i+1,new Label("  "+Data.getUsers().get(i).level));
        }
        for(Node label:TopScores.getChildren()){
            if (label instanceof Label){
                ((Label) label).setFont(Font.font("Ariel",FontWeight.BOLD, 20));
                ((Label) label).setTextFill(Color.WHITE);
            }
        }
        HBox Top=new HBox(TopPlayers,TopScores);
        Top.setLayoutX(5);
        Top.setLayoutY(510);
        rectangle1.setArcWidth(20);
        rectangle1.setArcHeight(20);
        rectangle2.setArcWidth(20);
        rectangle2.setArcHeight(20);
        rectangle3.setArcWidth(20);
        rectangle3.setArcHeight(20);
        rectangle1.setFill(Color.BLACK);
        rectangle2.setFill(Color.BLACK);
        rectangle3.setFill(Color.BLACK);
        HBox hBox=new HBox(imageView2,label6);
        hBox.setAlignment(Pos.CENTER);
        Top.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
        Top.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,new CornerRadii(10),BorderStroke.DEFAULT_WIDTHS)));
        StackPane stackPane0=new StackPane(label0);
        StackPane stackPane1=new StackPane(rectangle1,label1);
        StackPane stackPane2=new StackPane(rectangle2,label2);
        StackPane stackPane3=new StackPane(rectangle3,imageView);
        StackPane stackPane4=new StackPane(label3);
        StackPane stackPane5=new StackPane(label5);
        StackPane stackPane6=new StackPane(imageView2);
        stackPane0.setLayoutX(10);
        stackPane0.setLayoutY(10);
        stackPane1.setLayoutX(10);
        stackPane1.setLayoutY(60);
        stackPane2.setLayoutX(10);
        stackPane2.setLayoutY(235);
        stackPane3.setLayoutX(270);
        stackPane3.setLayoutY(60);
        stackPane4.setLayoutX(270);
        stackPane4.setLayoutY(290);
        stackPane5.setLayoutX(270);
        stackPane5.setLayoutY(390);
        stackPane6.setLayoutX(10);
        stackPane6.setLayoutY(405);
        stackPane0.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,null,null)));
        stackPane0.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,new CornerRadii(15),new BorderWidths(3))));
        stackPane2.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,new CornerRadii(10),new BorderWidths(5))));
        stackPane1.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,new CornerRadii(10),new BorderWidths(5))));
        stackPane3.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,new CornerRadii(10),new BorderWidths(5))));
        stackPane4.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
        stackPane4.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,new CornerRadii(10),new BorderWidths(5))));
        stackPane5.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
        stackPane5.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,new CornerRadii(10),new BorderWidths(5))));
        stackPane6.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
        stackPane6.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,new CornerRadii(10),new BorderWidths(5))));
        label0.setFont(Font.font("Ariel",FontWeight.BOLD, 18));
        label1.setFont(Font.font("Ariel",FontWeight.BOLD, 20));
        label2.setFont(Font.font("Ariel",FontWeight.BOLD, 20));
        label3.setFont(Font.font("Ariel",FontWeight.BOLD, 20));
        label4.setFont(Font.font("Ariel",FontWeight.BOLD, 20));
        label5.setFont(Font.font("Ariel",FontWeight.BOLD, 20));
        label6.setFont(Font.font("Ariel",FontWeight.BOLD, 20));
        label0.setTextFill(Color.WHITE);
        label1.setTextFill(Color.WHITE);
        label2.setTextFill(Color.WHITE);
        label3.setTextFill(Color.WHITE);
        label5.setTextFill(Color.WHITE);
        label6.setTextFill(Color.WHITE);
        label1.setAlignment(Pos.BOTTOM_CENTER);
        stackPane0.setAlignment(Pos.TOP_CENTER);
        label1.setLayoutX(50);
        label1.setLayoutY(50);
        stackPane0.setMinWidth(550);
        stackPane0.setMinHeight(35);
        stackPane4.setMinWidth(285);
        stackPane4.setMinHeight(85);
        stackPane5.setMinWidth(285);
        stackPane5.setMinHeight(85);
        stackPane6.setMinWidth(250);
        stackPane6.setMinHeight(95);
        pane.getChildren().add(stackPane0);
        pane.getChildren().add(stackPane1);
        pane.getChildren().add(stackPane2);
        pane.getChildren().add(stackPane3);
        pane.getChildren().add(stackPane4);
        pane.getChildren().add(stackPane5);
        pane.getChildren().add(stackPane6);
        pane.getChildren().add(Top);
        stackPane1.setOnMousePressed(mouseEvent -> {
            if(ApplicationManager.loggedUser==null){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("You haven't logged in yet!\nplease login to access this part.");
                alert.showAndWait();
            }
           else{
                try {
                    new ProfileMenu().start(stage);
                } catch (Exception e) {
                    new RuntimeException();
                }
            }
        });
        stackPane2.setOnMousePressed(mouseEvent -> {

        });
        stackPane3.setOnMousePressed(mouseEvent -> {
            try {
                new SettingMenu().start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        stackPane4.setOnMousePressed(mouseEvent -> {
            if(ApplicationManager.loggedUser==null ){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("you haven't logged in or you don't have any saved game!");
                alert.showAndWait();
            }


        });
        stackPane5.setOnMousePressed(mouseEvent -> {

        });
        stackPane6.setOnMousePressed(mouseEvent -> {
            ApplicationManager.saveUsers();
           // LoginMenu.mediaPlayer.stop();
            System.exit(0);

        });
        stage.setScene(scene);
        stage.show();
    }
}
