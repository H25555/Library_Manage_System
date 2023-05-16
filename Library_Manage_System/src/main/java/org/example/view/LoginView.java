package org.example.view;

import org.example.model.Role;
import org.example.model.User;
import org.example.model.UserStatus;
import org.example.service.AuthService;
import org.example.service.UserServices;
import org.example.utils.ValidateUtils;

import java.util.Scanner;

public class LoginView {
    private AuthService authService = new AuthService();
    private UserServices userServices = new UserServices();
    public static User currentUser = new User();
    MenuView menuView = new MenuView();
    public boolean signin(){
            String phonenumber, password;
            Scanner scanner = new Scanner(System.in);
            do {
                System.out.println("Enter Phonenumber:");
                phonenumber = scanner.nextLine();
            }  while (!ValidateUtils.isPhoneValid(phonenumber));
            do {
                System.out.println("Enter Password:");
                password = scanner.nextLine();

            } while (password == "");
        boolean checkLogin = authService.signin(phonenumber, password);
        User user = userServices.getUserDetailByPhoneNumber(phonenumber);

        userServices.handleExpiredAccount(user);
        if (user.getUserStatus().equals(UserStatus.DENY)){
//            checkLogin = false;
            System.out.println("Please Return Your Borrowed Book To Library !!!");
            menuView.select();
        }
        currentUser = user;
        return checkLogin;
    }
    public void signup(){
        User user = new User();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Your Fullname");
        String name = scanner.nextLine();
        while (!ValidateUtils.isNameValid(name)){
            System.out.println("Please enter valid name!");
            System.out.println("Enter Your Fullname:");
            name = scanner.nextLine();
        }
        System.out.println("Enter Your Address");
        String add = scanner.nextLine();
        System.out.println("Enter Phone Number:");
        String phonenumber = scanner.nextLine();
        while (!ValidateUtils.isPhoneValid(phonenumber)){
            System.out.println("Please enter valid phone number!");
            System.out.println("Enter Phone number:");
            phonenumber = scanner.nextLine();
        }
        System.out.println("Enter Password:");
        String password = scanner.nextLine();
        user.setName(name);
        user.setAddress(add);
        user.setPhonenumber(phonenumber);
        user.setPassword(password);
        authService.signup(user);
    }
    public Role checkRole(){
        return currentUser.getRole();
    }
}
