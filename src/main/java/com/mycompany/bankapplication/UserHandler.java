package com.mycompany.bankapplication;
import java.util.ArrayList;


public class UserHandler {
    private static final ArrayList<Users> _users = new ArrayList<Users>();
    
    public static ArrayList<Users> getList(){
        return _users;
    }
    
    public static int login(String username, String password){
        for(Users user:_users){
            if (user.getUsername().equals(username) & user.getPassword().equals(password)){
                return 1;
            }  
        }
        return 0;
    }
    
    public static void addUser(String username, String password, String name){
        Users user= new Users();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        _users.add(user);
    }
    
    public static boolean checkUser(String username){
        for(Users user:_users){
            if(user.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }
    
    public static void deleteList(String username){
        for(Users user:_users){
            if(user.getUsername().equals(username)){
                _users.remove(user);
            }
        }
    }
    
}
