package org.example.service;

import org.example.model.*;
import org.example.model.Book;
import org.example.service.impls.IBookService;
import org.example.utils.CSVUltis;
import org.example.utils.DateUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookService {

    List<Book> list = new ArrayList<>();
    String filename = "data/book.csv";


    public List<Book> getListBook() {
        List<Book> newBookList = new ArrayList<>();
        List<String> reads = CSVUltis.read(filename);

        for (String read : reads) {
            String[] book = read.split(";");



            newBookList.add(new Book(
                    Integer.parseInt(book[0]),
                    book[1],
                    Category.valueOf(book[2]),
                    Publisher.valueOf(book[3]),
                    Status.valueOf(book[4]),
                    LocalDate.parse(book[5]),
                    Integer.parseInt(book[6])
            ));
        }
        return newBookList;
    }


    public Book getBookDetail(int bookid) {
        list = getListBook();
        for (Book book: list){
            if (book.getId() == bookid){
                return book;
            }
        }
        return null;
    }


    public void add(Book newbook) {
        list = getListBook();
        list.add(newbook);
        CSVUltis.write(filename, list);

    }


    public void delete(int bookid) {
        list = getListBook();
        Book book = getBookDetail(bookid);
        list.remove(book);
        CSVUltis.write(filename,list);
    }


    public void edit(long bookid, Book updateBook) {
        list = getListBook();
        for (Book book: list){
            if(book.getId() == bookid){
                book.setName(updateBook.getName());
                book.setCategory(updateBook.getCategory());
                book.setPublisher(updateBook.getPublisher());
                book.setQuantity(updateBook.getQuantity());
            }
        }
        CSVUltis.write(filename, list);
    }


    public void changeBookStatus(int bookid) {
        list = getListBook();
        Book book = getBookDetail(bookid);
        if(book.getQuantity() == 0){
            if (book.getStatus() == Status.INSTOCK){
                book.setStatus(Status.OUTOFSTOCK);
            }
        }
        CSVUltis.write(filename,list);
    }


    public boolean checkBookStatus(int bookid) {
        list = getListBook();
        Book book = getBookDetail(bookid);
            return book.getStatus() == Status.INSTOCK;
    }

}
