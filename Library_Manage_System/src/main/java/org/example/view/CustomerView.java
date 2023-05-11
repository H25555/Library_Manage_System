package org.example.view;

import org.example.model.Book;
import org.example.model.Borrowbook;
import org.example.model.User;
import org.example.service.BookService;
import org.example.service.BorrowBookService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CustomerView {
    MenuView menuView = new MenuView();
    LoginView loginView = new LoginView();
    BookService bookService = new BookService();
    Scanner scanner = new Scanner(System.in);
    BookView bookView = new BookView();
    BorrowBookService borrowBookService = new BorrowBookService();
    private List<Long> idArr = new ArrayList<>();

    public void borrow(){
        bookView.showBook();
        int bookid;
        do {
            System.out.println("Enter Book ID To Borrow:");
            bookid = scanner.nextInt();
        } while (bookView.checkExistId(bookid) == false);
        borrowBookService.borrowBook(bookid,loginView.currentUser);
        System.out.println("Successful !! Your Borrow Book List Have Created !! ");
        System.out.println("Borrow Date: " + LocalDate.now());
        System.out.println("Please Return Before " + LocalDate.now().plusDays(7));
    }
    public void renderView(){
        System.out.println();
        System.out.println("\u001B[35m                    Customer Display    \u001B[0m");
        System.out.println();
        System.out.println("\u001B[35m            ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪\u001B[0m");
        System.out.println("\u001B[35m            ⚪                                           ⚪\u001B[0m");
        System.out.println("\u001B[35m            ⚪        [1]    Borrow Book                 ⚪\u001B[0m");
        System.out.println("\u001B[35m            ⚪        [2]    Check Borrowed Book         ⚪\u001B[0m");
        System.out.println("\u001B[35m            ⚪        [3]    Sign Out                    ⚪\u001B[0m");
        System.out.println("\u001B[35m            ⚪                                           ⚪\u001B[0m");
        System.out.println("\u001B[35m            ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪\u001B[0m");
        System.out.println();
        System.out.println();
    }
    public void select(){
        do{
            renderView();
            try{
                System.out.println("\u001B[34m Select Function: \u001B[0m");
                int number = scanner.nextInt();
                switch (number){
                    case 1:
                        borrow();
                        break;
                    case 2:
                        displayBorrowBook();
                        break;
                    case 3:
                        menuView.select();
                    default:
                        System.out.println("Please Enter Valid Function!!");
                        select();
                        break;
                }
            } catch (InputMismatchException io){
                System.out.println("Please Enter Valid Function!!");
            }
        } while (true);
    }
    public void displayBorrowBook(){
        List<Borrowbook> borrowbookList = borrowBookService.displayBorrowBook(loginView.currentUser);
        System.out.println("╔═══════════════════════╦═══════════════════════╦═══════════════════════╗");

        System.out.printf("║%23s║%23s║%23s║","BookID","Borrow Date","Expired Date");
        for(Borrowbook borrowbook: borrowbookList){
            System.out.println();
            System.out.println("║═══════════════════════║═══════════════════════║═══════════════════════║");
            System.out.printf("║%23s║%23s║%23s║",borrowbook.getBookid(),borrowbook.getBorrowdate(),borrowbook.getExpDate());
            System.out.println();
        }
        System.out.println("╚═══════════════════════╩═══════════════════════╩═══════════════════════╝");

    }
}
