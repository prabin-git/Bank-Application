package com.mycompany.bankapplication;
import java.io.IOException;
import java.util.Scanner;


public class MainView {
    
    static Scanner sc = new Scanner(System.in);
    
    
    public static void main(String[] args) throws IOException{
        
        int lines = 40;
        predefinedObjects();
        
        while(true){
            if(loginView()){
                while (true){
                    System.out.println("Prime Bank\nSelect appropriate options as specified for using the following services\n");
                    System.out.println("Services                    Key");
                    System.out.println("-------------------------------");
                    System.out.println("Logout......................0");
                    System.out.println("Add Account.................1");
                    System.out.println("Check Balance...............2");
                    System.out.println("Deposit Amount..............3");
                    System.out.println("Withdraw Amount.............4");
                    System.out.println("Transfer Amount.............5");
                    System.out.println("List Users Account..........6");
                    System.out.println("Delete User Account.........7");
                    System.out.println("Create User.................8");
                    System.out.println("List User...................9");
                    System.out.println("Delete User.................10\n");

                    int input = sc.nextInt();
                    System.out.println("\n-------------------------------\n");
                    if(input == 0){
                        System.out.println("Logging out...");
                        System.out.println("\n-------------------------------\n");
                        break;
                    }
                    
                    switch(input){

                        case 1:
                            addCustomerAccountView();
                            break;

                        case 2:
                            checkCustomerBalanceView();
                            break;

                        case 3:
                            DepositCustomerAmountView();
                            break;

                        case 4:
                            WithdrawCustomerAmountView();
                            break;

                        case 5:
                            TransferCustomerAmountView();
                            break;

                        case 6:
                            ListCustomerView();
                            break;

                        case 7:
                            DeleteCustomerView();
                            break;

                        case 8:
                            AddUserView();
                            break;

                        case 9:
                            ListUsers();
                            break;
                        
                        case 10:
                            DeleteUser();
                            break;

                        default:
                            System.out.println("Enter appropriate key!");
                            newLines(lines);
                    }
                    System.out.println("Press Enter to continue...");
                    System.in.read();
                    newLines(lines);
                }
            }
            System.out.println("Do you want to continue using the program (Y/N)");
            char next = Character.toUpperCase(sc.next().charAt(0));
            if(next == 'Y'){
                newLines(lines);
            }else{
                System.out.println("Exiting Program...");
                break;
            }
        }
    }
    
    
    public static boolean loginView(){
        for(int i = 0; i < 3; i++){
            System.out.println("Enter your username");
            String username = sc.next();
            System.out.println("Enter your password");
            String password = sc.next();
            if(username.equals(Users.getAdminUsername()) & password.equals(Users.getAdminPassword())){
                MainView.newLines(30);
                return true;
            }
            int outcome = UserHandler.login(username, password);
            if(outcome == 1){
                MainView.newLines(30);
                return true;
            }else{
                System.out.println("\nInvalid Username or Password!\nPlease enter your crediantials again. " + (2-i) + " attempts remaining");
                System.out.println("\n-------------------------------\n");
            }
        }
        System.out.println("\n-------------------------------\n");
        return false;
    }
    
    
    public static void addCustomerAccountView(){
        System.out.println("Enter your Name");
        String name = sc.next();
        System.out.println("Enter the initial balance you want to deposit");
        double balance = sc.nextDouble();

        Accounts user = new Accounts(Accounts.getNumber(), name, balance);
        Accounts.setNumber();
        AccountHandler.setList(user);
        System.out.println("Note* Remember your account number!\nYour account number associated with the newly created account is " + user.getAccountNumber() + ".");
        System.out.println("\n-------------------------------\n");
    }
    
    
    public static void checkCustomerBalanceView() {
        while(true){
            System.out.println("Enter your account number");
            int account_number = sc.nextInt();
            double result = AccountHandler.checkBalance(account_number);
            if(result == -1){
                System.out.println("\n-------------------------------\n");
                System.out.println("Account not found!\nPlease enter your account number again.");
                System.out.println("\n-------------------------------\n");
                continue;
            }
            System.out.println("Your current balance for account number " + account_number + " is " + AccountHandler.checkBalance(account_number) + ".");
            System.out.println("\n-------------------------------\n");
            break;
        }
    }
    
    
    public static void DepositCustomerAmountView(){
        Accounts acc;
        acc = AccountHandler.checkAccount();
        if (acc == null){
            return;
        }
        System.out.println("Enter the amount you want to deposit");
        double amount = sc.nextDouble();
        acc.setBalance(amount);
        System.out.println(amount + " sucessfully deposited!");
        System.out.println("\n-------------------------------\n");
    }
    
    
    public static void WithdrawCustomerAmountView(){
        Accounts acc;
        acc = AccountHandler.checkAccount();
        if (acc == null){
            return;
        }
        double amount;
        while(true){
            System.out.println("Enter the amount you want to withdraw");
            amount = sc.nextDouble();
            if (amount == 0){
                break;
            }
            if(amount > acc.getBalance()){
                System.out.println("\n-------------------------------\n");
                System.out.println("Balance not sufficent!\nCurrent Balance = " + acc.getBalance() + "\nEnter amount again. Enter 0 to exit");
                System.out.println("\n-------------------------------\n");
            }else{
                acc.withdrawBalance(amount);
                System.out.println(amount + " sucessfully withdrawn!\nRemaining Balance = " + acc.getBalance());
                System.out.println("\n-------------------------------\n");
                break;
            }
        }    
    }
    
    
    public static void TransferCustomerAmountView(){
        Accounts acc1;
        acc1 = AccountHandler.checkAccount();
        if (acc1 == null){
            return;
        }
        
        Accounts acc2;
        acc2 = AccountHandler.checkAccount("Enter the account number of receiver");
        if (acc2 == null){
            return;
        }
        
        double amount;
        while(true){
            System.out.println("Enter the amount you want to transfer");
            amount = sc.nextDouble();
            if (amount == 0){
                break;
            }
            if(amount > acc1.getBalance()){
                System.out.println("\n-------------------------------\n");
                System.out.println("Balance not sufficent!\nCurrent Balance = " + acc1.getBalance() + "\nEnter amount again. Enter 0 to exit");
                System.out.println("\n-------------------------------\n");
            }else{
                acc1.withdrawBalance(amount);
                acc2.setBalance(amount);
                System.out.println(amount + " sucessfully transferred to " + acc2.getName() + "\nRemaining Balance: " + acc1.getBalance());
                System.out.println("\n-------------------------------\n");
                break;
            }
        }         
    }
    
    
    public static void ListCustomerView(){
        for(Accounts ls:AccountHandler.getList()){ 
            System.out.println("Account Number = " + ls.getAccountNumber() + "\nName = " + ls.getName() + "\nBalance = " + ls.getBalance());
            System.out.println("-------------------------------");
        }
        System.out.println("\n");
    }
    
    
    public static void DeleteCustomerView(){
        Accounts acc;
        acc = AccountHandler.checkAccount();
        if (acc == null){
            return;
        }
        System.out.println("Enter Administrative Password");
        String password = sc.next();
        if (password.equals(Users.getAdminPassword())){
            AccountHandler.deleteList(acc);
            System.out.println("User Deleted Sucessfully!");
        }else{
            System.out.println("Invalid Password.\nOperation Failed!");
        }
        System.out.println("\n-------------------------------\n");
    }
    
    
    public static void AddUserView(){
        boolean userPresent;
        String username;
        String password;
        String cpassword;
        String name;
        
        while(true){
            System.out.println("Enter username");
            username = sc.next();
            userPresent = UserHandler.checkUser(username);
            if(userPresent){
                System.out.println("User account with that name already exists!\n");
            }
            else{
                while(true){
                    System.out.println("Enter passowrd");
                    password = sc.next();
                    System.out.println("Enter password again");
                    cpassword = sc.next();
                    if(password.equals(cpassword)){
                        System.out.println("Enter your name");
                        name = sc.next();
                        UserHandler.addUser(username, password, name);
                        System.out.println("User sucessfuly added!");
                        break;
                    }
                }
                System.out.println("\n-------------------------------");
                break;
            }
        }
    }
    
    
    public static void ListUsers(){
        System.out.println("Usernames......................");
        for(Users ls:UserHandler.getList()){
            System.out.println(ls.getUsername());
        }
        System.out.println("\n-------------------------------");
    }
    
    
    public static void DeleteUser(){
        boolean user;
        String username;
        System.out.println("Enter Username");
        username = sc.next();
        user = UserHandler.checkUser(username);
        if (user){
            System.out.println("Enter Administrative Password");
            String password = sc.next();
            if (password.equals(Users.getAdminPassword())){
                UserHandler.deleteList(username);
                System.out.println("User Deleted Sucessfully!");
            }else{
                System.out.println("Invalid Password.\nOperation Failed!");
            }
        }else{
            System.out.println("Username not fouund!");
            return;
        }
        System.out.println("\n-------------------------------\n");
    }

    
    public static void newLines(int num){
        for (int i = 0; i < num; ++i){
            System.out.println(" ");
        }
    }
    
    
    public static void predefinedObjects(){
        //Creating predefined Account Objects
        Accounts acc1 = new Accounts(2000, "Prabin Shrestha", 2000);
        AccountHandler.setList(acc1);
        Accounts acc2 = new Accounts(3000, "Sajish Shrestha", 3000);
        AccountHandler.setList(acc2);
        //Creating predefined UserAccount
        UserHandler.addUser("prabin", "prabin", "Prabin Shrestha");
        UserHandler.addUser("sajish", "sajish", "Sajish Shrestha");
    }
}
