package com.mycompany.bankapplication;

import static com.mycompany.bankapplication.MainView.sc;
import java.util.ArrayList;

public class AccountHandler {
    private static final ArrayList<Accounts> _user_list = new ArrayList<Accounts>();
    
    public static ArrayList<Accounts> getList(){
        return _user_list;
    }
    
    public static void deleteList(Accounts user){
        _user_list.remove(user);
    }
    
    public static void setList(Accounts user){
        _user_list.add(user);
    }
    
    public static Accounts findAccount(int account_number){
        for(Accounts a:getList()){
            if (a.getAccountNumber() == account_number){
                return a;
            }
        }
        return null;
    }
    
    public static Accounts checkAccount(){
        Accounts acc;
        while(true){
            System.out.println("Enter your account number");
            int account_number = sc.nextInt();
            acc = AccountHandler.findAccount(account_number);
            if(acc == null){
                System.out.println("\n-------------------------------\n");
                System.out.println("Account not found!\nPlease enter the account number again.");
                continue;
            }
            break;
        }
        return acc;
    }
    
    public static Accounts checkAccount(String msg){
        Accounts acc;
        while(true){
            System.out.println(msg);
            int account_number = sc.nextInt();
            if (account_number == 0){
                return null;
            }
            acc = AccountHandler.findAccount(account_number);
            if(acc == null){
                System.out.println("\n-------------------------------\n");
                System.out.println("Account not found!\nPlease enter the account number again. Enter 0 to exit");
                continue;
            }
            break;
        }
        return acc;
    }
    
    public static double checkBalance(int account_number){
        if(findAccount(account_number) != null){
            return findAccount(account_number).getBalance();
        }
        return -1;
    }
}
