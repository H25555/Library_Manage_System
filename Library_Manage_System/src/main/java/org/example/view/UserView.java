package org.example.view;

import org.example.model.Borrowbook;
import org.example.model.User;
import org.example.service.BorrowBookService;
import org.example.service.UserServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserView {
    List<Integer> idArr = new ArrayList<>();

    UserServices userServices = new UserServices();

    Scanner scanner =new Scanner(System.in);
    BorrowBookService borrowBookService = new BorrowBookService();
    public static void renderView() {
        System.out.println();
        System.out.println("⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ");
        System.out.println("⚪                                                        ⚪");
        System.out.println("⚪             1.    Show User Account                    ⚪");
        System.out.println("⚪             2.    Show Borrowed Book List              ⚪");
        System.out.println("⚪             3.    Confirm Return Borrowed Book         ⚪");
        System.out.println("⚪             4.    Exit                                 ⚪");
        System.out.println("⚪                                                        ⚪");
        System.out.println("⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ");
        System.out.println();
    }
    public void showUserAccount(){
        List<User> userList = userServices.getListUser();
        System.out.println("╔═════╦════════════════════════════╦════════════════════════════╦════════════════════╦══════════╦══════════╗");
        System.out.printf("║%5s║%28s║%28s║%20s║%10s║%10s║","ID","Name","Address","PhoneNumber","Role","Status");
        System.out.println();
        System.out.println("╚═════╩════════════════════════════╩════════════════════════════╩════════════════════╩══════════╩══════════╝");
        for(User user : userList){
            System.out.printf("║%5s║%28s║%28s║%20s║%10s║%10s║",user.getId(),user.getName(),user.getAddress(),user.getPhonenumber(),user.getRole(),user.getUserStatus());
            System.out.println();
        }
        System.out.println("╚═════╩════════════════════════════╩════════════════════════════╩════════════════════╩══════════╩══════════╝");
    }
    public void confirmReturnBook(){
        showBorrowedBookList();
        try {
            System.out.println("Enter Borrowed Book ID:");
            int id = scanner.nextInt();
            borrowBookService.confirmReturnBook(id);
            System.out.println("Successful !");
        } catch (NumberFormatException e){
            System.out.println("please Enter a Number!");
            confirmReturnBook();
        }
    }
    public void showBorrowedBookList(){
        List<Borrowbook> borrowbookList = borrowBookService.getBorrowBook();
        System.out.println("╔═════╦════════╦════════╦════════════════════╦════════════════════╗");
        System.out.printf("║%5s║%8s║%8s║%20s║%20s║","ID","User ID","Book ID","Borrow Date","Exp Date");
        System.out.println();
        System.out.println("╚═════╩════════╩════════╩════════════════════╩════════════════════╝");
        for (Borrowbook borrowbook : borrowbookList){
            System.out.printf("║%5s║%8s║%8s║%20s║%20s║",borrowbook.getId(),borrowbook.getUserid(),borrowbook.getBookid(),borrowbook.getBorrowdate(),borrowbook.getExpDate());
            System.out.println();
            idArr.add(borrowbook.getId());
        }
        System.out.println("╚═════╩════════╩════════╩════════════════════╩════════════════════╝");
    }
    public void select(){
        do {
            renderView();
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("\nSelect Function");
                int number = scanner.nextInt();
                switch (number) {
                    case 1:
                        showUserAccount();
                        break;
                    case 2:
                        showBorrowedBookList();
                        break;
                    case 3:
                        confirmReturnBook();
                        break;
                    case 4:
                        AdminView.select();
                    default:
                        System.out.println("Invalid Function!");
                        select();
                        break;
                }

            } catch (NumberFormatException io) {
                System.out.println("Invalid Function!");
            }
        } while (true);
    }
}
