package org.example.service;

import org.example.model.Book;
import org.example.model.Borrowbook;
import org.example.model.User;
import org.example.service.impls.IBorrowBookService;
import org.example.utils.CSVUltis;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowBookService implements IBorrowBookService {
    static String filename = "data/borrowbook.csv";
    String filename1 = "data/book.csv";
    List<Borrowbook> listBorrowBook =new ArrayList<>();
    List<Book> listBook = new ArrayList<>();
    BookService bookService =new BookService();
    @Override
    public List<Borrowbook> getBorrowBook() {
        List<Borrowbook> borrowbookList = new ArrayList<>();
        List<String> reads = CSVUltis.read(filename);
        for (String read: reads){
            String[] borrowbook = read.split(",");
//            String strDate = borrowbook[5];
//            Date date = DateUtils.parseDate(strDate);
            borrowbookList.add(new Borrowbook(
                    Integer.parseInt(borrowbook[0]),
                    Integer.parseInt(borrowbook[1]),
                    Integer.parseInt(borrowbook[2]),
                    LocalDate.parse(borrowbook[3]),
                    LocalDate.parse(borrowbook[4])
            ));
        }
        return borrowbookList;
    }
    public Borrowbook getBorrowBookDetail(int borrowbookid){
        listBorrowBook = getBorrowBook();
        for (Borrowbook borrowbook: listBorrowBook){
            if (borrowbook.getId() == borrowbookid){
                return borrowbook;
            }
        }
        return null;
    }
    public void borrowBook( int bookid, User user){
        listBorrowBook = getBorrowBook();
        listBook = bookService.getListBook();
        LocalDate date = LocalDate.now();
//        User user = userServices.getUserDetailByPhoneNumber(phonenumber);
//        Book book = bookService.getBookDetail(bookid);
        for (Book book: listBook){
            if(bookid == book.getId()){
                book.setQuantity(book.getQuantity()-1);
            }
        }
        Borrowbook borrowbook = new Borrowbook();
        int id = 0;
        if (listBorrowBook.size() == 0){
            id = 1;
        } else  id = listBorrowBook.get(listBorrowBook.size() - 1).getId() + 1;
        borrowbook.setId(id);
        borrowbook.setBookid(bookid);
        borrowbook.setUserid(user.getId());
        borrowbook.setBorrowdate(date);
        borrowbook.setExpDate(date.plusDays(7));
        borrowbook.setUserid(user.getId());
        bookService.changeBookStatus(bookid);
        listBorrowBook.add(borrowbook);
        CSVUltis.write(filename1, listBook);
        CSVUltis.write(filename, listBorrowBook);
    }
    public void confirmReturnBook(int borrowbookID){
        listBorrowBook = getBorrowBook();
        listBook = bookService.getListBook();
        Borrowbook borrowbook = getBorrowBookDetail(borrowbookID);
//        Book returnbook = bookService.getBookDetail(borrowbook.getBookid());
//        returnbook.setQuantity(returnbook.getQuantity() + 1);
        for (Book book : listBook){
            if (book.getId() == borrowbook.getBookid()){
                book.setQuantity(book.getQuantity()+1);
            }
        }
        listBorrowBook.remove(borrowbook);
        CSVUltis.write(filename, listBorrowBook);
        CSVUltis.write(filename1, listBook);
    }
    public List<Borrowbook> displayBorrowBook(User user){
        List<Borrowbook> listBorrowBookByUser = new ArrayList<>();
        listBorrowBook = getBorrowBook();
        int id = user.getId();
        for(Borrowbook borrowbook: listBorrowBook ){
            if (id == borrowbook.getUserid()){
                listBorrowBookByUser.add(borrowbook);
            }
        }
        return listBorrowBookByUser;
    }
}
