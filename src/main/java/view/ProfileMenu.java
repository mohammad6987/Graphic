package view;

import controller.ApplicationManager;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Data;
import model.User;

import java.io.File;

public class ProfileMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setMinHeight(750);
        stage.setMinWidth(580);
        Label SetPicLab = new Label("select from local files:");
        ImageView folder = new ImageView(new Image(ProfileMenu.class.getResource("/images/icons8-folder-50.png").toString()));
        ImageView imageView1 = new ImageView(new Image(ProfileMenu.class.getResource("/images/icons/scifi_starwars_rd_icon_158235.png").toString()));
        ImageView imageView2 = new ImageView(new Image(ProfileMenu.class.getResource("/images/icons/scifi_starwars_rebel_alliance_icon_158234.png").toString()));
        ImageView imageView3 = new ImageView(new Image(ProfileMenu.class.getResource("/images/icons/Sensibleworld-Starwars-Boba-Fett.512.png").toString()));
        ImageView imageView4 = new ImageView(new Image(ProfileMenu.class.getResource("/images/icons/Sensibleworld-Starwars-C3PO.512.png").toString()));
        ImageView imageView5 = new ImageView(new Image(ProfileMenu.class.getResource("/images/icons/Sensibleworld-Starwars-Chewbacca.512.png").toString()));
        ImageView imageView6 = new ImageView(new Image(ProfileMenu.class.getResource("/images/icons/Sensibleworld-Starwars-Darth-Vader.512.png").toString()));
        ImageView imageView7 = new ImageView(new Image(ProfileMenu.class.getResource("/images/icons/Sensibleworld-Starwars-Death-Star.512.png").toString()));
        ImageView imageView8 = new ImageView(new Image(ProfileMenu.class.getResource("/images/icons/Sensibleworld-Starwars-Stormtrooper.512.png").toString()));
        imageView1.setFitWidth(45);
        imageView1.setFitHeight(45);
        imageView2.setFitWidth(45);
        imageView2.setFitHeight(45);
        imageView3.setFitWidth(45);
        imageView3.setFitHeight(45);
        imageView4.setFitWidth(45);
        imageView4.setFitHeight(45);
        imageView5.setFitWidth(45);
        imageView5.setFitHeight(45);
        imageView6.setFitWidth(45);
        imageView6.setFitHeight(45);
        imageView7.setFitWidth(45);
        imageView7.setFitHeight(45);
        imageView8.setFitWidth(45);
        imageView8.setFitHeight(45);
        imageView1.setX(350);
        imageView1.setY(50);
        imageView2.setX(400);
        imageView2.setY(50);
        imageView3.setX(450);
        imageView3.setY(50);
        imageView4.setX(500);
        imageView4.setY(50);
        imageView5.setX(350);
        imageView5.setY(110);
        imageView6.setX(400);
        imageView6.setY(110);
        imageView7.setX(450);
        imageView7.setY(110);
        imageView8.setX(500);
        imageView8.setY(110);
        SetPicLab.setFont(Font.font("Ariel", FontWeight.BOLD, 20));
        SetPicLab.setTextFill(Color.BLACK);
        folder.setFitHeight(30);
        folder.setFitWidth(30);
        folder.setLayoutX(260);
        folder.setLayoutY(215);
        SetPicLab.setLayoutY(215);
        SetPicLab.setLayoutX(55);
        Pane pane = new Pane();
        Scene scene = new Scene(pane);
        Circle circle1 = new Circle(5, 60, 90, Color.WHITE);
        Circle circle2 = new Circle(5, 60, 95, Color.BLACK);
        pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(15), new BorderWidths(8
        ))));
        pane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

        ImageView currentAvatar = new ImageView(new Image(ApplicationManager.loggedUser.AvatarUrl));
        StackPane stackPane = new StackPane(circle2, circle1, currentAvatar);
        stackPane.setLayoutX(60);
        stackPane.setLayoutY(25);
        Line line1 = new Line(0, 255, 570, 255);
        line1.setStrokeWidth(8);
        currentAvatar.setFitHeight(160);
        currentAvatar.setFitWidth(160);
        pane.getChildren().add(stackPane);
        pane.getChildren().add(folder);
        pane.getChildren().add(SetPicLab);
        pane.getChildren().add(line1);
        pane.getChildren().addAll(imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8);
        Label changeUsername = new Label("Change Username :");
        changeUsername.setFont(Font.font("Ariel", FontWeight.BOLD, 20));
        changeUsername.setTextFill(Color.BLACK);
        TextField changeName = new TextField("username");
        Button checkName = new Button("Check");
        changeUsername.setLayoutX(15);
        changeUsername.setLayoutY(265);
        pane.getChildren().add(changeUsername);
        changeName.setLayoutX(203);
        changeName.setLayoutY(267);
        changeName.setMinWidth(300);
        pane.getChildren().add(changeName);
        checkName.setLayoutX(508);
        checkName.setLayoutY(267);
        pane.getChildren().add(checkName);

        Label changePassword = new Label("Change Password :");
        changePassword.setFont(Font.font("Ariel", FontWeight.BOLD, 20));
        changePassword.setTextFill(Color.BLACK);
        TextField changepassword = new TextField("password");
        Button checkPass = new Button("Check");
        changePassword.setLayoutX(15);
        changePassword.setLayoutY(345);
        pane.getChildren().add(changePassword);
        changepassword.setLayoutX(203);
        changepassword.setLayoutY(347);
        changepassword.setMinWidth(300);
        pane.getChildren().add(changepassword);
        checkPass.setLayoutX(508);
        checkPass.setLayoutY(347);
        pane.getChildren().add(checkPass);

        Label currentUsername = new Label("Current Username : " + (ApplicationManager.loggedUser == null ? "null" : ApplicationManager.loggedUser.username));
        currentUsername.setFont(Font.font("Ariel", FontWeight.BOLD, 20));
        currentUsername.setTextFill(Color.BLACK);
        currentUsername.setLayoutX(180);
        currentUsername.setLayoutY(300);
        pane.getChildren().add(currentUsername);

        Label currentPassword = new Label("Current Password : " + (ApplicationManager.loggedUser == null ? "null" : ApplicationManager.loggedUser.getPassword()));
        currentPassword.setFont(Font.font("Ariel", FontWeight.BOLD, 20));
        currentPassword.setTextFill(Color.BLACK);
        currentPassword.setLayoutX(180);
        currentPassword.setLayoutY(380);
        pane.getChildren().add(currentPassword);

        Label deletAccount = new Label("Delete Account");
        deletAccount.setFont(Font.font("Ariel", FontWeight.BOLD, 20));
        deletAccount.setTextFill(Color.BLACK);
        deletAccount.setLayoutY(600);
        deletAccount.setLayoutX(20);
        pane.getChildren().add(deletAccount);

        Label logoutAccount = new Label("Logout Account");
        logoutAccount.setFont(Font.font("Ariel", FontWeight.BOLD, 20));
        logoutAccount.setTextFill(Color.BLACK);
        logoutAccount.setLayoutY(600);
        logoutAccount.setLayoutX(300);
        pane.getChildren().add(logoutAccount);

        ImageView back = new ImageView(new Image(ProfileMenu.class.getResource("/images/Ionic-Ionicons-Caret-back-circle-sharp.512.png").toString()));
        back.setFitWidth(70);
        back.setFitHeight(70);
        back.setX(10);
        back.setY(630);
        pane.getChildren().add(back);
        folder.setOnMouseClicked(mouseEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Avatar Image");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
            );
            File selectedFile = fileChooser.showOpenDialog(stage); // where 'stage' is the primary stage of your application

            if (selectedFile != null) {
                // Load the selected image file into an Image object
                Image image = new Image(selectedFile.toURI().toString());

                // Create an ImageView to display the selected image
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(30);
                imageView.setFitHeight(30);
                currentAvatar.setImage(imageView.getImage());
                ApplicationManager.loggedUser.AvatarUrl=imageView.getImage().getUrl();
                // Add the ImageView to your profile menu
                // For example, if you have a VBox named 'profileBox', you could do:
                //ApplicationManager.loggedUser.setAvatar(imageView);
            }
        });
        back.setOnMouseClicked(mouseEvent -> {
            if (ApplicationManager.loggedUser != null)
                ApplicationManager.loggedUser.AvatarUrl = currentAvatar.getImage().getUrl().toString();
            try {
                new MainMenu().start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });
        imageView1.setOnMouseClicked(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                currentAvatar.setImage(imageView1.getImage());
                ApplicationManager.loggedUser.AvatarUrl=imageView1.getImage().getUrl();
            }
        });
        imageView2.setOnMouseClicked(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                currentAvatar.setImage(imageView2.getImage());
                ApplicationManager.loggedUser.AvatarUrl=imageView2.getImage().getUrl();
            }
        });
        imageView3.setOnMouseClicked(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                currentAvatar.setImage(imageView3.getImage());
                ApplicationManager.loggedUser.AvatarUrl=imageView3.getImage().getUrl();
            }
        });
        imageView4.setOnMouseClicked(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                currentAvatar.setImage(imageView4.getImage());
                ApplicationManager.loggedUser.AvatarUrl=imageView4.getImage().getUrl();
            }
        });
        imageView5.setOnMouseClicked(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                currentAvatar.setImage(imageView5.getImage());
                ApplicationManager.loggedUser.AvatarUrl=imageView5.getImage().getUrl();
            }
        });
        imageView6.setOnMouseClicked(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                currentAvatar.setImage(imageView6.getImage());
                ApplicationManager.loggedUser.AvatarUrl=imageView6.getImage().getUrl();
            }
        });
        imageView7.setOnMouseClicked(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                currentAvatar.setImage(imageView7.getImage());
                ApplicationManager.loggedUser.AvatarUrl=imageView7.getImage().getUrl();
            }
        });
        imageView8.setOnMouseClicked(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                currentAvatar.setImage(imageView8.getImage());
                ApplicationManager.loggedUser.AvatarUrl=imageView8.getImage().getUrl();
            }
        });
        checkName.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (Data.getUserByName(changeName.getText()) == null && ApplicationManager.loggedUser != null) {
                    ApplicationManager.loggedUser.setUsername(changeName.getText());
                    ApplicationManager.saveUsers();
                    currentUsername.setText("Current Username : " + ApplicationManager.loggedUser.username);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("You Haven't logged in yet or your enterd username is already taken!");
                    changeName.setText("random bullshit");
                    alert.showAndWait();
                }
            }
        });
        checkPass.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (Data.getUserByName(changepassword.getText()) == null && ApplicationManager.loggedUser != null && changepassword.getText().length() > 3) {
                    ApplicationManager.loggedUser.setPassword(changepassword.getText());
                    ApplicationManager.saveUsers();
                    currentPassword.setText("Current Password : " + ApplicationManager.loggedUser.getPassword());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("You Haven't logged in yet or your password is too short!");
                    changepassword.setText("random bullshit");
                    alert.showAndWait();
                }
            }
        });
        deletAccount.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (ApplicationManager.loggedUser != null) {
                    ApplicationManager.deleteUser(ApplicationManager.loggedUser);
                    ApplicationManager.loggedUser = null;
                    ApplicationManager.saveUsers();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("this account deleted!");
                    alert.showAndWait();
                    try {
                        new LoginMenu().start(stage);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        logoutAccount.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ApplicationManager.saveUsers();
                for (User user : Data.getUsers())
                    System.out.println(user.username);
                ApplicationManager.loggedUser = null;
                try {
                    new LoginMenu().start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        stage.setScene(scene);
        stage.show();
    }
}
