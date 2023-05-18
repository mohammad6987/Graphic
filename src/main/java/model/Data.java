package model;

import java.util.ArrayList;
import java.util.Collections;

public class Data {
    private static ArrayList<User> users=new ArrayList<>();
    public static ArrayList<User> getUsers() {
        return users;
    }
    public static void setUsers(ArrayList<User> users) {
        Data.users = users;
    }
    public static User getUserByName(String name){
        for(User x:users){
            if(x.username.equals(name))
                return x;
        }
        return null;
    }
    public static int getUserIndex(User user){
        if(users.size()==0)
            return -1;
        for(int i=0;i<users.size();i++ ){
            if(users.get(i).equals(user))
                return i;
        }
        return -1;
    }

}
