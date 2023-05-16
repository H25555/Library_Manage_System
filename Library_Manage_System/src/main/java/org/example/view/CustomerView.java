package org.example.view;

import org.example.model.Book;
import org.example.model.Borrowbook;
import org.example.model.User;
import org.example.service.BookService;
import org.example.service.BorrowBookService;

import java.io.IOException;
import java.text.ParseException;
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

    public void borrow() {
        bookView.showBook();
//        try {
            int bookid;
            do {
                System.out.println("Enter Book ID To Borrow:");
                bookid = scanner.nextInt();
            } while (bookView.checkExistId(bookid) == false);
            if (!bookService.checkBookStatus(bookid)) {
                select();
            }
            borrowBookService.borrowBook(bookid, loginView.currentUser);
            System.out.println("Successful !! Your Borrow Book List Have Created !! ");
            System.out.println("Borrow Date: " + LocalDate.now());
            System.out.println("Please Return Before " + LocalDate.now().plusDays(7));
//        } catch (InputMismatchException e){
//            System.out.println("Please Enter A Valid ID");
//            borrow();
//        }
    }
    public void renderView(){
        System.out.println();
        System.out.println("\u001B[35m                                Customer Display    \u001B[0m");
        System.out.println();
        System.out.println("\u001B[35m            ╔══════════════════════════════════════════════════════════╗\u001B[0m");
        System.out.println("\u001B[35m                                                             \u001B[0m");
        System.out.println("\u001B[35m                        [1]    Display Library                \u001B[0m");
        System.out.println("\u001B[35m                        [2]    Search Book By Name            \u001B[0m");
        System.out.println("\u001B[35m                        [3]    Borrow Book                    \u001B[0m");
        System.out.println("\u001B[35m                        [4]    Check Borrowed Book            \u001B[0m");
        System.out.println("\u001B[35m                        [5]    Sign Out                       \u001B[0m");
        System.out.println("\u001B[35m                                                             \u001B[0m");
        System.out.println("\u001B[35m            ╚══════════════════════════════════════════════════════════╝\u001B[0m");
        System.out.println();
        System.out.println();
    }
    public void select(){
        do{
            renderView();
            try{
                System.out.println("\u001B[34m Select Function: \u001B[0m");
                String input = scanner.nextLine();
                int number = Integer.parseInt(input);
                switch (number){
                    case 1:
                        bookView.showBook();
                        break;
                    case 2:
                        search();
                        break;
                    case 3:
                        borrow();
                        break;
                    case 4:
                        displayBorrowBook();
                        break;
                    case 5:
                        menuView.select();
                        break;
                    default:
                        System.out.println("Please Enter Valid Function!!");
                        select();
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            } catch (IOException | ParseException | InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (true);
    }
    public void displayBorrowBook(){
        List<Borrowbook> borrowbookList = borrowBookService.displayBorrowBook(loginView.currentUser);
        System.out.println("╔═══════════════════════╦═══════════════════════╦═══════════════════════╗");

        System.out.printf("║%23s║%23s║%23s║","Book name","Borrow Date","Expired Date");
        System.out.println();
        for(Borrowbook borrowbook: borrowbookList){

            System.out.println();
            System.out.println("║═══════════════════════║═══════════════════════║═══════════════════════║");
            System.out.printf("║%23s║%23s║%23s║",bookService.getBookDetail(borrowbook.getBookid()).getName(),borrowbook.getBorrowdate(),borrowbook.getExpDate());
            System.out.println();
        }
        System.out.println("╚═══════════════════════╩═══════════════════════╩═══════════════════════╝");

    }
    public void search()throws IOException, ParseException, InterruptedException {
        List<Book> results = new ArrayList<>();
        List<Book> books = bookService.getListBook();

        boolean checkKW = false;
        do{
            System.out.println("Enter Book Name:");
            String kw = scanner.next().toUpperCase();
            boolean checkOut = false;
            for (int i = 0; i< books.size(); i++){
                if (books.get(i).getName().toUpperCase().contains(kw.toUpperCase())) {
                    results.add(books.get(i));
                    checkOut = true;
                }
            }if (!checkOut) {
                System.out.println("No matching books found, please re-enter!");
                checkKW = false;
            } else {
                checkKW = true;
            }
        } while (!checkKW);
        showBookList(results);
    }


    public void showBookList(List<Book> books){
        System.out.println("╔═══════╦════════════════════════════╦═════════════════════╦═════════════════════╦══════════════════╦═══════════════════════════════╦═══════╗");
        System.out.printf("║%7s║%28s║%21s║%21s║%18s║%31s║%7s║","ID","Name","Category","Publisher","Status","Import Date", "Qty");
        System.out.println();
        for (Book book: books){
            System.out.printf("║%7s║%28s║%21s║%21s║%18s║%31s║%7s║",book.getId(),book.getName(),book.getCategory(),book.getPublisher(),book.getStatus(),book.getImportdate(),book.getQuantity());
            System.out.println();
        }
        System.out.println("╚═══════╩════════════════════════════╩═════════════════════╩═════════════════════╩══════════════════╩═══════════════════════════════╩═══════╝");

    }
}
