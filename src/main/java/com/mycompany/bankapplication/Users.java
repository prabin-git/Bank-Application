package com.mycompany.bankapplication;


public class Users {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    
    public static String getAdminUsername(){
        return USERNAME;
    }
    
    public static String getAdminPassword(){
        return PASSWORD;
    }
    private String _username;
    private String _password;
    private String _name;
    
    public String getName(){
        return _name;
    }
    
    public void setName(String name){
        _name = name;
    }
    
    public String getUsername(){
        return _username;
    }
    
    public void setUsername(String username){
        _username = username;
    }
    
    public String getPassword(){
        return _password;
    }
    
    public void setPassword(String password){
        _password = password;
    }
    
    
}
