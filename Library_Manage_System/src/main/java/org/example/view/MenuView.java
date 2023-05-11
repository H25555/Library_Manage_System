package org.example.view;

import org.example.model.Role;
import org.example.model.User;
import org.example.service.UserServices;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuView {
    static LoginView loginView = new LoginView();
    static Scanner scanner = new Scanner(System.in);
    static AdminView adminView = new AdminView();
    static CustomerView customerView = new CustomerView();
    private boolean isSignin = false;
    static User user = new User();
    UserServices userServices =new UserServices();
    public void signin(){

        this.isSignin = loginView.signin();
        if (this.isSignin == true){

            if (loginView.checkRole().equals(Role.ADMIN)){
                adminView.select();
//                System.out.println("admin");
            } else {
//                System.out.println("customer");
                userServices.getMessage(loginView.currentUser);
                customerView.select();
            }
        } else {
            System.out.println("Wrong Phonenumber or Password!! Please Enter A Valid Account!");
            }
    }
    public void signup(){
        loginView.signup();
    }
    public void select(){
        do {
            menuMain();
            try{
            System.out.println("\u001B[34m Select Function: \u001B[0m");
            int number = scanner.nextInt();
            switch (number) {
                case 1:
                    signin();
                    break;
                case 2:
                    signup();
                    break;
                case 3:
                    exit();
                    break;
                default:
                    System.out.println("Please Enter Valid Function!!");
                    select();
                    break;
                }
            }catch (InputMismatchException io) {
                System.out.println("Please Enter Valid Function!!");
            }
        }while (true);
    }
    public static void menuMain() {
        System.out.println();
        System.out.println(" \u001B[35m                 LIBRARY APLICATION    \u001B[0m");
        System.out.println();
        System.out.println("\u001B[35m            ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪\u001B[0m");
        System.out.println("\u001B[35m            ⚪    1.    Signin           ⚪\u001B[0m");
        System.out.println("\u001B[35m            ⚪    2.    Signup           ⚪\u001B[0m");
        System.out.println("\u001B[35m            ⚪    3.    Exit             ⚪\u001B[0m");
        System.out.println("\u001B[35m            ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪\u001B[0m");
        System.out.println();
        System.out.println();
    }
    public void exit(){
        System.exit(0);
    }
}
