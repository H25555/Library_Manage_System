package org.example.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminView {
    static BookView bookView = new BookView();
    static UserView userView = new UserView();
    static MenuView menuView = new MenuView();
    static Scanner scanner = new Scanner(System.in);
    public static void selectAdminView() {
        System.out.println();
        System.out.println("                   Library Manager                   ");
        System.out.println("⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪");
        System.out.println("⚪                                              ⚪");
        System.out.println("⚪                                              ⚪");
        System.out.println("⚪            1.   Account Manager              ⚪");
        System.out.println("⚪            2.   Book Manager                 ⚪");
        System.out.println("⚪            3.   Sign out                     ⚪");
        System.out.println("⚪                                              ⚪");
        System.out.println("⚪                                              ⚪");
        System.out.println("⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪");
    }
    public static void select(){
        do {
            selectAdminView();
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("\nSelect Function: ");
                int number = scanner.nextInt();
                switch (number) {
                    case 1:
                        userView.select();
                        return;
                    case 2:
                        bookView.select();
                        break;
                    case 3:
                        menuView.select();
                        break;
                    default:
                        System.out.println("Invalid Function!");
                        select();
                        break;
                }

            } catch (InputMismatchException io) {
                System.out.println("Invalid Function!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
}