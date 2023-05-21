package model;

import view.Game;

public class User {
    public String username;
    private String password;
    public int level;
    public String AvatarUrl;
   // public Game SavedGame;
    public int difficulty;
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.level=1;
        this.AvatarUrl=User.class.getResource("/images/icons/Sensibleworld-Starwars-Stormtrooper.512.png").toString();
       // this.SavedGame=null;
        this.difficulty=2;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getLevel() {
        return level;
    }

    public boolean isPasswordTrue(String pass){
        return pass.equals(this.password);
    }

}


