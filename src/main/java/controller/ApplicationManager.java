package controller;

import javafx.scene.control.Alert;
import model.Data;
import model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import view.Game;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class ApplicationManager {
    public static String Address1= "C:\\Users\\Lenovo\\Downloads\\Telegram Desktop\\workshop\\src\\main\\resources\\Users.json";
    public static User loggedUser;
    public static ArrayList<Game> games=new ArrayList<>();

    public static void saveUsers() {
        if(Data.getUsers() != null) {
            Gson gson = new Gson();
            String json;
            json = gson.toJson(Data.getUsers());
          try (FileWriter file = new FileWriter(Address1)) {
                file.write(json);
            } catch (Exception e) {

            }
        }
    }
    public static void loadUsers() throws FileNotFoundException {
        ArrayList<User> users;
        FileReader file = new FileReader(Address1);
            Type gameListType = new TypeToken<ArrayList<User>>(){}.getType();
            Gson gson = new Gson();
            users = gson.fromJson(file, gameListType);
            if (users!=null)
                Data.setUsers(users);
            else
                Data.setUsers(new ArrayList<>());

    }

    public static void sortUsers(){
        ArrayList<User> x=new ArrayList<>();
        ArrayList<User> current=Data.getUsers();
        if(current.size()<2)
            return;
        x.add(current.get(0));
        if(current.get(0).level>current.get(1).level)
          x.add(1,current.get(1));
        else
          x.add(0,current.get(1));
        User unknown;
       /* for(int i=2;i<current.size();i++){
            unknown=current.get(i);
            int index=0;
            while(index<current.size()){
                if(unknown.level<x.get(index).level)
                    index++;
                else
                    break ;
            }
            x.add(index,unknown);
        }*/
        Data.setUsers(x);
    }
    public static void deleteUser(User user){
        for(int i=0;i<Data.getUsers().size();i++){
            if(user.equals(Data.getUsers().get(i)))
                Data.getUsers().remove(i);
            return;
        }
    }

    public static void saveGame() {
        for(Game x:games){

        }
    }
}
