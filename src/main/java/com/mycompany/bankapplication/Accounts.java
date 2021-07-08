package com.mycompany.bankapplication;


public class Accounts {
    
    //private methods
    private static long _number = 1000;
    private long _account_number;
    private String _name;
//    private String account_country;
//    private String account_phone;
//    private String account_gmail;
    private double _balance;
    
    //constructors
    public Accounts(long account_number, String name, double balance){
        _account_number = account_number;
        _name = name;
        _balance = balance;
    }
    
    //get methods
    public long getAccountNumber(){
        return _account_number;
    }
    
    public String getName(){
        return _name;
    }
    
    public double getBalance(){
        return _balance;
    }
    
    //set methods
    public void setAccountNumber(long account_number){
        _account_number = account_number;
    }
        
    public void setName(String name){
        _name = name;
    }
    
    public void setBalance(double balance){
        _balance += balance;
    }
    
    public void withdrawBalance(double balance){
        _balance -= balance;
    }
    
    public static long getNumber(){
        return _number;
    }
    
    public static void setNumber(){
        _number++;
    }
}
