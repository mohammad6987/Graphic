package view;

import controller.ApplicationManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SettingMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane=new Pane();
        pane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        stage.setMinHeight(700);
        stage.setMinWidth(580);
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        ToggleGroup toggleGroup = new ToggleGroup();
        Label Difficulty=new Label("Difficulty :");
        RadioButton option1 = new RadioButton("Level 1:\nspin : 5 wind : 1.2 freeze : 7");
        option1.setToggleGroup(toggleGroup);
        RadioButton option2 = new RadioButton("Level 2:\nspin : 10 wind : 1.5 freeze : 5");
        option2.setToggleGroup(toggleGroup);
        RadioButton option3 = new RadioButton("Level 3:\nspin : 15 wind : 1.8 freeze : 3");
        option3.setToggleGroup(toggleGroup);
        option1.setFont(Font.font("Ariel", FontWeight.BOLD, 20));
        option2.setFont(Font.font("Ariel", FontWeight.BOLD, 20));
        option3.setFont(Font.font("Ariel", FontWeight.BOLD, 20));
        if(ApplicationManager.loggedUser != null){
            switch (ApplicationManager.loggedUser.difficulty) {
                case 1 -> option1.setSelected(true);
                case 2 -> option2.setSelected(true);
                case 3 -> option3.setSelected(true);
            }
        }
        else
            option2.setSelected(true);
        Difficulty.setFont(Font.font("Ariel", FontWeight.BOLD, 22));
        option1.setTextFill(Color.WHITE);
        option2.setTextFill(Color.WHITE);
        option3.setTextFill(Color.WHITE);
        Difficulty.setTextFill(Color.WHITE);
        root.getChildren().addAll(Difficulty,option1, option2, option3);
        pane.getChildren().add(root);
        Scene scene = new Scene(pane);
        option1.setOnAction(actionEvent -> Game.setter(5, 1.2, 7));
        option2.setOnAction(actionEvent -> Game.setter(10,1.5,5));
        option3.setOnAction(actionEvent -> Game.setter(15,1.8,3));
        VBox root2=new VBox();
        root2.setPadding(new Insets(10));
        ToggleGroup toggleGroup1=new ToggleGroup();
        Label language=new Label("Language :                               ");
        RadioButton english=new RadioButton("English\n");
        RadioButton persian=new RadioButton("Persian");
        language.setFont(Font.font("Ariel", FontWeight.BOLD, 22));
        language.setTextFill(Color.WHITE);
        persian.setFont(Font.font("Ariel", FontWeight.BOLD, 22));
        persian.setSelected(true);
        persian.setTextFill(Color.WHITE);
        english.setFont(Font.font("Ariel", FontWeight.BOLD, 22));
        english.setTextFill(Color.WHITE);
        persian.setToggleGroup(toggleGroup1);
        english.setToggleGroup(toggleGroup1);
        root2.getChildren().addAll(language,persian,english);
        pane.getChildren().add(root2);
        root2.setSpacing(20);
        root.setLayoutY(20);
        root.setLayoutX(10);
        root2.setLayoutY(290);
        root2.setLayoutX(10);
        ImageView back = new ImageView(new Image(SettingMenu.class.getResource("/images/close-icon-white-border.png").toString()));
        back.setFitWidth(70);
        back.setFitHeight(70);
        back.setX(10);
        back.setY(590);
        pane.getChildren().add(back);
        back.setOnMouseClicked(mouseEvent -> {
            try {
                new MainMenu().start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Button muteButton = new Button("Mute");
        muteButton.setTextFill(Color.WHITE);
        muteButton.setLayoutX(90);
        muteButton.setLayoutY(600);
        muteButton.setFont(Font.font("Ariel", FontWeight.BOLD, 20));
        muteButton.setBackground(new Background(new BackgroundFill(Color.BLACK,new CornerRadii(10),new Insets(5))));
        pane.getChildren().add(muteButton);
        muteButton.setOnAction(event -> {
            if (LoginMenu.mediaPlayer.isMute()) {
                LoginMenu.mediaPlayer.setMute(false);
                muteButton.setText("Mute");
            } else {
                LoginMenu.mediaPlayer.setMute(true);
                muteButton.setText("Unmute");
            }
        });
        option1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(ApplicationManager.loggedUser != null)
                    ApplicationManager.loggedUser.difficulty=1;
            }
        });
        option2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(ApplicationManager.loggedUser != null)
                    ApplicationManager.loggedUser.difficulty=2;
            }
        });
        option3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(ApplicationManager.loggedUser != null)
                    ApplicationManager.loggedUser.difficulty=3;
            }
        });
        root.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,new CornerRadii(10),new BorderWidths(5))));
        root2.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,new CornerRadii(10),new BorderWidths(5))));
        stage.setScene(scene);
        stage.show();
    }
}
