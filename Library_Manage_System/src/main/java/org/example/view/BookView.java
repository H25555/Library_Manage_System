package org.example.view;

import org.example.model.Book;
import org.example.model.Category;
import org.example.model.Publisher;
import org.example.model.Status;
import org.example.service.BookService;
import org.example.utils.DateUtils;

import java.sql.Time;
import java.time.LocalDate;
import java.util.*;

public class BookView {
    private BookService bookService = new BookService();
    private List<Integer> idArr = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    public void select(){
        do {
            renderView();
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("\nSelect Function");
                int number = scanner.nextInt();
                switch (number) {
                    case 1:
                        showBook();
                        break;
                    case 2:
                        add();
                        break;
                    case 3:
                        delete();
                        break;
                    case 4:
                        edit();
                        break;
                    case 5:
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
    public static void renderView() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("                                                   ");
        System.out.println("               [1]    Show Library                  ");
        System.out.println("               [2]    Add Book                      ");
        System.out.println("               [3]    Delete Book                   ");
        System.out.println("               [4]    Edit Book                     ");
        System.out.println("               [5]    Exit                          ");
        System.out.println("                                                   ");
        System.out.println("╚══════════════════════════════════════════════════╝");
    }
    public void showBook(){
        List<Book> bookList = bookService.getListBook();
//        int id, String name, Category category, Publisher publisher, Status status, Date importdate, int quantity
        System.out.println("╔═══════╦════════════════════════════╦═════════════════════╦═════════════════════╦══════════════════╦═══════════════════════════════╦═══════╗");
        System.out.printf("║%7s║%28s║%21s║%21s║%18s║%31s║%7s","ID","Name","Category","Publisher","Status","Import Date", "Qty");
        System.out.println();
        for (Book book:bookList){

            System.out.printf("║%7s║%28s║%21s║%21s║%18s║%31s║%7s║",book.getId(),book.getName(),book.getCategory(),book.getPublisher(),book.getStatus(),book.getImportdate(),book.getQuantity());
            System.out.println();
            idArr.add(book.getId());
        }
        System.out.println("╚═══════╩════════════════════════════╩═════════════════════╩═════════════════════╩══════════════════╩═══════════════════════════════╩═══════╝");
    }
    public void add(){
        List<Book> bookList = bookService.getListBook();

        Book book = new Book();
        String bookname;
        int id = 0;
        if(bookList.size() == 0){
            id = 1;
        } else id = bookList.get(bookList.size()-1).getId() + 1;
        book.setId(id);
        do {
            System.out.println("Enter Book Name: ");
            bookname = scanner.nextLine();
            book.setName(bookname);
        } while (bookname == "");
        int category, publisher, quantity;
        try{
            do {
                System.out.println("Choose Category: ");
                System.out.println("[1] Novel");
                System.out.println("[2] Short");
                System.out.println("[3] Memoir");
                System.out.println("[4] Scifi");
                System.out.println("[5] Dective");
                category = scanner.nextInt();
                switch (category){
                    case 1:
                        book.setCategory(Category.NOVEL);
                        break;
                    case 2:
                        book.setCategory(Category.SHORT);
                        break;
                    case 3:
                        book.setCategory(Category.MEMOIR);
                        break;
                    case 4:
                        book.setCategory(Category.SCIFI);
                        break;
                    case 5:
                        book.setCategory(Category.DETECTIVE);
                        break;
                    default:
                        System.out.println("Please Enter Valid Function");
                }
            } while (category > 5 || category < 1);

        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        try{
            do{
                System.out.println("Choose Publisher:");
                System.out.println("[1] Kim Đồng");
                System.out.println("[2] Phương Nam");
                System.out.println("[3] Thế Kỷ");
                publisher = scanner.nextInt();
                switch (publisher){
                    case 1:
                        book.setPublisher(Publisher.KIMDONG);
                        break;
                    case 2:
                        book.setPublisher(Publisher.PHUONGNAM);
                        break;
                    case 3:
                        book.setPublisher(Publisher.THEKY);
                        break;
                    default:
                        System.out.println("Please Enter Valid Function:");
                }
            } while (publisher >3 || publisher < 1);
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        book.setStatus(Status.INSTOCK);
//        book.setImportdate(DateUtils.parseDate(String.valueOf(new Date())));
        LocalDate date = LocalDate.now();

        book.setImportdate(date);
//        System.out.println(String.valueOf(new Date()));
//        System.out.println(new Date().getClass().getSimpleName());
            try {
                System.out.println("Enter quantity:");
                quantity = scanner.nextInt();
                book.setQuantity(quantity);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            bookService.add(book);

    }
    public void edit(){
        int category, publisher, quantity;
        Book book = new Book();
        showBook();
        int id;
        do{
            System.out.println("Enter Book ID:");
            id = scanner.nextInt();
            book.setId(id);
        } while (checkExistId(id) == false);
            System.out.println("Enter Book Name:");
            book.setName(scanner.next());
        try{
            do {
                System.out.println("Choose Category: ");
                System.out.println("[1] Novel");
                System.out.println("[2] Short");
                System.out.println("[3] Memoir");
                System.out.println("[4] Scifi");
                System.out.println("[5] Dective");
                category = scanner.nextInt();
                switch (category){
                    case 1:
                        book.setCategory(Category.NOVEL);
                        break;
                    case 2:
                        book.setCategory(Category.SHORT);
                        break;
                    case 3:
                        book.setCategory(Category.MEMOIR);
                        break;
                    case 4:
                        book.setCategory(Category.SCIFI);
                        break;
                    case 5:
                        book.setCategory(Category.DETECTIVE);
                        break;
                    default:
                        System.out.println("Please Enter Valid Function");
                }
            } while (category > 5 || category < 1);

        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        try{
            do{
                System.out.println("Choose Publisher:");
                System.out.println("[1] Kim Đồng");
                System.out.println("[2] Phương Nam");
                System.out.println("[3] Thế Kỷ");
                publisher = scanner.nextInt();
                switch (publisher){
                    case 1:
                        book.setPublisher(Publisher.KIMDONG);
                        break;
                    case 2:
                        book.setPublisher(Publisher.PHUONGNAM);
                        break;
                    case 3:
                        book.setPublisher(Publisher.THEKY);
                        break;
                    default:
                        System.out.println("Please Enter Valid Function:");
                }
            } while (publisher >3 || publisher < 1);
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        book.setStatus(Status.INSTOCK);
        LocalDate date = LocalDate.now();
        book.setImportdate(date);
        try {
            System.out.println("Enter quantity:");
            quantity = scanner.nextInt();
            book.setQuantity(quantity);
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        bookService.edit(id, book);
    }
    public boolean checkExistId(int id) {
        for (int idBook:idArr) {
            if (idBook == id) return true;
        }
        return false;
    }
    public void delete(){
        showBook();
        boolean check = true;
        int bookid ;
        do{
            System.out.println("Enter Book ID:");
            bookid = scanner.nextInt();
        } while (checkExistId(bookid) == false);
        bookService.delete(bookid);
        System.out.println("Delete Successful !!!");
    }
}
