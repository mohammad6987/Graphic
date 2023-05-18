package view;

import controller.ApplicationManager;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import model.Data;
import model.User;

import static view.LoginMenu.stage;

public class LoginMenuController {
    public PasswordField password;
    @FXML
    public TextField username;
    public static String currentusername;
    public void submit(MouseEvent mouseEvent) throws Exception {
        User current= Data.getUserByName(username.getText());
        if(username.getText().length()==0 || password.getText().length()==0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setY(280);
            alert.setX(585);
            alert.setHeaderText("Error");
            alert.setContentText("all fields must be filled");
            alert.showAndWait();
            reset();
        }
        else if(current==null){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setY(280);
            alert.setX(585);
            alert.setHeaderText("Error");
            alert.setContentText("username doesn't exist");
            alert.showAndWait();
            reset();
        }
        else if(password.getText().length()<4){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setY(280);
            alert.setX(585);
            alert.setHeaderText("Error");
            alert.setContentText("password is too short");
            alert.showAndWait();
            reset();
        }
        else if(!current.isPasswordTrue(password.getText())){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setY(280);
            alert.setX(585);
            alert.setHeaderText("Error");
            alert.setContentText("password doesn't match");
            alert.showAndWait();
            reset();
        }
        else{
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setY(280);
            alert.setX(585);
            alert.setHeaderText("Welcome");
            alert.setContentText("you have logged in as : " +username.getText());
            ApplicationManager.loggedUser=Data.getUserByName(username.getText());
            alert.showAndWait();
            new MainMenu().start(stage);
        }
    }

    public void reset() {
        username.setText("");
        password.setText("");
    }

    public void Play(MouseEvent mouseEvent) {
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setContentText("size"+Data.getUsers().size());
        alert.show();
    }
    @FXML
    public void initialize() {
        username.textProperty().addListener((observable, oldText, newText)->{
            currentusername=newText;
        });
    }
    public void register(MouseEvent mouseEvent) throws Exception {
        if(username.getText().length()==0 || password.getText().length()==0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setY(280);
            alert.setX(585);
            alert.setHeaderText("Error");
            alert.setContentText("all fields must be filled");
            alert.showAndWait();
            reset();
        }
        else if(Data.getUserByName(username.getText()) != null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Username Taken");
            alert.setY(280);
            alert.setX(585);
            alert.setHeaderText("The username " + username.getText() + " is already taken.");
            alert.setContentText("Please choose a different username.");

            alert.show();
        }
        else if(password.getText().length()<4){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setY(280);
            alert.setX(585);
            alert.setContentText("password is too short");
            alert.showAndWait();
            reset();
        }
        else{
            ApplicationManager.loggedUser=new User(username.getText(),password.getText());
            Data.getUsers().add(0,ApplicationManager.loggedUser);
            ApplicationManager.sortUsers();
            ApplicationManager.saveUsers();
            for(User user : Data.getUsers())
                System.out.println(user.username);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setY(280);
            alert.setX(585);
            alert.setTitle("Register Succesful!");
            alert.setHeaderText("new user with username: "+username.getText()+" created.");
            alert.show();
            new MainMenu().start(stage);

        }
    }
}
