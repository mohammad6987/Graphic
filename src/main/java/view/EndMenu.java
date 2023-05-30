package view;

import controller.ApplicationManager;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Data;

public class EndMenu extends Application {
    public static boolean condition;
    public static Stage gamestage;
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane=new Pane();
        Label result=new Label("You Lost");
        pane.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
        if(condition)
            result.setText("You Win");
        VBox TopPlayers=new VBox();
        VBox TopScores=new VBox();
        TopPlayers.getChildren().add(0,new Label(" Name:                                       "));
        TopScores.getChildren().add(0,new Label("Points                                     "));
        ApplicationManager.loadUsers();
        ApplicationManager.sortUsers();
        for(int i = 0; i< Math.min(Data.getUsers().size(),10); i++){
            TopPlayers.getChildren().add(i+1,new Label(" "+(i+1)+")  "+Data.getUsers().get(i).username));
        }
        for(Node label:TopPlayers.getChildren()){
            if (label instanceof Label){
                ((Label) label).setFont(Font.font("Ariel", FontWeight.BOLD, 20));
                ((Label) label).setTextFill(Color.WHITE);
            }
        }
        for(int i=0;i< Math.min(Data.getUsers().size(),10);i++){
            TopScores.getChildren().add(i+1,new Label("  "+Data.getUsers().get(i).level));
        }
        for(Node label:TopScores.getChildren()){
            if (label instanceof Label){
                ((Label) label).setFont(Font.font("Ariel",FontWeight.BOLD, 20));
                ((Label) label).setTextFill(Color.WHITE);
            }
        }
        HBox Top=new HBox(TopPlayers,TopScores);
        Top.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,new CornerRadii(15),new BorderWidths(3))));
        Top.setLayoutX(5);
        Top.setLayoutY(200);
        result.setFont(Font.font("Ariel", FontWeight.BOLD, 22));
        result.setTextFill(Color.WHITE);
        result.setLayoutX(230);
        pane.getChildren().add(result);
        pane.getChildren().add(Top);
        Scene scene=new Scene(pane);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(windowEvent -> {
            if(gamestage != null){
                gamestage=new Stage();
                try {
                    new MainMenu().start(gamestage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
